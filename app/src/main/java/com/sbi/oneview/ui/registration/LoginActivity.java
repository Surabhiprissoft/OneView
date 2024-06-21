package com.sbi.oneview.ui.registration;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.button.MaterialButton;
import com.sbi.oneview.R;
import com.sbi.oneview.utils.CommonUtils;

public class LoginActivity extends AppCompatActivity {

    EditText etPhoneNumber;
    MaterialButton btnRequestOtp;
    ImageView topRightImg,bottomLeftImg,bottomRightImg;
    TextView txt_applyforcard;
    boolean isNumberValid=false;
    ConstraintLayout contentLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

       /* requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

        setContentView(R.layout.activity_login);



        initWidgets();
        clickListners();

        //-------------------- animating all widgets on login screen -----------------------
        CommonUtils.changeBallPositionWithTime(topRightImg,bottomRightImg,bottomLeftImg,500);
        contentLogin.setVisibility(View.INVISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Make the layout visible
                contentLogin.setVisibility(View.VISIBLE);

                // Create and start the fade-in animation
                ObjectAnimator fadeIn = ObjectAnimator.ofFloat(contentLogin, "alpha", 0f, 1f);
                fadeIn.setDuration(1500); // Animation duration in milliseconds
                fadeIn.start();
            }
        }, 500); // Delay in milliseconds





    }


    public void initWidgets(){
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        btnRequestOtp = findViewById(R.id.btnRequestOtp);
        topRightImg = findViewById(R.id.topRight_image);
        bottomLeftImg = findViewById(R.id.bottomLeft_image);
        bottomRightImg = findViewById(R.id.bottomRight_image);
        txt_applyforcard = findViewById(R.id.txt_applyforcard);
        contentLogin = findViewById(R.id.contentLogin);

    }

    public void clickListners(){

        etPhoneNumber.setFocusable(true);

        etPhoneNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                CommonUtils.changeBallPosition(topRightImg,bottomRightImg,bottomLeftImg);
            }
        });
        etPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtils.changeBallPosition(topRightImg,bottomRightImg,bottomLeftImg);
            }
        });

        btnRequestOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNumberValid){
                    Intent requestOtpIntent = new Intent(LoginActivity.this, EnterOtp.class);
                    String message = etPhoneNumber.getText().toString();
                    requestOtpIntent.putExtra("PHONE_NUMBER", message);
                    startActivity(requestOtpIntent);




                }
                CommonUtils.changeBallPosition(topRightImg,bottomRightImg,bottomLeftImg);
            }
        });

        etPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not required for this functionality
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Check if entered text has 10 digits
                String phoneNumber = s.toString();
                if (phoneNumber.length() == 10) {
                    isNumberValid=true;
                    btnRequestOtp.setBackgroundColor(Color.WHITE); // Enable button
                } else {
                    isNumberValid=false;
                    btnRequestOtp.setBackgroundColor(getResources().getColor(R.color.disable_button_color)); // Disable button
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                // Not required for this functionality
            }
        });

        txt_applyforcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(LoginActivity.this, ApplyTransitCardActivity.class);
                startActivity(i);
            }
        });

    }


}