package com.sbi.oneview.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.sbi.oneview.R;
import com.sbi.oneview.base.App;

public class SharedConfig {
    private final static String NAME = App.getAppContext().getString(R.string.shared_config_name);
    private final static String IS_LOGIN = App.getAppContext().getString(R.string.islogin);
    private final static String SESSIONMANAGEMENT = App.getAppContext().getString(R.string.sessionmanagement);
    private final static String USERID = App.getAppContext().getString(R.string.userid);
    private final static String PASSWORD = App.getAppContext().getString(R.string.password);

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

    public void clear() {
        sharedConfig.setIsLogin(false);
        sharedConfig.setSessionmanagement("");
        sharedConfig.setUserId("");
        sharedConfig.setPassword("");


    }


}
