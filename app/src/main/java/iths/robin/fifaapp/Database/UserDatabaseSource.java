package iths.robin.fifaapp.Database;

import android.app.Activity;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public interface UserDatabaseSource {

    void authProviders(Activity context);

    void onResult(int requestCode, int resultCode, Intent data);

    FirebaseAuth getAuthInstance();

    FirebaseUser getCurrentUser();

    boolean checkAuthState();
}
