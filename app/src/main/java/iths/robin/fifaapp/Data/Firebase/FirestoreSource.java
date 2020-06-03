package iths.robin.fifaapp.Data.Firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import iths.robin.fifaapp.Data.Models.MatchResult;
import iths.robin.fifaapp.Data.Models.TeamUser;
import iths.robin.fifaapp.Database.DataBaseSource;
import iths.robin.fifaapp.Utils.Constants;


public class FirestoreSource implements DataBaseSource {

    private List<String> teamsName = new ArrayList<>();
    private List<String> teamId = new ArrayList<>();
    private ArrayList<TeamUser> teamUserArrayList = new ArrayList<>();

    public ArrayList<TeamUser> getTeamUserArrayList() {
        return teamUserArrayList;
    }

    @Override
    public void playMatch(TeamUser teamUser1, TeamUser teamUser2, MatchResult resultTeam1,
                          MatchResult resultTeam2, int team1result, int team2result, String team1, String team2) {
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
    }

    private FirebaseFirestore db;
    private String TAG = "FirestoreSource";

    public FirestoreSource() {
        db = FirebaseFirestore.getInstance();
    }

    public List<String> getTeamsName() {
        return teamsName;
    }

    public List<String> getTeamId() {
        return teamId;
    }

    @Override
    public void creatTeam(TeamUser teamUser) {
        db.collection(Constants.FB_PATH_USERS)
                .add(teamUser)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    @Override
    public Task setLeagueTable() {
        return db.collection(Constants.FB_PATH_USERS)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d(TAG, document.getId() + " => " + document.getData());

                                Map<String, Object> teams = document.getData();
                                //TODO:

                                //int played, int won, int drawn, int lost, int gf, int ga, int points
                                TeamUser team = new TeamUser(document.get(Constants.FB_PLAYED).toString(),
                                        document.get(Constants.FB_WON).toString(),
                                        document.get(Constants.FB_DRAW).toString(),
                                        document.get(Constants.FB_LOST).toString(),
                                        document.get(Constants.FB_GF).toString(),
                                        document.get(Constants.FB_GA).toString(),
                                        document.get(Constants.FB_POINTS).toString(),
                                        document.get(Constants.FB_TEAM_NAME).toString());


                                teamUserArrayList.add(team);

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public Task configureMatch() {
       return db.collection(Constants.FB_PATH_USERS)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                               teamsName.add(document.get(Constants.FB_TEAM_NAME).toString());
                               teamId.add(document.getId());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }

                    }
                });
    }
}
