package iths.robin.fifaapp.Data.Models;

public class MatchResult {

    private int won;
    private int lost;
    private int draw;
    private int points;

    public void setMatchResult(int won, int lost, int draw, int points) {
        this.won = won;
        this.lost = lost;
        this.draw = draw;
        this.points = points;
    }

    public int getWon() {
        return won;
    }

    public int getLost() {
        return lost;
    }

    public int getDraw() {
        return draw;
    }

    public int getPoints() {
        return points;
    }

    public MatchResult() {

    }

}
