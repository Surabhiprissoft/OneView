package com.sbi.oneview.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.card.MaterialCardView;
import com.sbi.oneview.R;
import com.sbi.oneview.ui.home.TransitHomeActivity;

public class DashboardActivity extends AppCompatActivity {

    AnimatorSet backAnim,frontAnim;
    boolean isFront = true;

    private ImageView imgTransitFrontRotate, imgTransitBackRotate;
    private ImageView imgInrFrontRotate, imgInrBackRotate;
    private ImageView imgFtcFrontRotate, imgFtcBackRotate;
    private ImageView imgVirtualFrontRotate, imgVirtualBackRotate;
    private Button btnTransitProceed, btnInrProceed, btnFtcProceed, btnVirtualProceed;
    private MaterialCardView transitCardLayout, transitInstructionCardLayout, inrCardLayout, inrInstructionCardLayout;
    private MaterialCardView ftcCardLayout, ftcInstructionCardLayout, virtualCardLayout, virtualInstructionCardLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transit_dash_board);

        imgTransitFrontRotate = findViewById(R.id.img_transit_front_rotate);
        imgTransitBackRotate = findViewById(R.id.img_transit_back_rotate);
        imgInrFrontRotate = findViewById(R.id.img_inr_front_rotate);
        imgInrBackRotate = findViewById(R.id.img_inr_back_rotate);
        imgFtcFrontRotate = findViewById(R.id.img_ftc_front_rotate);
        imgFtcBackRotate = findViewById(R.id.img_ftc_back_rotate);
        imgVirtualFrontRotate = findViewById(R.id.img_virtual_front_rotate);
        imgVirtualBackRotate = findViewById(R.id.img_virtual_back_rotate);

        transitCardLayout = findViewById(R.id.transitCardLayout);
        transitInstructionCardLayout = findViewById(R.id.transitInstructionCardLayout);
        inrCardLayout = findViewById(R.id.inrCardLayout);
        inrInstructionCardLayout = findViewById(R.id.inrInstructionCardLayout);
        ftcCardLayout = findViewById(R.id.ftcCardLayout);
        ftcInstructionCardLayout = findViewById(R.id.ftcInstructionCardLayout);
        virtualCardLayout = findViewById(R.id.virtualCardLayout);
        virtualInstructionCardLayout = findViewById(R.id.virtualInstructionCardLayout);

        // Initializing Buttons
        btnTransitProceed = findViewById(R.id.btn_transit_proceed);
       /* btnInrProceed = findViewById(R.id.btn_inr_proceed);
        btnFtcProceed = findViewById(R.id.btn_ftc_proceed);
        btnVirtualProceed = findViewById(R.id.btn_virtual_proceed);*/

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
                Intent i=new Intent(DashboardActivity.this, TransitHomeActivity.class);
                startActivity(i);
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