package edu.udayton.moivedb.login;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xurengui on 11/07/2017.
 */

public class Session {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    public Session(Context context){
        this.context = context;
        preferences = context.getSharedPreferences("MyMovie", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setLoggedin(boolean loggedin){
        editor.putBoolean("loggedInmode", loggedin);
        editor.commit();
    }

    public boolean loggedin(){
        return preferences.getBoolean("loggedInmode", false);
    }

}
