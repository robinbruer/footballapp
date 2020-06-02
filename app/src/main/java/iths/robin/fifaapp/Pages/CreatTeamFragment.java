package iths.robin.fifaapp.Pages;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import iths.robin.fifaapp.Data.Models.TeamUser;
import iths.robin.fifaapp.R;
import iths.robin.fifaapp.Utils.Constants;
import iths.robin.fifaapp.Utils.HelperClass;

public class CreatTeamFragment extends Fragment {

    private String TAG = "CreatTeamFragment";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_team_fragment, container, false);
        view.findViewById(R.id.createTeam).setOnClickListener(this::createTeam);
        return view;
    }

    private void createTeam(View view){
       String teamName = HelperClass.getinputToString(view, R.id.createTeamEditText);

        TeamUser teamUser = new TeamUser();
        teamUser.setTeamName(teamName);
        teamUser.setUserId("firebaseId"+teamName);

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
}
