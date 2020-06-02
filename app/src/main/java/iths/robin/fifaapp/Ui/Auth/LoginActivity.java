package iths.robin.fifaapp.Ui.Auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.Arrays;
import java.util.List;

import iths.robin.fifaapp.Authentication.SignIn;
import iths.robin.fifaapp.Data.Repositories.UserRepository;
import iths.robin.fifaapp.R;

public class LoginActivity extends AppCompatActivity {

    private Button signInbtn,signUpBtn;
    private LoginButton fbLoginButton;
    private CallbackManager callbackManager;
    //private FirebaseAuth mAuth;
    private static final String EMAIL = "email";
    private String TAG = "LoginActivity";
    private UserRepository userRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //FacebookSdk.sdkInitialize(getApplicationContext());
        //AppEventsLogger.activateApp(this);
        userRepository = new UserRepository();
        userRepository.getAuthInstance();
        userRepository.authProviders(this);


        signInbtn = findViewById(R.id.signInbtn);

        signInbtn.setOnClickListener(this::signIn);
/*


        callbackManager = CallbackManager.Factory.create();

        fbLoginButton = findViewById(R.id.fb_login_button);
        fbLoginButton.setReadPermissions("email", "public_profile");
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        fbLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
            }
        });



 */

    }
/*
    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);
        // [START_EXCLUDE silent]
        //showProgressBar();
        // [END_EXCLUDE]

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d(TAG, "signInWithCredential:success " + user);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // [START_EXCLUDE]
                        //hideProgressBar();
                        // [END_EXCLUDE]
                    }
                });
    }

 */

    private void signedIn(){
        Intent signIn = new Intent(LoginActivity.this, SignIn.class);
        startActivity(signIn);

    }

    private void signIn(View view){
        Intent signIn = new Intent(LoginActivity.this, SignIn.class);
        startActivity(signIn);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        userRepository.onResult(requestCode,resultCode,data);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = userRepository.getCurrentUser();
        Log.d(TAG, "onStart: user logged in " + currentUser);
        //updateUI(currentUser);
    }
}
