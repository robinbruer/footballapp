package iths.robin.fifaapp.Ui.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import iths.robin.fifaapp.Pages.HomeFragment;
import iths.robin.fifaapp.Pages.KickoffFragment;
import iths.robin.fifaapp.Pages.LeagueTableFragment;
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
                    case R.id.table:
                        fragment = new LeagueTableFragment();
                        break;
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.kickoff:
                        fragment = new KickoffFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
                return true;
            }
        });


    }

}
