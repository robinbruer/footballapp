package iths.robin.fifaapp.Pages;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.util.List;

import iths.robin.fifaapp.Data.Repositories.DatabaseRepository;
import iths.robin.fifaapp.R;
import iths.robin.fifaapp.Ui.Match.AwaySpinnerClass;
import iths.robin.fifaapp.Ui.Match.HomeSpinnerClass;
import iths.robin.fifaapp.Utils.Constants;

public class ConfigureMatchFragment extends Fragment {

    private String TAG = "ConfigureMatchFragment";
    private Spinner spinnerTeam1, spinnerTeam2;
    private DatabaseRepository databaseRepository;
    private HomeSpinnerClass homeSpinnerClass;
    private AwaySpinnerClass awaySpinnerClass;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.configure_match_fragment, container, false);
        spinnerTeam1 = view.findViewById(R.id.spinnerTeam1);
        spinnerTeam2 = view.findViewById(R.id.spinnerTeam2);
        homeSpinnerClass = new HomeSpinnerClass();
        awaySpinnerClass = new AwaySpinnerClass();
        databaseRepository = new DatabaseRepository();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().findViewById(R.id.startMatchBtn).setOnClickListener(this::startMatch);

        readData();
    }

    private void startMatch(View view){
        Bundle bundle = new Bundle();
        bundle.putString(Constants.BUNDLE_KEY_TEAMID1, databaseRepository.getTeamId().get(homeSpinnerClass.getPosition()));
        bundle.putString(Constants.BUNDLE_KEY_TEAMID2, databaseRepository.getTeamId().get(awaySpinnerClass.getPosition()));
        bundle.putString(Constants.BUNDLE_KEY_TEAM1, databaseRepository.getTeamsName().get(homeSpinnerClass.getPosition()));
        bundle.putString(Constants.BUNDLE_KEY_TEAM2,databaseRepository.getTeamsName().get(awaySpinnerClass.getPosition()));
        Fragment fragment = new MatchFragment();
        fragment.setArguments(bundle);

        requireFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
    }

    private void readData() {
        Tasks.whenAllSuccess(databaseRepository.configureMatch()).addOnSuccessListener(new OnSuccessListener<List<Object>>() {
            @Override
            public void onSuccess(List<Object> list) {
                ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, databaseRepository.getTeamsName());

                dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinnerTeam1.setAdapter(dataAdapter1);
                spinnerTeam2.setAdapter(dataAdapter1);

                spinnerTeam1.setOnItemSelectedListener(homeSpinnerClass);
                spinnerTeam2.setOnItemSelectedListener(awaySpinnerClass);
            }
        });
    }



}
