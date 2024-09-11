package com.sbi.oneview.ui.paymentStatus;

import android.content.Intent;
import android.graphics.Color;
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
import com.sbi.oneview.utils.CommonUtils;

public class PaymentStatusActivity extends AppCompatActivity {

    ImageView imgStatus;
    TextView tvStatus,tvStatusDesc,tvSuccessNote,successNoteInstruction;
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
        successNoteInstruction = findViewById(R.id.successNoteInstruction);


        String status = getIntent().getStringExtra("status");
        int amount = getIntent().getIntExtra("topupAmount",0);

        if (status!=null)
        {

            if (status.equals("s"))
            {
                imgStatus.setImageDrawable(getDrawable(R.drawable.success_icon));
                tvStatus.setText(this.getString(R.string.cong));
                tvStatus.setTextColor(Color.GREEN);
                //tvStatusDesc.setText(this.getString(R.string.success_transaction));
                tvStatusDesc.setText("Your top up of "+getResources().getString(R.string.Rs)+""+amount+"has been successful");
                tvSuccessNote.setText(""+getResources().getString(R.string.succes_note));
                successNoteInstruction.setText(""+getResources().getString(R.string.success_note_instruction));

            }else if (status.equals("f")){
                imgStatus.setImageDrawable(getDrawable(R.drawable.fail_icon));
                tvStatus.setText(this.getString(R.string.fail));
                tvStatus.setTextColor(Color.RED);
               // tvStatusDesc.setText(this.getString(R.string.failed_transaction));
                tvStatusDesc.setText("Your top up of "+getResources().getString(R.string.Rs)+""+amount+"has been failed");
                tvSuccessNote.setText(""+getResources().getString(R.string.fail_note));

            }
            else{
                imgStatus.setImageDrawable(getDrawable(R.drawable.success_icon));
                tvStatus.setText(this.getString(R.string.success));
                tvStatus.setTextColor(Color.GREEN);
                tvStatusDesc.setText(this.getString(R.string.card_hotlisted));
                tvSuccessNote.setText(""+getResources().getString(R.string.hotlist_note));
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