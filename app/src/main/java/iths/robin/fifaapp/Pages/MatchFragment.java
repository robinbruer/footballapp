package iths.robin.fifaapp.Pages;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import iths.robin.fifaapp.Data.Models.MatchResult;
import iths.robin.fifaapp.Data.Models.TeamUser;
import iths.robin.fifaapp.R;
import iths.robin.fifaapp.Utils.Constants;
import iths.robin.fifaapp.Utils.HelperClass;

public class MatchFragment extends Fragment {

    private String TAG = "MatchFragment";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String team1, team2;
    private Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.match_fragment, container, false);
        bundle = this.getArguments();
        if(bundle != null){
            team1 = bundle.getString("team1");
            team2 = bundle.getString("team2");

            HelperClass.setTexttoView(view, team1, R.id.displayTeam1Match);
            HelperClass.setTexttoView(view, team2, R.id.displayTeam2Match);
        } else {
            team1 = "";
            team2 = "";
        }
        view.findViewById(R.id.finishMatchBtn).setOnClickListener(this::finishMatch);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*
        bundle = this.getArguments();
        if(bundle != null){
            team1 = bundle.getString("team1");
            team2 = bundle.getString("team2");

            HelperClass.setTexttoView(getView(), team1, R.id.displayTeam1Match);
            HelperClass.setTexttoView(getView(), team2, R.id.displayTeam2Match);
        } else {
            team1 = "";
            team2 = "";
        }
        getActivity().findViewById(R.id.finishMatchBtn).setOnClickListener(this::finishMatch);

         */
    }

    private void finishMatch(View view){
        Log.d(TAG, "startMatch: ");

        int team1result = HelperClass.getinput(view, R.id.resultsTeam1Match);
        int team2result = HelperClass.getinput(view, R.id.resultsTeam2Match);

        bundle.putString("result1",HelperClass.getinputToString(view,R.id.resultsTeam1Match));
        bundle.putString("result2",HelperClass.getinputToString(view,R.id.resultsTeam2Match));

        MatchResult resultTeam1 = new MatchResult();
        MatchResult resultTeam2 = new MatchResult();

        if(team1result > team2result) {
            resultTeam1.setMatchResult(1,0,0,3);
            resultTeam2.setMatchResult(0,1,0,0);
        } else if(team1result < team2result) {
            resultTeam1.setMatchResult(0,1,0,0);
            resultTeam2.setMatchResult(1,0,0,3);
        } else {
            resultTeam1.setMatchResult(0,0,1,1);
            resultTeam2.setMatchResult(0,0,1,1);
        }

        TeamUser teamUser1 = new TeamUser(1, resultTeam1.getWon(),resultTeam1.getDraw(),resultTeam1.getLost(),team1result,team2result,resultTeam1.getPoints());
        TeamUser teamUser2 = new TeamUser(1,resultTeam2.getWon(),resultTeam2.getDraw(),resultTeam2.getLost(),team2result,team2result,resultTeam2.getPoints());

        teamUser1.setUserId("005");
        teamUser2.setUserId("006");

        teamUser1.teamsPlayed.add(team1);
        teamUser2.teamsPlayed.add(team2);

        Map<String, Object> teams = new HashMap<>();
        teams.put(teamUser1.getUserId(), teamUser1);
        teams.put(teamUser2.getUserId(), teamUser2);

        WriteBatch batch = db.batch();

        DocumentReference user1 = db.collection(Constants.FB_PATH_USERS).document(teamUser1.getUserId());
        DocumentReference user2 = db.collection(Constants.FB_PATH_USERS).document(teamUser2.getUserId());

        Task task1 = user1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "Document exists!");
                        batch.update(user1, Constants.FB_PLAYED, FieldValue.increment(1),
                                Constants.FB_LOST, FieldValue.increment(resultTeam1.getLost()),
                                Constants.FB_WON, FieldValue.increment(resultTeam1.getWon()),
                                Constants.FB_DRAW, FieldValue.increment(resultTeam1.getDraw()),
                                Constants.FB_POINTS, FieldValue.increment(resultTeam1.getPoints()),
                                Constants.FB_TEAMS_PLAYED, FieldValue.arrayUnion(team1),
                                Constants.FB_GF, FieldValue.increment(team1result),
                                Constants.FB_GA, FieldValue.increment(team2result));
                    } else {
                        Log.d(TAG, "Document does not exist!");
                        batch.set(user1,teamUser1);
                    }
                } else {
                    Log.d(TAG, "Failed with: ", task.getException());
                }
            }
        });

        Task task2 = user2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "Document exists!");
                        batch.update(user2, Constants.FB_PLAYED, FieldValue.increment(1),
                                Constants.FB_LOST, FieldValue.increment(resultTeam2.getLost()),
                                Constants.FB_WON, FieldValue.increment(resultTeam2.getWon()),
                                Constants.FB_DRAW, FieldValue.increment(resultTeam2.getDraw()),
                                Constants.FB_POINTS, FieldValue.increment(resultTeam2.getPoints()),
                                Constants.FB_TEAMS_PLAYED, FieldValue.arrayUnion(team2),
                                Constants.FB_GF, FieldValue.increment(team2result),
                                Constants.FB_GA, FieldValue.increment(team1result));
                    } else {
                        batch.set(user2,teamUser2);
                        Log.d(TAG, "Document does not exist!");
                    }
                } else {
                    Log.d(TAG, "Failed with: ", task.getException());
                }
            }
        });


        Tasks.whenAllSuccess(task1, task2).addOnSuccessListener(new OnSuccessListener<List<Object>>() {
            @Override
            public void onSuccess(List<Object> list) {
                batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
            }
        });


        Fragment fragment = new FinalScoreFragment();
        fragment.setArguments(bundle);
        requireFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();


    }


}
