package iths.robin.fifaapp.Data.Firebase;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

import iths.robin.fifaapp.Database.UserDatabaseSource;

import static android.app.Activity.RESULT_OK;

public class FirebaseSource implements UserDatabaseSource {

    private FirebaseAuth mAuth;
    private static final int RC_SIGN_IN = 123;

    public FirebaseSource() {
        mAuth = FirebaseAuth.getInstance();
    }

    private String TAG = "FirebaseSource";

    @Override
    public FirebaseAuth getAuthInstance () {
        return mAuth;
    }

    @Override
    public void authProviders(Activity context) {
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                //new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build());

        context.startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    public void onResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                //signedIn();
                // ...
            } else {
                Log.d(TAG, "onActivityResult: failed login");
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }

    @Override
    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }

    @Override
    public boolean checkAuthState() {
        if(mAuth.getCurrentUser() == null){
            Log.d(TAG, "user is null, sent back to login");
            return false;
        }else{
            Log.d(TAG, "user is authenticated, user id: " + mAuth.getCurrentUser().getUid());
            return true;
        }
    }

}
