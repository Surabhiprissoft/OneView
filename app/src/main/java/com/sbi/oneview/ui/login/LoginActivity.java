package com.sbi.oneview.ui.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.sbi.oneview.R;
import com.sbi.oneview.ui.otp.EnterOtp;
import com.sbi.oneview.ui.registration.ApplyTransitCardActivity;
import com.sbi.oneview.utils.CommonUtils;

public class LoginActivity extends AppCompatActivity {

    EditText etPhoneNumber;
    MaterialButton btnRequestOtp;
    ImageView topRightImg,bottomLeftImg,bottomRightImg;
    TextView txt_applyforcard;
    boolean isNumberValid=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /* requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

        setContentView(R.layout.activity_login);



        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        btnRequestOtp = findViewById(R.id.btnRequestOtp);
        topRightImg = findViewById(R.id.topRight_image);
        bottomLeftImg = findViewById(R.id.bottomLeft_image);
        bottomRightImg = findViewById(R.id.bottomRight_image);
        txt_applyforcard = findViewById(R.id.txt_applyforcard);


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