package com.ulutsoft.mamtil;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by NURLAN on 20.02.2016.
 */

public class App extends Application {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate() {
        super.onCreate();

        sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if(sharedPreferences.getString("lang","").isEmpty()) {
            editor = sharedPreferences.edit();
            editor.putString("lang", "ru");
            editor.commit();
        }
    }

    public String getLang(){
        return sharedPreferences.getString("lang","ru");
    }

    public void setLang(String lang) {
        editor.putString("lang", lang);
        editor.commit();
    }
}
