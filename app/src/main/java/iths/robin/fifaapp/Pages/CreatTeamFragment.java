package iths.robin.fifaapp.Pages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import es.dmoral.toasty.Toasty;
import iths.robin.fifaapp.Data.Models.TeamUser;
import iths.robin.fifaapp.Data.Repositories.DatabaseRepository;
import iths.robin.fifaapp.R;
import iths.robin.fifaapp.Utils.HelperClass;

public class CreatTeamFragment extends Fragment {

    private String TAG = "CreatTeamFragment";
    private DatabaseRepository databaseRepository;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_team_fragment, container, false);
        view.findViewById(R.id.createTeam).setOnClickListener(this::createTeam);
        databaseRepository = new DatabaseRepository();
        return view;
    }

    private void createTeam(View view){
       String teamName = HelperClass.getinputToString(getView(), R.id.createTeamEditText);

        TeamUser teamUser = new TeamUser();
        teamUser.setTeamName(teamName);

        databaseRepository.creatTeam(teamUser);

        Toasty.success(getActivity(), "Created new team!", Toast.LENGTH_SHORT, true).show();
    }
}
