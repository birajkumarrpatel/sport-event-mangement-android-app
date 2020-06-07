package com.example.dell.teamup;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dell on 4/2/2018.
 */

public class DataObject1 {
    @SerializedName("location")
    private String eventname;
    public DataObject1(){}
    public DataObject1(String eventname) {
        this.eventname = eventname;
    }
    public String getName() {
        return eventname;
    }
    public void setName(String eventname) {
        this.eventname = eventname;
    }
}
