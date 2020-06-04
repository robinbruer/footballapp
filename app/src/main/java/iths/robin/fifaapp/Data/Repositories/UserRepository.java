package iths.robin.fifaapp.Data.Repositories;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.google.android.gms.tasks.Task;

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
    public boolean checkAuthState() {
        return firebaseSource.checkAuthState();
    }

    @Override
    public Task logOut(Context context) {
        return firebaseSource.logOut(context);
    }

    @Override
    public String getUserEmail() {
        return firebaseSource.getUserEmail();
    }

    @Override
    public String getuserId() {
        return firebaseSource.getuserId();
    }
}
