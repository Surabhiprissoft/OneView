package com.sbi.oneview.ui.registration;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.sbi.oneview.R;
import com.sbi.oneview.base.App;
import com.sbi.oneview.base.BaseActivity;
import com.sbi.oneview.base.RequestBaseModel;
import com.sbi.oneview.network.APIRequests;
import com.sbi.oneview.network.NetworkResponseCallback;
import com.sbi.oneview.network.RequestModel.LoginWithOtpRequestModel;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.LoginWithOtpResponseModel;
import com.sbi.oneview.ui.mainDashboard.DashboardCardSelectionActivity;
import com.sbi.oneview.utils.CommonUtils;
import com.sbi.oneview.utils.NetworkUtils;
import com.sbi.oneview.utils.SharedConfig;

import retrofit2.Call;
import retrofit2.Response;

public class EnterOtp extends BaseActivity {

    EditText etFirstOTP, etSecondOTP, etThirdOTP, etFourthOTP;
    MaterialButton btnVerify;
    TextView txtResendOtp,txtPhoneNumber,txtEnterOtp;
    ImageView topRightImg,bottomLeftImg,bottomRightImg;
    String number;
    LinearLayout otpTextViewLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_enter_otp);

        initWidgets();
        clickListners();
        CommonUtils.changeBallPosition(topRightImg,bottomRightImg,bottomLeftImg);
    }




    public void initWidgets(){

        etFirstOTP = findViewById(R.id.et1);
        etSecondOTP = findViewById(R.id.et2);
        etThirdOTP = findViewById(R.id.et3);
        etFourthOTP = findViewById(R.id.et4);
        topRightImg = findViewById(R.id.topRight_image);
        bottomLeftImg = findViewById(R.id.bottomLeft_image);
        bottomRightImg = findViewById(R.id.bottomRight_image);
        btnVerify = findViewById(R.id.btnVerify);
        txtResendOtp = findViewById(R.id.txtResendOtp);
        txtPhoneNumber = findViewById(R.id.txtPhoneNumber);
        otpTextViewLayout = findViewById(R.id.otpTextViewLayout);
        txtEnterOtp = findViewById(R.id.txtEnterOtp);

        Intent intent = getIntent();
        number = intent.getStringExtra("PHONE_NUMBER");
        String maskedNumber = maskPhoneNumber(number);
        txtPhoneNumber.setText(""+maskedNumber);

    }

    public void clickListners(){

        otpTextViewLayout.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                otpTextViewLayout.getViewTreeObserver().removeOnPreDrawListener(this);

                // Get the final position of the LinearLayout
                float finalPositionX = otpTextViewLayout.getX();

                // Set the initial position off the screen to the left
                otpTextViewLayout.setX(-otpTextViewLayout.getWidth());

                // Create the ObjectAnimator to animate the LinearLayout to its final position
                ObjectAnimator animator = ObjectAnimator.ofFloat(otpTextViewLayout, "translationX", finalPositionX);
                animator.setDuration(1000); // Animation duration in milliseconds
                animator.start();

                return true;
            }
        });

        txtEnterOtp.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                txtEnterOtp.getViewTreeObserver().removeOnPreDrawListener(this);

                // Get the final position of the TextView
                float finalPositionX = txtEnterOtp.getX();

                // Set the initial position off the screen to the right
                txtEnterOtp.setX(txtEnterOtp.getWidth() + getResources().getDisplayMetrics().widthPixels);

                // Create the ObjectAnimator to animate the TextView to its final position
                ObjectAnimator animator = ObjectAnimator.ofFloat(txtEnterOtp, "translationX", finalPositionX);
                animator.setDuration(1000); // Animation duration in milliseconds
                animator.start();

                return true;
            }
        });



        etFirstOTP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                Log.d("Called","Called"+s.length());
                if(s.length()==1)
                {

                    performNext(etSecondOTP, etFirstOTP);
                }
                else
                {
                    performBack(etFirstOTP, etFirstOTP);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etSecondOTP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                if(s.length()==1)
                {

                    performNext(etThirdOTP, etSecondOTP);
                }
                else
                {
                    performBack(etFirstOTP, etSecondOTP);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        etThirdOTP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                Log.d("Called","Called"+s.length());
                if(s.length()==1)
                {

                    performNext(etFourthOTP, etThirdOTP);
                }
                else
                {
                    performBack(etSecondOTP, etThirdOTP);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        etFourthOTP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2)
            {

                if(s.length()==1)
                {

                    performNext(etFourthOTP, etFourthOTP);
                }
                else
                {
                    performBack(etThirdOTP, etFourthOTP);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        performNext(etFirstOTP, etFirstOTP);

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String otpTxt = etFirstOTP.getText().toString()+ etSecondOTP.getText().toString()+ etThirdOTP.getText().toString()+ etFourthOTP.getText().toString();
                if (otpTxt.length()==4)
                {
                    /*Intent i=new Intent(EnterOtp.this, DashboardCardSelectionActivity.class);
                    startActivity(i);*/

                    showLoading();

                    //-------------- Login API Integration -----------------------
                    RequestBaseModel<LoginWithOtpRequestModel> data =  new RequestBaseModel<>();
                    LoginWithOtpRequestModel loginWithOtpRequestModel = new LoginWithOtpRequestModel();

                    loginWithOtpRequestModel.setUsername(number);
                    loginWithOtpRequestModel.setOtp("3241");
                    loginWithOtpRequestModel.setSId("");

                    data.setRequest(loginWithOtpRequestModel);
                   // Log.d("REQUEST",""+data.toString());

                    Log.d("REQUEST",loginWithOtpRequestModel.toString());

                    SharedConfig.getInstance(EnterOtp.this).setMobileNumber(number);
                    if(NetworkUtils.isNetworkConnected(EnterOtp.this)){

                        APIRequests.loginWithOTP(EnterOtp.this, loginWithOtpRequestModel, new NetworkResponseCallback<LoginWithOtpResponseModel>() {
                            @Override
                            public void onSuccess(Call<LoginWithOtpResponseModel> call, Response<LoginWithOtpResponseModel> response) {
                                Log.d("MSG","SUCCESS");
                                Toast.makeText(EnterOtp.this, "Success "+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //testEdt.setText("Success");
                                if (response.body().getStatusCode()==200){

                                    hideLoading();
                                    SharedConfig.getInstance(EnterOtp.this).saveLoginResponse(EnterOtp.this,response.body().getData());

                                    Intent i=new Intent(EnterOtp.this, DashboardCardSelectionActivity.class);
                                    startActivity(i);


                                }else{
                                    Toast.makeText(EnterOtp.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onResponseBodyNull(Call<LoginWithOtpResponseModel> call, Response<LoginWithOtpResponseModel> response) {
                                Log.d("MSG","NULL RESPONSE");
                                hideLoading();
                                Toast.makeText(EnterOtp.this, "Null "+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //testEdt.setText("null response "+ response.body().getMessage().toString());
                            }

                            @Override
                            public void onResponseUnsuccessful(Call<LoginWithOtpResponseModel> call, Response<LoginWithOtpResponseModel> response) {
                                Log.d("MSG","UNSUCCESSFULL");
                                hideLoading();
                                Toast.makeText(EnterOtp.this, "Unsuccessfull "+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //testEdt.setText("unSuccessfull"+response.body().getMessage().toString());

                            }

                            @Override
                            public void onFailure(Call<LoginWithOtpResponseModel> call, Throwable t) {
                                Log.d("MSG","On FAILURE"+t.getLocalizedMessage());
                                hideLoading();
                                Toast.makeText(EnterOtp.this, "Failure "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                //testEdt.setText("FAILURE"+t.getLocalizedMessage());
                            }


                            @Override
                            public void onInternalServerError() {
                                Log.d("MSG","INTERNAL SERVER ERROR");
                                hideLoading();
                                Toast.makeText(EnterOtp.this, "INTERNAL SERVER ERROR", Toast.LENGTH_SHORT).show();
                                //testEdt.setText("INTERNAL SERVER ERROR");

                            }
                        });
                    }
                    else{
                        hideLoading();
                        Toast.makeText(EnterOtp.this,  getResources().getString(R.string.noInternet), Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    // Toast.makeText(getContext(), ""+R.string.enter_otp, Toast.LENGTH_SHORT).show();
                    Toast.makeText(App.getAppContext(), App.getAppContext().getString(R.string.enter_otp), Toast.LENGTH_SHORT).show();
                }

            }
        });

        txtResendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                clearData();
            }
        });

    }




    private void performNext(EditText etNext, EditText etCurrent)
    {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                if (etFourthOTP==etCurrent)
                {
                    etCurrent.setSelection(etCurrent.getText().length());
                }  else
                {
                    etNext.requestFocus();
                }
                etCurrent.setTransformationMethod(PasswordTransformationMethod.getInstance());
                etCurrent.setBackground(EnterOtp.this.getDrawable(R.drawable.fill_edit_text_otp));
                InputMethodManager inputMethodManager = (InputMethodManager) EnterOtp.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(etNext,InputMethodManager.SHOW_IMPLICIT);

            }
        }, 100);

    }


    private void performBack(EditText etprevious, EditText etCurrent)
    {
        etCurrent.setTransformationMethod(PasswordTransformationMethod.getInstance());
        etprevious.setSelection(etprevious.getText().length());
        etprevious.requestFocus();
        etCurrent.setBackground(EnterOtp.this.getDrawable(R.drawable.edit_text_otp_style));
        InputMethodManager inputMethodManager = (InputMethodManager) EnterOtp.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(etprevious,InputMethodManager.SHOW_IMPLICIT);
    }



    public void clearData()

    {

        etFirstOTP.setText("");
        etSecondOTP.setText("");
        etThirdOTP.setText("");
        etFourthOTP.setText("");

        performNext(etFirstOTP, etFirstOTP);

    }


    private String maskPhoneNumber(String phoneNumber) {

        if (phoneNumber != null) {
            String lastFourDigits = phoneNumber.substring(phoneNumber.length() - 4);
            return "xxxxxx" + lastFourDigits;
        } else {
            // Handle cases where phone number is less than 10 characters or null
            return phoneNumber; // Or handle as needed
        }
    }

}