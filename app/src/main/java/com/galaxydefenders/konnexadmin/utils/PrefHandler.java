package com.galaxydefenders.konnexadmin.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Rounak
 * For more info visit https://rounak.tech
 **/


public enum PrefHandler {
    INSTANCE;
    public static final String IS_LOGGED_IN = "logged_in";
    private SharedPreferences sharedPreferences = null;

    public void saveLogin(boolean isLoggedIn, Context ctx) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putBoolean(IS_LOGGED_IN, isLoggedIn);
        e.apply();
    }

    public boolean getLogin(Context ctx) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        return sharedPreferences.getBoolean(IS_LOGGED_IN,false);
    }


}
