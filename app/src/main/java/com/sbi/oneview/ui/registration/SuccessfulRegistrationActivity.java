package com.sbi.oneview.ui.registration;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.sbi.oneview.R;

public class SuccessfulRegistrationActivity extends AppCompatActivity {

    MaterialButton btnLoginNow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
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