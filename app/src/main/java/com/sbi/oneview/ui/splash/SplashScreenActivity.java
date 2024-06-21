package com.sbi.oneview.ui.splash;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.sbi.oneview.R;
import com.sbi.oneview.ui.inrPrepaid.InrPrepaidHomeActivity;
import com.sbi.oneview.ui.registration.LoginActivity;
import com.sbi.oneview.utils.MyAnimation;

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
        Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
       // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finishAffinity();
    }

}