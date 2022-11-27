package com.berkaysevim.counterapp;

import android.content.Context;
import android.content.SharedPreferences;

import java.security.PublicKey;

public class SharedPref {
    private static SharedPref instance;
    public int counter = 0;
    public int altLimit;
    public int ustLimit;
    public Boolean sound;
    public Boolean titresim;


    private static final String KEY_COUNTER = "COUNTER";
    private static final String KEY_ALTLIMIT = "ALTLIMIT";
    private static final String KEY_USTLIMIT = "USTLIMIT";
    private static final String KEY_SOUND = "SOUND";
    private static final String KEY_TITRESIM = "TITRESIM";


    private SharedPreferences sharedPreferences;
    private SharedPref(Context context)
    {
        sharedPreferences = context.getSharedPreferences(context.getPackageName(),Context.MODE_PRIVATE);
        loadValue();
    }

    public static SharedPref getInstance(Context context)
    {
        if(instance==null)
        {
            instance = new SharedPref(context);
        }
        return instance;
    }
    public void loadValue()
    {
        counter = sharedPreferences.getInt(KEY_COUNTER,10);
        altLimit = sharedPreferences.getInt(KEY_ALTLIMIT,0);
        ustLimit = sharedPreferences.getInt(KEY_USTLIMIT,100);
        sound = sharedPreferences.getBoolean(KEY_SOUND,true);
        sound=sharedPreferences.getBoolean(KEY_TITRESIM,true);
    }
    public void saveValue()
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_COUNTER,counter);
        editor.putInt(KEY_ALTLIMIT,altLimit);
        editor.putInt(KEY_USTLIMIT,ustLimit);
        editor.putBoolean(KEY_SOUND,sound);
        editor.putBoolean(KEY_TITRESIM ,titresim);
        editor.commit();
    }
}
