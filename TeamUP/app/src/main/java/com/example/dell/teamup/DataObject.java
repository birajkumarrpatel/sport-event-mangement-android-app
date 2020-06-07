package com.example.dell.teamup;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dell on 4/1/2018.
 */

public class DataObject {
    @SerializedName("eventname")
    private String eventname;
    public DataObject(){}
    public DataObject(String eventname) {
        this.eventname = eventname;
    }
    public String getName() {
        return eventname;
    }
    public void setName(String eventname) {
        this.eventname = eventname;
    }


}
