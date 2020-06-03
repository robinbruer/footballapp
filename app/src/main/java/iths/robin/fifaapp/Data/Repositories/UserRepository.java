package iths.robin.fifaapp.Data.Repositories;

import android.app.Activity;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import iths.robin.fifaapp.Data.Firebase.FirebaseSource;
import iths.robin.fifaapp.Database.UserDatabaseSource;

public class UserRepository implements UserDatabaseSource {

    private FirebaseSource firebaseSource;

    public UserRepository() {
        this.firebaseSource = new FirebaseSource();
    }

    @Override
    public void authProviders(Activity context) {
        firebaseSource.authProviders(context);
    }

    @Override
    public void onResult(int requestCode, int resultCode, Intent data) {
        firebaseSource.onResult(requestCode, resultCode, data);
    }

    @Override
    public FirebaseAuth getAuthInstance (){
        return firebaseSource.getAuthInstance();
    }

    @Override
    public FirebaseUser getCurrentUser(){
        return firebaseSource.getCurrentUser();
    }

    @Override
    public boolean checkAuthState() {
        return firebaseSource.checkAuthState();
    }
}
