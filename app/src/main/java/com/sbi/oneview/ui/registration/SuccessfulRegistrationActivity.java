package com.sbi.oneview.ui.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.sbi.oneview.R;
import com.sbi.oneview.ui.login.LoginActivity;

public class SuccessfulRegistrationActivity extends AppCompatActivity {

    MaterialButton btnLoginNow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_registration_activty);
        btnLoginNow=findViewById(R.id.btnLoginNow);
        btnLoginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SuccessfulRegistrationActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}