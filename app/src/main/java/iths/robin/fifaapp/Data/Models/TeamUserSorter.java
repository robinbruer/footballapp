package iths.robin.fifaapp.Data.Models;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TeamUserSorter {
    private ArrayList<TeamUser> teamUsers;
    List<Integer> teamGf = new ArrayList<>();
    private String TAG = "TeamUserSorter";

    public TeamUserSorter(ArrayList<TeamUser> teamUsers) {
        this.teamUsers = teamUsers;
    }
    public ArrayList<TeamUser> getSortedTeamUserByPoints() {
        Collections.sort(teamUsers);
        Log.d(TAG, "getSortedTeamUserByPoints: ");

        for (int i = 0; i < teamUsers.size(); i++) {
            for (TeamUser team:teamUsers) {
                teamGf.add(team.getGf());
                if (teamUsers.get(i).getPoints() == team.getPoints()) {

                    Log.d(TAG, "getSortedTeamUserByPoints: should be 4");
                }

            }

        }

        Collections.sort(teamGf);

        return teamUsers;
    }
}
