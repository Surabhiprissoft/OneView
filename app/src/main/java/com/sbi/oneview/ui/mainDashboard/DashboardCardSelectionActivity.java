package com.sbi.oneview.ui.mainDashboard;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.sbi.oneview.R;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.Data;
import com.sbi.oneview.ui.FtcScreen.FtcHomeActivity;
import com.sbi.oneview.ui.inrPrepaid.InrPrepaidHomeActivity;
import com.sbi.oneview.ui.registration.ApplyTransitCardActivity;
import com.sbi.oneview.ui.registration.LoginActivity;
import com.sbi.oneview.ui.transitScreen.TransitHomeActivity;
import com.sbi.oneview.utils.SharedConfig;

public class DashboardCardSelectionActivity extends AppCompatActivity {

    AnimatorSet backAnim,frontAnim;
    boolean isFront = true;

    private ImageView imgTransitFrontRotate, imgTransitBackRotate;
    private ImageView imgInrFrontRotate, imgInrBackRotate;
    private ImageView imgFtcFrontRotate, imgFtcBackRotate;
    private ImageView imgVirtualFrontRotate, imgVirtualBackRotate;
    private Boolean transitStatus=false,inrStatus=false,ftcStatus=false;
    private Button btnTransitProceed, btnInrProceed, btnFtcProceed, btnVirtualProceed;
    private MaterialCardView transitCardLayout, transitInstructionCardLayout, inrCardLayout, inrInstructionCardLayout;
    private MaterialCardView ftcCardLayout, ftcInstructionCardLayout, virtualCardLayout, virtualInstructionCardLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard_card_selection);


        initWidget();
        clickListener();
        manageButtonVisibility();

    }

    public void manageButtonVisibility(){

        Data loginResponse = SharedConfig.getInstance(DashboardCardSelectionActivity.this).getLoginResponse(DashboardCardSelectionActivity.this);

        if (loginResponse!=null){
            if (loginResponse.getTransit()!=null){
                transitStatus=true;
                btnTransitProceed.setText("Proceed");
            }
            else{
                btnTransitProceed.setText("Apply");
            }


            if (loginResponse.getPrepaid()!=null){
                inrStatus=true;
               }
            else{
                btnInrProceed.setBackgroundColor(getResources().getColor(R.color.faintPurple));
                btnInrProceed.setTextColor(getResources().getColor(R.color.faintWhite));

            }

            if (loginResponse.getFtc()!=null){
                ftcStatus=true;

            }else{
                btnFtcProceed.setBackgroundColor(getResources().getColor(R.color.faintPurple));
                btnFtcProceed.setTextColor(getResources().getColor(R.color.faintWhite));
            }

        }
        else {
            Toast.makeText(this, "Something went wrong, Please login again", Toast.LENGTH_SHORT).show();
            Intent loginIntent = new Intent(DashboardCardSelectionActivity.this, LoginActivity.class);
            startActivity(loginIntent);
        }
    }


    public void initWidget(){
        imgTransitFrontRotate = findViewById(R.id.img_transit_front_rotate);
        imgTransitBackRotate = findViewById(R.id.img_transit_back_rotate);
        imgInrFrontRotate = findViewById(R.id.img_inr_front_rotate);
        imgInrBackRotate = findViewById(R.id.img_inr_back_rotate);
        imgFtcFrontRotate = findViewById(R.id.img_ftc_front_rotate);
        imgFtcBackRotate = findViewById(R.id.img_ftc_back_rotate);

        transitCardLayout = findViewById(R.id.transitCardLayout);
        transitInstructionCardLayout = findViewById(R.id.transitInstructionCardLayout);
        inrCardLayout = findViewById(R.id.inrCardLayout);
        inrInstructionCardLayout = findViewById(R.id.inrInstructionCardLayout);
        ftcCardLayout = findViewById(R.id.ftcCardLayout);
        ftcInstructionCardLayout = findViewById(R.id.ftcInstructionCardLayout);


        // Initializing Buttons
        btnTransitProceed = findViewById(R.id.btnTransitProceed);
        btnInrProceed = findViewById(R.id.btnInrPrepaid);
        btnFtcProceed = findViewById(R.id.btnFtcProceed);
       /* btnVirtualProceed = findViewById(R.id.btn_virtual_proceed);*/



    }


    public void clickListener(){
        imgTransitFrontRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                flipcard(transitCardLayout,transitInstructionCardLayout);
            }
        });

        imgTransitBackRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Log.d("TransitDashBoard", "Back image clicked");
                flipcard(transitCardLayout,transitInstructionCardLayout);
            }
        });


        imgInrFrontRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                flipcard(inrCardLayout,inrInstructionCardLayout);
            }
        });

        imgInrBackRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Log.d("TransitDashBoard", "Back image clicked");
                flipcard(inrCardLayout,inrInstructionCardLayout);
            }
        });

        btnTransitProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (transitStatus){
                    Intent i=new Intent(DashboardCardSelectionActivity.this, TransitHomeActivity.class);
                    startActivity(i);
                }else{
                    Intent i=new Intent(DashboardCardSelectionActivity.this, ApplyTransitCardActivity.class);
                    startActivity(i);
                }


            }
        });

        btnInrProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (inrStatus){
                    Intent intent = new Intent(DashboardCardSelectionActivity.this, InrPrepaidHomeActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(DashboardCardSelectionActivity.this, "You don't have any INR prepaid card linked with your mobile number", Toast.LENGTH_SHORT).show();
                }

            }
        });


        btnFtcProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ftcStatus){
                    Intent intent = new Intent(DashboardCardSelectionActivity.this, FtcHomeActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(DashboardCardSelectionActivity.this, "You don't have any FTC card linked with your mobile number", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void flipcard(MaterialCardView frontcard, MaterialCardView backcard)
    {
        float scale = getApplicationContext().getResources().getDisplayMetrics().density;

        frontcard.setCameraDistance(8000 * scale);
        backcard.setCameraDistance(8000 * scale);

        frontAnim = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.front_animator);
        backAnim = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.back_animator);

        if (isFront) {
            frontAnim.setTarget(frontcard);
            backAnim.setTarget(backcard);
            frontAnim.start();
            backAnim.start();
            isFront = false;
        } else {
            frontAnim.setTarget(backcard);
            backAnim.setTarget(frontcard);
            backAnim.start();
            frontAnim.start();
            isFront = true;
        }
    }
}