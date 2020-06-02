package iths.robin.fifaapp.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

import java.util.Arrays;

import iths.robin.fifaapp.Ui.Home.HomeActivity;
import iths.robin.fifaapp.R;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if(isLoggedIn) {
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
        }
        Log.d("SignIn", "onCreate: is logged in: " + isLoggedIn);
        Intent homepage = new Intent(SignIn.this, HomeActivity.class);
        startActivity(homepage);


    }
}
