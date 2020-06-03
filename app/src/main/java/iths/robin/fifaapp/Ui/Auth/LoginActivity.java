package iths.robin.fifaapp.Ui.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import iths.robin.fifaapp.Authentication.SignIn;
import iths.robin.fifaapp.Data.Repositories.UserRepository;
import iths.robin.fifaapp.R;

public class LoginActivity extends AppCompatActivity {

    private String TAG = "LoginActivity";
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userRepository = new UserRepository();
        userRepository.authProviders(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        userRepository.onResult(requestCode,resultCode,data);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
        if(userRepository.checkAuthState()){
            Intent signIn = new Intent(LoginActivity.this, SignIn.class);
            startActivity(signIn);
        } else {
            userRepository.authProviders(this);
        }
    }

}
