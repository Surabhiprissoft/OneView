package com.sbi.oneview.ui.transitScreen;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
import com.sbi.oneview.R;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.Data;
import com.sbi.oneview.utils.CommonUtils;
import com.sbi.oneview.utils.SharedConfig;

public class TransitHomeActivity extends AppCompatActivity {

    private ImageView openIcon;
    private DrawerLayout drawerLayout;

    LinearLayout dashboardLayout,myProfileLayout,viewProfileLayout,editProfileLayout,accountDetailsLayout,cardTopUpLayout,cardStatementLayout,cardhotlistLayout,cardLimitLayout,resetpinLayout,cardBlockUnblockLayout,cardHotListLayout;
    MaterialCardView DashboardCardView,myProfileCardView,cardManagementCardView,contactUsCardView;
    ImageView iconDashboard,iconMyProfile,iconCardManagement,iconContactUs;
    TextView tvDashboard,tvMyProfile,tvCardManagement,tvContactUs,tvTransit,tvUserNameChar;
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



        tvTransit = findViewById(R.id.tvTransit);
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
        tvUserNameChar = findViewById(R.id.tvUserNameChar);
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


        //set user firstName and lastName first character

        Data loginResponse = SharedConfig.getInstance(TransitHomeActivity.this).getLoginResponse(TransitHomeActivity.this);
        tvUserNameChar.setText(loginResponse.getTransit().getFirstName().charAt(0)+""+loginResponse.getTransit().getLastName().charAt(0));

        setListners();
        replaceFragment(new TransitCardDashboardFragment());

        CommonUtils.setGradientColor(tvTransit);


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
                replaceFragment(new ContactUsBlankFragment());
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
                replaceFragment(new TopUpFragment());
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
        cardStatementLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerItemClick("cardManagement");
                subMenuClicked(cardStatementCard,true);
                replaceFragment(new CardStatementFragment());
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        resetpinLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerItemClick("cardManagement");
                subMenuClicked(resetpinCard,true);
                replaceFragment(new ResetPinFragment());
                drawerLayout.closeDrawer(GravityCompat.START);
                /*showDialog();
                subMenuClicked(cardStatementCard,true);*/
            }
        });
        cardhotlistLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerItemClick("cardManagement");
                subMenuClicked(cardhotlistCard,true);
                replaceFragment(new CardHotlistFragment());
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
        cardLimitLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerItemClick("cardManagement");
                subMenuClicked(cardLimitCard,true);
                replaceFragment(new CardLimitFragment());
                drawerLayout.closeDrawer(GravityCompat.START);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }


    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.logout_item:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

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


}