package com.example.dell.teamup.constructor_models;

/**
 * Created by Dell on 5/8/2018.
 */

public class Product8 {
    private String eventspinner,winningteam,runnerupteam;

    public Product8(String eventspinner,String winningteam,String runnerupteam) {
        this.eventspinner = eventspinner;
        this.winningteam = winningteam;
        this.runnerupteam = runnerupteam;

    }

    public String getEventSpinner() {
        return eventspinner;
    }

    public String getWinningTeam() {
        return winningteam;
    }

    public String getRunnerUp() {
        return runnerupteam;
    }
}
