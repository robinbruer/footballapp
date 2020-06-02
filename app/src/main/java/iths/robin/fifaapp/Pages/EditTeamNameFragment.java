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
import com.google.firebase.firestore.FirebaseFirestore;

import iths.robin.fifaapp.Data.Models.TeamUser;
import iths.robin.fifaapp.R;
import iths.robin.fifaapp.Utils.Constants;
import iths.robin.fifaapp.Utils.HelperClass;

public class EditTeamNameFragment extends Fragment {
    private String TAG = "EditTeamNameFragment";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_team_fragment, container, false);
        view.findViewById(R.id.editTeam).setOnClickListener(this::editTeam);
        return view;
    }

    private void editTeam(View view){
        String teamName = HelperClass.getinputToString(view, R.id.editTeamEditText);

        TeamUser teamUser = new TeamUser();
        teamUser.setTeamName(teamName);
        teamUser.setUserId("firebaseId");

        db.collection(Constants.FB_PATH_USERS).document(teamUser.getUserId())
                .set(teamUser)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot added with ID: ");
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
