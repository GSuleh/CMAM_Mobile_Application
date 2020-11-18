package com.example.cmamapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SessionManager(Context context){
        sharedPreferences = context.getSharedPreferences("App Key", 0);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    //set login method
    public void setLogin(Boolean login){
        editor.putBoolean("KEY_LOGIN",login);
        editor.commit();
    }

    //create login method
    public boolean getLogin(){
        return sharedPreferences.getBoolean("KEY_LOGIN",false);
    }

    public void setUsername(String username){
        editor.putString("KEY_USERNAME",username);
        editor.commit();
    }

    //create login method
    public String getUsername(){
        return sharedPreferences.getString("KEY_USERNAME","");
    }
}
