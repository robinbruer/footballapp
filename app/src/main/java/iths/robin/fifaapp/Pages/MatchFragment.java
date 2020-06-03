package iths.robin.fifaapp.Pages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import iths.robin.fifaapp.Data.Models.MatchResult;
import iths.robin.fifaapp.Data.Models.TeamUser;
import iths.robin.fifaapp.Data.Repositories.DatabaseRepository;
import iths.robin.fifaapp.R;
import iths.robin.fifaapp.Utils.Constants;
import iths.robin.fifaapp.Utils.HelperClass;

public class MatchFragment extends Fragment {

    private String TAG = "MatchFragment";
    private DatabaseRepository databaseRepository;
    private String team1, team2, teamId1, teamId2;
    private Bundle bundle;
    private int team1result, team2result;
    private MatchResult resultTeam1, resultTeam2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.match_fragment, container, false);
        databaseRepository = new DatabaseRepository();
        bundle = this.getArguments();
        if(bundle != null){
            team1 = bundle.getString(Constants.BUNDLE_KEY_TEAM1);
            team2 = bundle.getString(Constants.BUNDLE_KEY_TEAM2);
            teamId1 = bundle.getString(Constants.BUNDLE_KEY_TEAMID1);
            teamId2 = bundle.getString(Constants.BUNDLE_KEY_TEAMID2);
            HelperClass.setTexttoView(view, team1, R.id.displayTeam1Match);
            HelperClass.setTexttoView(view, team2, R.id.displayTeam2Match);
        } else {
            team1 = "";
            team2 = "";
        }
        view.findViewById(R.id.finishMatchBtn).setOnClickListener(this::finishMatch);
        return view;
    }

    private void finishMatch(View view){

        team1result = HelperClass.getinput(getView(), R.id.resultsTeam1Match);
        team2result = HelperClass.getinput(getView(), R.id.resultsTeam2Match);

        bundle.putString(Constants.BUNDLE_KEY_RESULTAT1,HelperClass.getinputToString(getView(),R.id.resultsTeam1Match));
        bundle.putString(Constants.BUNDLE_KEY_RESULTAT2,HelperClass.getinputToString(getView(),R.id.resultsTeam2Match));

        resultTeam1 = new MatchResult();
        resultTeam2 = new MatchResult();

        calcResult();

        TeamUser teamUser1 = new TeamUser(1, resultTeam1.getWon(),resultTeam1.getDraw(),resultTeam1.getLost(),team1result,team2result,resultTeam1.getPoints());
        TeamUser teamUser2 = new TeamUser(1,resultTeam2.getWon(),resultTeam2.getDraw(),resultTeam2.getLost(),team2result,team2result,resultTeam2.getPoints());

        teamUser1.setUserId(teamId1);
        teamUser2.setUserId(teamId2);

        teamUser1.teamsPlayed.add(team1);
        teamUser2.teamsPlayed.add(team2);

        databaseRepository.playMatch(teamUser1, teamUser2,resultTeam1, resultTeam2,team1result,team2result,team1,team2);

        Fragment fragment = new FinalScoreFragment();
        fragment.setArguments(bundle);
        requireFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    private void calcResult() {
        if(team1result > team2result) {
            resultTeam1.setMatchResult(1,0,0,3);
            resultTeam2.setMatchResult(0,1,0,0);
        } else if(team1result < team2result) {
            resultTeam1.setMatchResult(0,1,0,0);
            resultTeam2.setMatchResult(1,0,0,3);
        } else {
            resultTeam1.setMatchResult(0,0,1,1);
            resultTeam2.setMatchResult(0,0,1,1);
        }
    }

}
