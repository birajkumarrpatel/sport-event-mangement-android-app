package com.example.dell.teamup;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Dell on 3/31/2018.
 */

public class Session1 {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public Session1(Context ctx){
        this.ctx=ctx;
        prefs=ctx.getSharedPreferences("myapp",Context.MODE_PRIVATE);
        editor=prefs.edit();
    }

    public void setLoggedin(boolean logggedin){
        editor.putBoolean("loggedinmode",logggedin);
        editor.commit();
    }

    public boolean loggedin(){
        return prefs.getBoolean("loggedinmode",false);
    }

    public  void logout()
    {
        setLoggedin(false);
        prefs.edit().clear().commit();
    }
}
