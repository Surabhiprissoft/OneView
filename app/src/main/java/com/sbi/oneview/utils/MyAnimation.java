package com.sbi.oneview.utils;

import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.sbi.oneview.R;

public class MyAnimation {

    private static int duration = 700;
    private static int delay = 15;

    public interface AnimationListener {
        void onAnimationEnd();
    }

    public static void animateText(final TextView textView, final String newText, ImageView imglogo, AnimationListener listener) {
        // Animation to move text from left to right
        Animation animToLeft = AnimationUtils.loadAnimation(textView.getContext(), android.R.anim.slide_in_left);
        animToLeft.setDuration(duration); // Set duration as needed
        animToLeft.setFillAfter(true);

        // Animation listener to change text and start animation from right to left
        animToLeft.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(() -> {
                    textView.setText(newText);
                    animateTextFromRight(textView, imglogo, listener);
                }, delay);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        textView.startAnimation(animToLeft);
    }

    private static void animateTextFromRight(final TextView textView, ImageView imglogo, AnimationListener listener) {
        // Animation to move text from right to left
        Animation animToRight = new TranslateAnimation(1000, 0, 0, 0);
        animToRight.setDuration(duration); // Set duration as needed
        animToRight.setFillAfter(true);

        animToRight.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                textView.setText(R.string.one_view);
                animateTextFromTop(textView, imglogo, listener);
                animateTextFromBottom(imglogo, listener);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        textView.startAnimation(animToRight);
    }

    private static void animateTextFromTop(final TextView textView, ImageView imglogo, AnimationListener listener) {
        // Animation to move text from top to original position
        Animation animFromTop = new TranslateAnimation(0, 0, -1000, 0);
        animFromTop.setDuration(duration); // Set duration to 2000 milliseconds (2 seconds)
        animFromTop.setFillAfter(true);

        animFromTop.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                // Notify the listener
                if (listener != null) {
                    listener.onAnimationEnd();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        textView.startAnimation(animFromTop);
    }

    private static void animateTextFromBottom(ImageView imglogo, AnimationListener listener) {
        // Animation to move text from top to original position
        imglogo.setVisibility(View.VISIBLE);
        Animation animFromBottom = AnimationUtils.loadAnimation(imglogo.getContext(), android.R.anim.slide_in_left);
        animFromBottom.setDuration(duration); // Set duration to 2000 milliseconds (2 seconds)
        animFromBottom.setFillAfter(true);

        animFromBottom.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                // Notify the listener
                if (listener != null) {
                    listener.onAnimationEnd();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        imglogo.startAnimation(animFromBottom);
    }
}





