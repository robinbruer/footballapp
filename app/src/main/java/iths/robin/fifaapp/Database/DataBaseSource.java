package iths.robin.fifaapp.Database;

import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import iths.robin.fifaapp.Data.Models.MatchResult;
import iths.robin.fifaapp.Data.Models.TeamUser;

public interface DataBaseSource {

    Task configureMatch();
    List<String> getTeamsName();
    List<String> getTeamId();
    void creatTeam(TeamUser teamUser);
    Task setLeagueTable();
    ArrayList<TeamUser> getTeamUserArrayList();
    void playMatch(TeamUser teamUser1, TeamUser teamUser2, MatchResult resultTeam1,
                   MatchResult resultTeam2, int team1result, int team2result, String team1, String team2);
}
