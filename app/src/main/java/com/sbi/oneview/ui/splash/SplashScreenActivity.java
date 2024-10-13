package com.sbi.oneview.ui.splash;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sbi.oneview.R;
import com.sbi.oneview.ui.inrPrepaid.InrPrepaidHomeActivity;
import com.sbi.oneview.ui.registration.LoginActivity;
import com.sbi.oneview.utils.MyAnimation;

import java.io.File;

public class SplashScreenActivity extends AppCompatActivity  implements MyAnimation.AnimationListener{

    TextView tv_splash;
    ImageView imglogo;
    MyAnimation myAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        tv_splash = findViewById(R.id.tv_splash);
        imglogo = findViewById(R.id.img_logo);


        // Start animation
        MyAnimation.animateText(tv_splash, getString(R.string.multiple_products),imglogo,this);


    }

    @Override
    public void onAnimationEnd() {

        if (checkForRoot())
        {
            finishAffinity();
        }
        if (checkForRootManagementApps())
        {
            finishAffinity();
        }
        if (checkSuCommand())
        {
            finishAffinity();
        }


        Toast.makeText(this, "Congrats Device is not Rooted", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
       // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finishAffinity();
    }

    public Boolean checkForRoot(){
        String[] paths = {
                "/system/app/Superuser.apk",
                "/sbin/su",
                "/system/bin/su",
                "/system/xbin/su",
                "/data/local/xbin/su",
                "/data/local/bin/su",
                "/system/sd/xbin/su",
                "/system/bin/failsafe/su",
                "/data/local/su"
        };

        for (String path : paths) {
            if (new File(path).exists()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkForRootManagementApps() {
        PackageManager pm = getPackageManager();
        String[] rootApps = {"com.noshufou.android.su", "com.thirdparty.superuser", "eu.chainfire.supersu", "com.koushikdutta.superuser", "com.topjohnwu.magisk"};

        for (String app : rootApps) {
            try {
                pm.getPackageInfo(app, 0);
                return true;
            } catch (PackageManager.NameNotFoundException ignored) {}
        }
        return false;
    }

    public boolean checkSuCommand() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("su");
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
    }



}