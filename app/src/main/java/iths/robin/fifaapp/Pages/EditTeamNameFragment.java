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
import iths.robin.fifaapp.Data.Repositories.UserRepository;
import iths.robin.fifaapp.R;
import iths.robin.fifaapp.Utils.HelperClass;

public class EditTeamNameFragment extends Fragment {
    private String TAG = "EditTeamNameFragment";
    private UserRepository userRepository = new UserRepository();
    private DatabaseRepository databaseRepository = new DatabaseRepository();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_team_fragment, container, false);
        view.findViewById(R.id.editTeam).setOnClickListener(this::editTeam);
        return view;
    }

    private void editTeam(View view){
        String teamName = HelperClass.getinputToString(getView(), R.id.editTeamEditText);

        TeamUser teamUser = new TeamUser();
        teamUser.setTeamName(teamName);
        teamUser.setUserId(userRepository.getuserId());

        databaseRepository.creatTeam(teamUser);

        Toasty.success(getActivity(), getString(R.string.updated_team_name), Toast.LENGTH_SHORT, true).show();

    }
}
