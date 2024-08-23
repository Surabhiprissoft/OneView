package com.sbi.oneview.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.sbi.oneview.R;
import com.sbi.oneview.base.App;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.Data;

public class SharedConfig {
    private final static String NAME = App.getAppContext().getString(R.string.shared_config_name);
    private final static String IS_LOGIN = App.getAppContext().getString(R.string.islogin);
    private final static String SESSIONMANAGEMENT = App.getAppContext().getString(R.string.sessionmanagement);
    private final static String USERID = App.getAppContext().getString(R.string.userid);
    private final static String PASSWORD = App.getAppContext().getString(R.string.password);
    private static final String KEY_RESPONSE = "response";
    private static final String MOBILE_NUMBER = "mob_number";

    // declare context
    private static Context mContext;
    // singleton
    private static SharedConfig sharedConfig = null;
    private final SharedPreferences preferences;


    public SharedConfig() {
        preferences = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public static SharedConfig getInstance(Context context) {
        if (sharedConfig == null) {
            mContext = context;
            sharedConfig = new SharedConfig();
        }
        return sharedConfig;
    }

    private SharedPreferences.Editor edit() {
        return preferences.edit();
    }

    public boolean isLogin() {
        try {
            return preferences.getBoolean(IS_LOGIN, false);
        }catch (Exception e) {
            Log.d("EXCEPTION",""+e.getMessage());
            return false;
        }
    }

    public void setIsLogin(boolean value) {
        edit().putBoolean(IS_LOGIN, value).commit();
    }

    public String getSessionmanagement() {
        try {
            return preferences.getString(SESSIONMANAGEMENT, "");
        } catch (Exception e) {
            Log.d("EXCEPTION",""+e.getMessage());
            return "";
        }
    }

    public void setSessionmanagement(String value) {
        edit().putString(SESSIONMANAGEMENT, value).commit();
    }


    public String getUserId() {
        try {
            return preferences.getString(USERID, "");
        } catch (Exception e) {
            Log.d("EXCEPTION",""+e.getMessage());
            return "";
        }
    }

    public void setUserId(String value) {
        edit().putString(USERID, value).commit();
    }

    public String getPassword() {
        try {
            return preferences.getString(PASSWORD, "");
        }catch (Exception e) {
            Log.d("EXCEPTION",""+e.getMessage());
            return "";
        }
    }

    public void setPassword(String value) {
        edit().putString(PASSWORD, value).commit();
    }


    public void setMobileNumber(String mobileNumber){
        edit().putString(MOBILE_NUMBER,mobileNumber).commit();
    }

    public String getMobileNumber(){
        try {
            return preferences.getString(MOBILE_NUMBER, "");
        }catch (Exception e) {
            Log.d("EXCEPTION",""+e.getMessage());
            return "";
        }
    }

    public void saveLoginResponse(Context context, Data response) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_RESPONSE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(response);
        editor.putString(KEY_RESPONSE, json);
        editor.apply();
    }

    public Data getLoginResponse(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_RESPONSE, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(KEY_RESPONSE, context.getString(R.string.no_data));
        Gson gson = new Gson();
        return gson.fromJson(json, Data.class);
    }


    public void clear() {
        sharedConfig.setIsLogin(false);
        sharedConfig.setSessionmanagement("");
        sharedConfig.setUserId("");
        sharedConfig.setPassword("");


    }


}
