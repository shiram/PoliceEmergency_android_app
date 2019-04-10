package com.ugpolice.hunter.policeemergency;

import android.content.Context;
import android.content.SharedPreferences;

public class DataStore {
    private Context context;

    public DataStore(Context context) {
        this.context = context;
    }

    private SharedPreferences sharedPreferences;

    public void setPrefs(int _id, String _firstname, String _lastname, String _phone, String _user_token){

        sharedPreferences = context.getSharedPreferences(Config.MYPREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("session_id", _id);
        editor.putString("session_firstname", _firstname);
        editor.putString("session_lastname", _lastname);
        editor.putString("session_phone", _phone);
        editor.putString("user_token", _user_token);
        editor.apply();

    }

    public void setPrefs(int _id, String _firstname, String _lastname, String _phone, String _email, int _access_level){

        sharedPreferences = context.getSharedPreferences(Config.MYPREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("session_id", _id);
        editor.putString("session_firstname", _firstname);
        editor.putString("session_lastname", _lastname);
        editor.putString("session_phone", _phone);
        editor.putString("session_email", _email);
        editor.putInt("access_level", _access_level);
        editor.apply();

    }

    public void deletePrefs(){
        sharedPreferences = context.getSharedPreferences(Config.MYPREFERENCES, Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }

    public int getUserId(){
        int user_id;
        sharedPreferences = context.getSharedPreferences(Config.MYPREFERENCES, context.MODE_PRIVATE);
        user_id = sharedPreferences.getInt("session_id", 0);

        return user_id;
    }

    public String getUserNames(){
        String firstname, lastname;
        sharedPreferences = context.getSharedPreferences(Config.MYPREFERENCES, context.MODE_PRIVATE);
        firstname = sharedPreferences.getString("session_firstname", null);
        lastname = sharedPreferences.getString("session_lastname", null);

        return firstname+" "+lastname;
    }

    public String getUserPhone(){
        String phone;
        sharedPreferences = context.getSharedPreferences(Config.MYPREFERENCES, context.MODE_PRIVATE);
        phone = sharedPreferences.getString("session_phone", null);

        return phone;
    }

    public String getUserToken(){
        String user_token;
        sharedPreferences = context.getSharedPreferences(Config.MYPREFERENCES, context.MODE_PRIVATE);
        user_token = sharedPreferences.getString("user_token", null);

        return user_token;
    }

    public String getUserEmail(){

        String email;
        sharedPreferences = context.getSharedPreferences(Config.MYPREFERENCES, context.MODE_PRIVATE);
        email = sharedPreferences.getString("session_email", null);

        return email;
    }

    public int getAccessLevel(){
        int access_level;
        sharedPreferences = context.getSharedPreferences(Config.MYPREFERENCES, context.MODE_PRIVATE);
        access_level = sharedPreferences.getInt("access_level", 0);

        return access_level;
    }
}
