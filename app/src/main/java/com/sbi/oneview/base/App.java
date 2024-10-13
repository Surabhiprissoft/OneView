package com.sbi.oneview.base;

import android.app.Application;
import android.content.Context;

import com.sbi.oneview.network.APIClient;
import com.sbi.oneview.network.APIClientSSL;
import com.sbi.oneview.network.APIInterface;

public class App extends Application {

    private static App instance;

    private static Context appContext;


    public static APIInterface apiClientencrypt;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;


        // for without encryot
        //apiClientencrypt = APIClient.create(APIInterface.class);


        appContext = getApplicationContext();


        // for encrypt
        try {
            apiClientencrypt = APIClientSSL.create(appContext, APIInterface.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

       // SecretManager.loadSecrets();

    }

    public static Context getAppContext() {
        return appContext;
    }
}