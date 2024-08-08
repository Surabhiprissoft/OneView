package com.sbi.oneview.ui.registration;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.sbi.oneview.R;

public class SuccessfulRegistrationActivity extends AppCompatActivity {

    MaterialButton btnLoginNow;
    TextView tvCardRefNumber,tvCopy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_successful_registration_activty);

        Intent intent = getIntent();
        String tempCardRefNumber = intent.getStringExtra("TXNID");

        tvCardRefNumber = findViewById(R.id.tvCardRefNumber);
        tvCopy = findViewById(R.id.tvCopy);

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

        tvCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyTextToClipboard();
            }
        });

    }

    private void copyTextToClipboard() {
        String textToCopy = tvCardRefNumber.getText().toString();
        if (!textToCopy.isEmpty()) {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(this.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Copied Text", textToCopy);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "copied card reference number", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Card reference number not available", Toast.LENGTH_SHORT).show();
        }
    }
}