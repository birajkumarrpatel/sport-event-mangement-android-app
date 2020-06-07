package com.example.dell.teamup.constructor_models;

/**
 * Created by Dell on 4/11/2018.
 */

public class Product6 {
    private String eventname,address,date,sport,image;

    public Product6(String eventnanme,String address,String date,String sport,String image) {
       this.eventname = eventnanme;
       this.address = address;
       this.date = date;
       this.sport = sport;
       this.image = image;

    }

   public String getEventname()
   {
       return eventname;
   }
   public String getAddress()
   {
       return address;
   }
   public String getDate()
   {
       return date;
   }
   public String getSport()
   {
       return sport;
   }
   public String getImage()
   {
       return image;
   }
}
