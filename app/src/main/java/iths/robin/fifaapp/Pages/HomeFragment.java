package iths.robin.fifaapp.Pages;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import iths.robin.fifaapp.Ui.Auth.LoginActivity;
import iths.robin.fifaapp.R;

public class HomeFragment extends Fragment {

    private String TAG = "HomeFragment";
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        TextView userInfo;
        mAuth = FirebaseAuth.getInstance();
        userInfo = view.findViewById(R.id.user_home_fragment);
        currentUser = mAuth.getCurrentUser();
        // TODO:
        userInfo.setText(currentUser.getEmail());
        view.findViewById(R.id.logoutbtn).setOnClickListener(this::logOut);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

    }

    private void logOut(View view){
        AuthUI.getInstance()
                .signOut(getContext())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = mAuth.getCurrentUser();
        Log.d(TAG, "onStart: user logged in " + currentUser);
        //updateUI(currentUser);
    }
}
