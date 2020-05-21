package iths.robin.fifaapp.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import iths.robin.fifaapp.Pages.HomeActivity;
import iths.robin.fifaapp.R;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Intent homepage = new Intent(SignIn.this, HomeActivity.class);
        startActivity(homepage);


    }
}
