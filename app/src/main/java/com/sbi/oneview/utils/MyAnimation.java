package com.sbi.oneview.utils;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.sbi.oneview.R;
import com.sbi.oneview.base.App;
import com.sbi.oneview.ui.home.TransitHomeActivity;

public class MyAnimation {

    private static int duration=2000;
    private static int delay=15;
    public static void animateText(final TextView textView, final String newText, ImageView imglogo) {
        // Animation to move text from left to right

        Animation animToLeft = AnimationUtils.loadAnimation(textView.getContext(), android.R.anim.slide_in_left);

        //Animation animToLeft = new TranslateAnimation(0, -1000, 0, 0);
        animToLeft.setDuration(duration); // Set duration as needed
        animToLeft.setFillAfter(true);

        // Animation listener to change text and start animation from right to left
        animToLeft.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(newText);
                        animateTextFromRight(textView,imglogo);
                    }
                }, delay);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        textView.startAnimation(animToLeft);

        // Start animation

    }

    private static void animateTextFromRight(final TextView textView,ImageView imglogo) {
        // Animation to move text from right to left
        Animation animToRight = new TranslateAnimation(1000, 0, 0, 0);
        animToRight.setDuration(duration); // Set duration as needed
        animToRight.setFillAfter(true);

        // Start animation
        textView.startAnimation(animToRight);

        animToRight.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                textView.setText(R.string.one_view);
                animateTextFromTop(textView,imglogo);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        // Start animation
        textView.startAnimation(animToRight);
    }

    private static void animateTextFromTop(final TextView textView, ImageView imglogo) {
        // Animation to move text from top to original position
        Animation animFromTop = new TranslateAnimation(0, 0, -1000, 0);
        animFromTop.setDuration(duration); // Set duration to 2000 milliseconds (2 seconds)
        animFromTop.setFillAfter(true);

        animFromTop.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

              /*  Intent i = new Intent(App.getAppContext(), TransitHomeActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                App.getAppContext().startActivity(i);*/
                animateTextFromBottom(imglogo);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        // Start animation
        textView.startAnimation(animFromTop);
    }

    private static void animateTextFromBottom(ImageView imglogo) {
        // Animation to move text from top to original position
        imglogo.setVisibility(View.VISIBLE);
        Animation animFromBottom = new TranslateAnimation(0, 0, 1000, 0);
        animFromBottom.setDuration(duration); // Set duration to 2000 milliseconds (2 seconds)
        animFromBottom.setFillAfter(true);

        animFromBottom.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Intent i = new Intent(App.getAppContext(), TransitHomeActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                App.getAppContext().startActivity(i);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        // Start animation
        imglogo.startAnimation(animFromBottom);

        // Start animation
       // textView.startAnimation(animFromTop);
    }
}





