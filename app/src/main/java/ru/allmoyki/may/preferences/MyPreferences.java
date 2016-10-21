package ru.allmoyki.may.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Dima on 03.04.2015.
 */
public class MyPreferences {

    //    PUSH
    public static final String PUSH_PREFERENCE = "PUSH_TOKEN";
    public static final String PUSH_TOKEN_PREFERENCE = "push_token";
    public static final String SENDER_ID = "47995949447";
    public static final String ACTION_ON_REGISTERED = "com.sqisland.android.gcm_client.ON_REGISTERED";
    public static final String FIELD_REGISTRATION_ID = "registration_id";

    public static void seUserPushToken(Context context, String push_token) {
        SharedPreferences preferences = context.getSharedPreferences("PUSH_TOKEN",
                android.content.Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("push_token", push_token);
        editor.commit();
    }


    public static String getUserPushToken(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("PUSH_TOKEN",
                android.content.Context.MODE_PRIVATE);
        return preferences.getString("push_token", null);
    }
    public static void setUser(Context context, String user) {
        SharedPreferences preferences = context.getSharedPreferences("USER",
                android.content.Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user", user);
        editor.commit();
    }

    public static String getUser(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("USER",
                android.content.Context.MODE_PRIVATE);
        return preferences.getString("user", "");
    }

    public static void setRegion(Context context, String region) {
        SharedPreferences preferences = context.getSharedPreferences("REGION",
                android.content.Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("region", region);
        editor.commit();
    }

    public static String getRegion(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("REGION",
                android.content.Context.MODE_PRIVATE);
        return preferences.getString("region", null);
    }
    public static void setAutoLogin(Context context, boolean value) {
        SharedPreferences preferences = context.getSharedPreferences("AUTO_LOGIN",
                android.content.Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("AUTO_LOGIN", value);
        editor.commit();
    }

    public static boolean getAutoLogin(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("AUTO_LOGIN",
                android.content.Context.MODE_PRIVATE);
        return preferences.getBoolean("AUTO_LOGIN",true);
    }

    public static void setAutoCar(Context context, boolean value) {
        SharedPreferences preferences = context.getSharedPreferences("AUTO_CAR",
                android.content.Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("AUTO_CAR", value);
        editor.commit();
    }

    public static boolean getAutoCar(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("AUTO_CAR",
                android.content.Context.MODE_PRIVATE);
        return preferences.getBoolean("AUTO_CAR",true);
    }

    public static void setAutoServices(Context context, boolean value) {
        SharedPreferences preferences = context.getSharedPreferences("AUTO_SERVICES",
                android.content.Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("AUTO_SERVICES", value);
        editor.commit();
    }

    public static boolean getAutoServices(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("AUTO_SERVICES",
                android.content.Context.MODE_PRIVATE);
        return preferences.getBoolean("AUTO_SERVICES",true);
    }



}
