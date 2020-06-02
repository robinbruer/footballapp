package iths.robin.fifaapp.Data.Models;

import java.util.HashMap;

public class Matches {

    private String userId;
    private String matchId;
    //public HashMap<String, Object> match = new HashMap<>();

    public Matches(String userId, String matchId) {
        this.userId = userId;
        this.matchId = matchId;
        //this.match = match;
    }
}
