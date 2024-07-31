package com.sbi.oneview.ui.paymentStatus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.sbi.oneview.R;
import com.sbi.oneview.ui.transitScreen.TransitHomeActivity;

public class PaymentStatusActivity extends AppCompatActivity {

    ImageView imgStatus;
    TextView tvStatus,tvStatusDesc,tvSuccessNote;
    MaterialButton btnDashboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_status);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imgStatus = findViewById(R.id.imgStatus);
        tvStatus = findViewById(R.id.tvStatus);
        tvStatusDesc = findViewById(R.id.tvStatusDesc);
        tvSuccessNote = findViewById(R.id.successNote);
        btnDashboard = findViewById(R.id.btnDashboard);


        String status = getIntent().getStringExtra("status");

        if (status!=null)
        {

            if (status.equals("s"))
            {
                imgStatus.setImageDrawable(getDrawable(R.drawable.ic_success));
                tvStatus.setText("Successful");
                tvStatusDesc.setText("Congratulation, Your transaction has been completed successfully.");

            }else{
                imgStatus.setImageDrawable(getDrawable(R.drawable.again_transaction));
                tvStatus.setText("Failed");
                tvStatusDesc.setText("Your topup transaction has been failed. Please try making another payment.");

            }


        }


        btnDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PaymentStatusActivity.this, TransitHomeActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }
}