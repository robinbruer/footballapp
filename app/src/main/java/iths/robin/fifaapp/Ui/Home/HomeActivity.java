package iths.robin.fifaapp.Ui.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import iths.robin.fifaapp.Pages.AboutFragment;
import iths.robin.fifaapp.Pages.DashboardFragment;
import iths.robin.fifaapp.Pages.HomeFragment;
import iths.robin.fifaapp.R;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setSelectedItemId(R.id.home);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
        }

        // TODO: Lamda
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.dashboard:
                        fragment = new DashboardFragment();
                        //overridePendingTransition(0, 0);
                        break;
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.about:
                        fragment = new AboutFragment();
                        break;

                }

                // TODO: nullcheck
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                return true;
            }
        });


    }

}
