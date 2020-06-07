package com.example.dell.teamup;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dell on 4/5/2018.
 */

public class DataObject2 {
    @SerializedName("teamname")
    private String eventname;
    public DataObject2(){}
    public DataObject2(String eventname) {
        this.eventname = eventname;
    }
    public String getName() {
        return eventname;
    }
    public void setName(String eventname) {
        this.eventname = eventname;
    }
}