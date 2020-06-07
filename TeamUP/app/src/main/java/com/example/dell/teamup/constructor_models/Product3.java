package com.example.dell.teamup.constructor_models;

/**
 * Created by Dell on 4/6/2018.
 */

public class Product3 {
   private String team1,team2,date,time,venue,description;

    public Product3(String team1, String team2,String date,String time,String venue,String description) {
        this.team1=team1;
        this.team2=team2;
        this.date=date;
        this.time=time;
        this.venue=venue;
        this.description = description;
    }

   public String getTeam1()
   {
       return team1;
   }
   public String getTeam2()
   {
       return team2;
   }
   public String getDate()
   {
       return date;
   }
   public String getTime()
   {
       return time;
   }
   public String getvenue()
   {
       return venue;
   }
   public String getDescription()
   {
       return description;
   }

}
