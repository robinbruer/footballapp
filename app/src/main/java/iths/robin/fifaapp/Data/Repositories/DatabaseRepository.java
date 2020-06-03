package iths.robin.fifaapp.Data.Repositories;

import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import iths.robin.fifaapp.Data.Firebase.FirestoreSource;
import iths.robin.fifaapp.Data.Models.MatchResult;
import iths.robin.fifaapp.Data.Models.TeamUser;
import iths.robin.fifaapp.Database.DataBaseSource;

public class DatabaseRepository implements DataBaseSource {
    private FirestoreSource firestoreSource;

    public DatabaseRepository() {
        firestoreSource = new FirestoreSource();
    }

    public Task configureMatch() {
        return firestoreSource.configureMatch();
    }

    @Override
    public List<String> getTeamsName() {
        return firestoreSource.getTeamsName();
    }

    @Override
    public List<String> getTeamId() {
        return firestoreSource.getTeamId();
    }

    @Override
    public void creatTeam(TeamUser teamUser) {
        firestoreSource.creatTeam(teamUser);
    }

    @Override
    public Task setLeagueTable() {
        return firestoreSource.setLeagueTable();
    }

    @Override
    public ArrayList<TeamUser> getTeamUserArrayList() {
        return firestoreSource.getTeamUserArrayList();
    }

    @Override
    public void playMatch(TeamUser teamUser1, TeamUser teamUser2, MatchResult resultTeam1,
                          MatchResult resultTeam2, int team1result, int team2result, String team1, String team2) {
        firestoreSource.playMatch(teamUser1,teamUser2,resultTeam1,resultTeam2,team1result,team2result,team1,team2);
    }
}
