package iths.robin.fifaapp.Data.Models;

import java.util.ArrayList;

public class TeamUser implements Comparable<TeamUser>{
    public ArrayList<String> teamsPlayed;
    private String userId;
    private String teamName;
    private int played;
    private int won;
    private int drawn;
    private int lost;
    private int gf;
    private int ga;
    private int points;
    private int gd;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public ArrayList<String> getTeamsPlayed() {
        return teamsPlayed;
    }

    public int getPlayed() {
        return played;
    }

    public int getWon() {
        return won;
    }

    public int getDrawn() {
        return drawn;
    }

    public int getLost() {
        return lost;
    }

    public int getGf() {
        return gf;
    }

    public int getGa() {
        return ga;
    }

    public int getPoints() {
        return points;
    }

    public int getGd() {
        return Math.subtractExact(this.getGf(), this.getGa());
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public TeamUser(int played, int won, int drawn, int lost, int gf, int ga, int points) {
        this.teamsPlayed = new ArrayList<>();
        this.played = played;
        this.won = won;
        this.drawn = drawn;
        this.lost = lost;
        this.gf = gf;
        this.ga = ga;
        this.points = points;
    }

    public TeamUser(String played, String won, String drawn, String lost, String gf, String ga, String points, String teamName) {
        this.teamsPlayed = new ArrayList<>();
        this.played = Integer.parseInt(played);
        this.won = Integer.parseInt(won);
        this.drawn = Integer.parseInt(drawn);
        this.lost = Integer.parseInt(lost);
        this.gf = Integer.parseInt(gf);
        this.ga = Integer.parseInt(ga);
        this.points = Integer.parseInt(points);
        this.teamName = teamName;
    }

    public TeamUser() {
        this.teamsPlayed = new ArrayList<>();
        this.played = 0;
        this.won = 0;
        this.drawn = 0;
        this.lost = 0;
        this.gf = 0;
        this.ga = 0;
        this.points = 0;
    }

    public int comparePoints(TeamUser team1, TeamUser team2) {
        return Integer.compare(team1.getPoints(), team2.getPoints());
    }

    @Override
    public int compareTo(TeamUser team) {
        /*if (team.getPoints() == this.getPoints()) {

        } else {

        }

         */

        return Integer.compare(team.getPoints(), this.getPoints());
                //(this.getPoints() < candidate.getPoints() ? -1 :
                //(this.getPoints() == candidate.getPoints() ? 0 : 1));
    }


}
