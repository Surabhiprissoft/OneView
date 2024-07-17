package com.sbi.oneview.ui.registration;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.sbi.oneview.R;

public class SuccessfulRegistrationActivity extends AppCompatActivity {

    MaterialButton btnLoginNow;
    TextView tvCardRefNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_successful_registration_activty);

        Intent intent = getIntent();
        String tempCardRefNumber = intent.getStringExtra("TXNID");

        tvCardRefNumber = findViewById(R.id.tvCardRefNumber);
        btnLoginNow=findViewById(R.id.btnLoginNow);

        if (tempCardRefNumber!=null)
        {
            tvCardRefNumber.setText(tempCardRefNumber);
        }

        btnLoginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SuccessfulRegistrationActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}