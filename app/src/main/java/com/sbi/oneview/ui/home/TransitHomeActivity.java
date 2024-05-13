package com.sbi.oneview.ui.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.card.MaterialCardView;
import com.sbi.oneview.MpinActivity;
import com.sbi.oneview.R;
import com.sbi.oneview.base.App;
import com.sbi.oneview.ui.profile.MyProfileFragment;

public class TransitHomeActivity extends AppCompatActivity {

    private ImageView openIcon;
    private DrawerLayout drawerLayout;

    LinearLayout dashboardLayout,myProfileLayout,viewProfileLayout,editProfileLayout,accountDetailsLayout,cardTopUpLayout,cardStatementLayout,cardhotlistLayout,cardLimitLayout,resetpinLayout,cardBlockUnblockLayout,cardHotListLayout;
    MaterialCardView DashboardCardView,myProfileCardView,cardManagementCardView,contactUsCardView;
    ImageView iconDashboard,iconMyProfile,iconCardManagement,iconContactUs;
    TextView tvDashboard,tvMyProfile,tvCardManagement,tvContactUs;
    MaterialCardView cardTopUpCard,cardStatementCard,cardhotlistCard,cardLimitCard,resetpinCard,cardBlockUnblockCard,cardHotListCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_transit_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        drawerLayout = findViewById(R.id.drawer_layout);
        DashboardCardView = findViewById(R.id.DashboardCardView);
        myProfileCardView = findViewById(R.id.myProfileCardView);
        iconDashboard = findViewById(R.id.iconDashboard);
        iconMyProfile = findViewById(R.id.iconMyProfile);
        iconCardManagement = findViewById(R.id.iconCardManagement);
        iconContactUs = findViewById(R.id.iconContactUs);
        cardManagementCardView = findViewById(R.id.cardManagementCardView);
        contactUsCardView = findViewById(R.id.contactUsCardView);
        tvDashboard = findViewById(R.id.tvDashboard);
        tvMyProfile = findViewById(R.id.tvMyProfile);
        tvCardManagement = findViewById(R.id.tvCardManagement);
        tvContactUs = findViewById(R.id.tvContactUs);
        myProfileLayout = findViewById(R.id.myProfileLayout);
        dashboardLayout = findViewById(R.id.dashboardLayout);
        /*viewProfileLayout = findViewById(R.id.viewProfileLayout);
        editProfileLayout = findViewById(R.id.editProfileLayout);
        accountDetailsLayout = findViewById(R.id.accountDetailsLayout);*/
        cardTopUpLayout = findViewById(R.id.cardTopUpLayout);
        cardStatementLayout = findViewById(R.id.cardStatementLayout);
        cardhotlistLayout = findViewById(R.id.cardhotlistLayout);
        cardLimitLayout = findViewById(R.id.cardLimitLayout);
        resetpinLayout = findViewById(R.id.resetpinLayout);
        cardBlockUnblockLayout = findViewById(R.id.cardBlockUnblockLayout);
        cardHotListLayout = findViewById(R.id.cardHotlistLayout);

        cardTopUpCard = findViewById(R.id.cardTopUpCard);
        cardStatementCard = findViewById(R.id.cardStatementCard);
        cardhotlistCard = findViewById(R.id.cardhotlistCard);
        cardLimitCard = findViewById(R.id.cardlimitCard);
        resetpinCard = findViewById(R.id.resetpincardCard);
        cardBlockUnblockCard = findViewById(R.id.cardBlockUnblockCard);
        cardHotListCard = findViewById(R.id.cardHotListCard);
        /*viewProfileCard = findViewById(R.id.viewProfileCard);
        editProfileCard = findViewById(R.id.editProfileCard);
        accountDetailsCard = findViewById(R.id.accountDetailsCard);*/

        openIcon = findViewById(R.id.openIcon);

        setListners();
        replaceFragment(new TransitCardDashboardFragment());


        openIcon.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        // closeIcon.setOnClickListener(v -> drawerLayout.closeDrawer(GravityCompat.START));


        dashboardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerItemClick("dashboard");
                subMenuClicked(cardTopUpCard,false);
                replaceFragment(new TransitCardDashboardFragment());
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        myProfileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerItemClick("myProfile");
                subMenuClicked(cardTopUpCard,false);
                replaceFragment(new MyProfileFragment());
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        contactUsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerItemClick("contactUs");
                subMenuClicked(cardTopUpCard,false);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

       /* viewProfileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerItemClick("myProfile");
                subMenuClicked(viewProfileCard,true);
                replaceFragment(new ViewProfileFragment());
            }
        });
        editProfileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerItemClick("myProfile");
                subMenuClicked(editProfileCard,true);
            }
        });
        accountDetailsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerItemClick("myProfile");
                subMenuClicked(accountDetailsCard,true);
            }
        });*/


        cardTopUpLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerItemClick("cardManagement");
                subMenuClicked(cardTopUpCard,true);
            }
        });
        cardStatementLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerItemClick("cardManagement");
                //showDialog();
                subMenuClicked(cardStatementCard,true);
            }
        });

        resetpinLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerItemClick("cardManagement");
                showDialog();
                subMenuClicked(cardStatementCard,true);
            }
        });
        cardhotlistLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerItemClick("cardManagement");
                subMenuClicked(cardhotlistCard,true);
            }
        });
        cardLimitLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerItemClick("cardManagement");
                subMenuClicked(cardLimitCard,true);
            }
        });
        cardBlockUnblockLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerItemClick("cardManagement");
                subMenuClicked(cardBlockUnblockCard,true);
            }
        });
        cardHotListLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerItemClick("cardManagement");
                subMenuClicked(cardHotListCard,true);
            }
        });
    }
    void setListners() {

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                handleDrawerOpen();
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                handleDrawerOpen();
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

                handleDrawerClose();
            }

            @Override
            public void onDrawerStateChanged(int newState) {


            }
        });
    }

    private void handleDrawerOpen() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_container); // Assuming your container ID here

        String fragmentName = currentFragment.getClass().getSimpleName();


       /* if (fragmentName.equalsIgnoreCase(getString(R.string.cardissuanceminkycfragment))) {
            CardIssuanceMinKYCFragment fragment = (CardIssuanceMinKYCFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (fragment != null) {
                fragment.hideErrorMessages();
            }
        }

        if (fragmentName.equalsIgnoreCase(getString(R.string.cardissuanceeformfragment))) {
            CardIssuanceEformFragment fragment = (CardIssuanceEformFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (fragment != null) {
                fragment.hideErrorMessages();
            }
        }


        if (fragmentName.equalsIgnoreCase(getString(R.string.cardlinkingfragment))) {
            CardLinkingFragment fragment = (CardLinkingFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (fragment != null) {
                fragment.hideErrorMessages();
            }
        }

        if (fragmentName.equalsIgnoreCase(getString(R.string.cardblockfragment))) {
            CardBlockFragment fragment = (CardBlockFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (fragment != null) {
                fragment.hideErrorMessages();
            }
        }


        if (fragmentName.equalsIgnoreCase(getString(R.string.newcardreplacement))) {
            NewCardReplacement fragment = (NewCardReplacement) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (fragment != null) {
                fragment.hideErrorMessages();
            }
        }*/
    }

    private void handleDrawerClose() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_container); // Assuming your container ID here

        String fragmentName = currentFragment.getClass().getSimpleName();


      /*  if (fragmentName.equalsIgnoreCase("TransitCardDashboardFragment")) {
            TransitCardDashboardFragment fragment = (TransitCardDashboardFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (fragment != null) {
                //fragment.showErrorMessages(); // Hide error messages in the fragment
            }
        }


        if (fragmentName.equalsIgnoreCase(getString(R.string.cardissuanceeformfragment))) {
            CardIssuanceEformFragment fragment = (CardIssuanceEformFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (fragment != null) {
                fragment.showErrorMessages(); // Hide error messages in the fragment
            }
        }


        if (fragmentName.equalsIgnoreCase(getString(R.string.cardlinkingfragment))) {
            CardLinkingFragment fragment = (CardLinkingFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (fragment != null) {
                fragment.showErrorMessages(); // Hide error messages in the fragment
            }
        }

        if (fragmentName.equalsIgnoreCase(getString(R.string.cardblockfragment))) {
            CardBlockFragment fragment = (CardBlockFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (fragment != null) {
                fragment.showErrorMessages(); // Hide error messages in the fragment
            }
        }

        if (fragmentName.equalsIgnoreCase(getString(R.string.newcardreplacement))) {
            NewCardReplacement fragment = (NewCardReplacement) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (fragment != null) {
                fragment.showErrorMessages(); // Hide error messages in the fragment
            }
        }*/
    }

    public void drawerItemClick(String menuName){

        if(menuName.equals("dashboard")){

            DashboardCardView.setCardBackgroundColor(Color.WHITE);
            tvDashboard.setTextColor(Color.BLACK);
            iconDashboard.setImageDrawable(getResources().getDrawable(R.drawable.dashboard_icon));

            myProfileCardView.setCardBackgroundColor(Color.TRANSPARENT);
            myProfileCardView.setStrokeWidth(0);
            tvMyProfile.setTextColor(Color.WHITE);
            iconMyProfile.setImageDrawable(getResources().getDrawable(R.drawable.bw_my_profile_icon));


            cardManagementCardView.setCardBackgroundColor(Color.TRANSPARENT);
            cardManagementCardView.setStrokeWidth(0);
            tvCardManagement.setTextColor(Color.WHITE);
            iconCardManagement.setImageDrawable(getResources().getDrawable(R.drawable.bw_card_manage_icon));


            contactUsCardView.setCardBackgroundColor(Color.TRANSPARENT);
            contactUsCardView.setStrokeWidth(0);
            tvContactUs.setTextColor(Color.WHITE);
            iconContactUs.setImageDrawable(getResources().getDrawable(R.drawable.bw_email));

        }
        else if(menuName.equals("myProfile")){

            DashboardCardView.setCardBackgroundColor(Color.TRANSPARENT);
            DashboardCardView.setStrokeWidth(0);
            tvDashboard.setTextColor(Color.WHITE);
            iconDashboard.setImageDrawable(getResources().getDrawable(R.drawable.bw_dashboard_icon));


            myProfileCardView.setCardBackgroundColor(Color.WHITE);
            tvMyProfile.setTextColor(Color.BLACK);
            iconMyProfile.setImageDrawable(getResources().getDrawable(R.drawable.my_profile_icon));

            cardManagementCardView.setCardBackgroundColor(Color.TRANSPARENT);
            cardManagementCardView.setStrokeWidth(0);
            tvCardManagement.setTextColor(Color.WHITE);
            iconCardManagement.setImageDrawable(getResources().getDrawable(R.drawable.bw_card_manage_icon));


            contactUsCardView.setCardBackgroundColor(Color.TRANSPARENT);
            contactUsCardView.setStrokeWidth(0);
            tvContactUs.setTextColor(Color.WHITE);
            iconContactUs.setImageDrawable(getResources().getDrawable(R.drawable.bw_email));
        }
        else if(menuName.equals("cardManagement")){
            DashboardCardView.setCardBackgroundColor(Color.TRANSPARENT);
            DashboardCardView.setStrokeWidth(0);
            tvDashboard.setTextColor(Color.WHITE);
            iconDashboard.setImageDrawable(getResources().getDrawable(R.drawable.bw_dashboard_icon));

            myProfileCardView.setCardBackgroundColor(Color.TRANSPARENT);
            myProfileCardView.setStrokeWidth(0);
            tvMyProfile.setTextColor(Color.WHITE);
            iconMyProfile.setImageDrawable(getResources().getDrawable(R.drawable.bw_my_profile_icon));


            cardManagementCardView.setCardBackgroundColor(Color.WHITE);
            tvCardManagement.setTextColor(Color.BLACK);
            iconCardManagement.setImageDrawable(getResources().getDrawable(R.drawable.card_manage_icon));

            contactUsCardView.setCardBackgroundColor(Color.TRANSPARENT);
            contactUsCardView.setStrokeWidth(0);
            tvContactUs.setTextColor(Color.WHITE);
            iconContactUs.setImageDrawable(getResources().getDrawable(R.drawable.bw_email));
        }
        else if(menuName.equals("contactUs")){

            DashboardCardView.setCardBackgroundColor(Color.TRANSPARENT);
            DashboardCardView.setStrokeWidth(0);
            tvDashboard.setTextColor(Color.WHITE);
            iconDashboard.setImageDrawable(getResources().getDrawable(R.drawable.bw_dashboard_icon));

            myProfileCardView.setCardBackgroundColor(Color.TRANSPARENT);
            myProfileCardView.setStrokeWidth(0);
            tvMyProfile.setTextColor(Color.WHITE);
            iconMyProfile.setImageDrawable(getResources().getDrawable(R.drawable.bw_my_profile_icon));


            cardManagementCardView.setCardBackgroundColor(Color.TRANSPARENT);
            cardManagementCardView.setStrokeWidth(0);
            tvCardManagement.setTextColor(Color.WHITE);
            iconCardManagement.setImageDrawable(getResources().getDrawable(R.drawable.bw_card_manage_icon));

            contactUsCardView.setCardBackgroundColor(Color.WHITE);
            tvContactUs.setTextColor(Color.BLACK);
            iconContactUs.setImageDrawable(getResources().getDrawable(R.drawable.bw_email));
        }

    }

    public void subMenuClicked(MaterialCardView cardName,boolean isApply){

        /*viewProfileCard.setCardBackgroundColor(Color.TRANSPARENT);
        viewProfileCard.setStrokeWidth(0);

        editProfileCard.setCardBackgroundColor(Color.TRANSPARENT);
        editProfileCard.setStrokeWidth(0);

        accountDetailsCard.setCardBackgroundColor(Color.TRANSPARENT);
        accountDetailsCard.setStrokeWidth(0);*/

        cardTopUpCard.setCardBackgroundColor(Color.TRANSPARENT);
        cardTopUpCard.setStrokeWidth(0);

        cardStatementCard.setCardBackgroundColor(Color.TRANSPARENT);
        cardStatementCard.setStrokeWidth(0);

        cardhotlistCard.setCardBackgroundColor(Color.TRANSPARENT);
        cardhotlistCard.setStrokeWidth(0);

        cardLimitCard.setCardBackgroundColor(Color.TRANSPARENT);
        cardLimitCard.setStrokeWidth(0);

        resetpinCard.setCardBackgroundColor(Color.TRANSPARENT);
        resetpinCard.setStrokeWidth(0);

        cardBlockUnblockCard.setCardBackgroundColor(Color.TRANSPARENT);
        cardBlockUnblockCard.setStrokeWidth(0);

        cardHotListCard.setCardBackgroundColor(Color.TRANSPARENT);
        cardHotListCard.setStrokeWidth(0);

        if(isApply){
            cardName.setCardBackgroundColor(Color.parseColor("#7A477f"));
        }
    }

    public void replaceFragment(Fragment fragment) {

        if (fragment != null) {

            String fragmentName = fragment.getClass().getSimpleName();
            if (fragmentName.equalsIgnoreCase("TransitCardDashboardFragment")) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            } else {

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }

        }
    }

    private void showDialog()
    {
        final Dialog dialog = new Dialog(TransitHomeActivity.this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_reset_pin);
        TextView textViewCountdown = (TextView) dialog.findViewById(R.id.textViewCountdown);

        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, // From X position (relative to self)
                Animation.RELATIVE_TO_SELF, 0.0f, // To X position (relative to self)
                Animation.RELATIVE_TO_SELF, 1.0f, // From Y position (relative to self) - start from the bottom
                Animation.RELATIVE_TO_SELF, 0.5f // To Y position (relative to self) - stop at the center
        );
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                0.5f, 1.0f, // From X scale, To X scale
                0.5f, 1.0f, // From Y scale, To Y scale
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot X (relative to self)
                Animation.RELATIVE_TO_SELF, 0.5f // Pivot Y (relative to self)
        );

// Create AnimationSet to combine TranslateAnimation and ScaleAnimation
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setDuration(1000);
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the TextView with the remaining time
                long secondsRemaining = millisUntilFinished / 1000;

               // textViewCountdown.setAnimation(animationSet);
                textViewCountdown.setText(String.valueOf(secondsRemaining));


            }

            @Override
            public void onFinish() {
                // Countdown timer finished, do something here if needed
                textViewCountdown.setText("0");
                dialog.dismiss();
            }
        }.start();

        dialog.show();
    }
}