package com.sbi.oneview.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.sbi.oneview.R;
import com.sbi.oneview.utils.MyAnimation;

public class SplashScreenActivity extends AppCompatActivity {

    TextView tv_splash;
    ImageView imglogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        tv_splash = findViewById(R.id.tv_splash);
        imglogo = findViewById(R.id.img_logo);


        // Start animation
        MyAnimation.animateText(tv_splash, getString(R.string.multiple_products),imglogo);
    }
}