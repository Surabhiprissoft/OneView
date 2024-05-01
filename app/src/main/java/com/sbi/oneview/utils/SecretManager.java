package com.sbi.oneview.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import android.content.Context;
import android.util.Log;
import com.sbi.oneview.base.App;

public class SecretManager {

    private static final String SECRETS_FILE = "secrets.properties";
    private static Properties properties = new Properties();

    public static void loadSecrets() {
        try {
            // Load the properties file from the resources directory
            InputStream inputStream = App.getAppContext().getAssets().open(SECRETS_FILE);
            properties.load(inputStream);
        } catch (IOException e) {
            Log.d("EXCEPTION",""+e.getMessage());
        }


    }

    public static String getPrivateKey() {
        return properties.getProperty("private_key");
    }

    public static String getPublicKey() {
        return properties.getProperty("public_key");
    }
    public static String getSecretKey() {
        return properties.getProperty("secret_key");
    }



}
