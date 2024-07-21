package com.sbi.oneview.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbi.oneview.R;
import com.sbi.oneview.base.ResponseBaseModel;
import com.sbi.oneview.network.APIRequests;
import com.sbi.oneview.network.NetworkResponseCallback;
import com.sbi.oneview.network.RequestModel.Transit.ResendOtpRequestModel;
import com.sbi.oneview.ui.CallBackListner.OtpDialogueCallBack;
import com.sbi.oneview.ui.registration.ApplyTransitCardActivity;
import com.sbi.oneview.utils.encryption.CipherEncryption;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class OTPVerificationDialog extends Dialog {

    EditText etFirstOTP, etSecondOTP, etThirdOTP, etFourthOTP,et5,et6;
    Button btnVerify;
    TextView tvResendOTP;
    String mobileNUmber,cardReferenceNUmber,action;
    private OtpDialogueCallBack otpDialogueCallBack;

    public OTPVerificationDialog(OtpDialogueCallBack otpDialogueCallBack,@NonNull Context context,String mobileNUmber,String cardReferenceNumber,String action) {
        super(context);
        this.otpDialogueCallBack=otpDialogueCallBack;
        this.mobileNUmber = mobileNUmber;
        this.cardReferenceNUmber = cardReferenceNumber;
        this.action = action;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(getContext().getResources().getColor(R.color.transparent)));

        setContentView(R.layout.dialog_enter_otp);

        etFirstOTP = findViewById(R.id.et1);
        etSecondOTP = findViewById(R.id.et2);
        etThirdOTP = findViewById(R.id.et3);
        etFourthOTP = findViewById(R.id.et4);
        btnVerify = findViewById(R.id.btnVerify);
        tvResendOTP = findViewById(R.id.tvResendOtp);

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String otpTxt = etFirstOTP.getText().toString()+ etSecondOTP.getText().toString()+ etThirdOTP.getText().toString()+ etFourthOTP.getText().toString();
                if (otpTxt.length()==4)
                {
                    otpDialogueCallBack.onVerifyClick(otpTxt);
                    //CommonUtils.showSuccessDialogue(getContext());
                }
                else {
                    // Toast.makeText(getContext(), ""+R.string.enter_otp, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), "Please enter otp to proceed further", Toast.LENGTH_SHORT).show();
                }

            }
        });

        CommonUtils.startTimer(tvResendOTP);
        tvResendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvResendOTP.setClickable(false);
                tvResendOTP.setText("sendig OTP, Please wait");

                String randomKey = CommonUtils.generateRandomString();
                System.out.println("Random Key: " + randomKey);

                ResendOtpRequestModel resendOtpRequestModel = new ResendOtpRequestModel();
                resendOtpRequestModel.setAction(action);
                resendOtpRequestModel.setCardRefNumber(cardReferenceNUmber);
                resendOtpRequestModel.setMobileNumber(mobileNUmber);
                resendOtpRequestModel.setSId(action.equals("EFORM") ? "" : "1");

                ObjectMapper om = new ObjectMapper();
                String req = null;
                try {
                    req = om.writeValueAsString(resendOtpRequestModel);
                } catch (JsonProcessingException e) {
                    Log.d("EXCEPTION",""+e.getLocalizedMessage());
                }
                String encryptedMsg = CipherEncryption.encryptMessage(req,randomKey);
                System.out.println("Message : " + encryptedMsg);

                if (NetworkUtils.isNetworkConnected(getContext()))
                {
                    APIRequests.resendOTP(getContext(), encryptedMsg, randomKey, new NetworkResponseCallback<String>() {
                        @Override
                        public void onSuccess(Call<String> call, Response<String> response) {

                            if (response.isSuccessful()){

                                String encryptedResponse = response.body();
                                encryptedResponse = encryptedResponse.replaceAll("^\"|\"$", "");

                                ObjectMapper om = new ObjectMapper();
                                ResponseBaseModel responseBaseModel = null;
                                JsonNode node = (JsonNode) CipherEncryption.decryptMessage(encryptedResponse, randomKey);
                                try {
                                    responseBaseModel = om.treeToValue(node, ResponseBaseModel.class);
                                }catch (Exception e)
                                {
                                    Log.d("EXCEPTION",e.getLocalizedMessage());
                                }

                                if (responseBaseModel != null) {

                                    if (responseBaseModel.getStatusCode()==200) {
                                        Toast.makeText(getContext(), "OTP successfully send to your mobile number", Toast.LENGTH_SHORT).show();
                                        tvResendOTP.setClickable(false);
                                        CommonUtils.startTimer(tvResendOTP);
                                    }
                                }
                            }
                            else{
                                String encryptedResponse ="";
                                try {
                                    encryptedResponse = response.errorBody().string();
                                } catch (IOException e) {
                                    Log.d("EXCEPTION",e.getLocalizedMessage());
                                }
                                encryptedResponse = encryptedResponse.replaceAll("^\"|\"$", "");

                                ObjectMapper om = new ObjectMapper();
                                ResponseBaseModel responseBaseModel = null;
                                JsonNode node = (JsonNode) CipherEncryption.decryptMessage(encryptedResponse, randomKey);
                                try {
                                    responseBaseModel = om.treeToValue(node, ResponseBaseModel.class);
                                }catch (Exception e)
                                {
                                    Log.d("EXCEPTION",e.getLocalizedMessage());
                                }

                                if (responseBaseModel!=null)
                                {
                                    Log.d("MSEF",responseBaseModel.getMessage());
                                    Toast.makeText(getContext(), ""+responseBaseModel.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }

                        }

                        @Override
                        public void onResponseBodyNull(Call<String> call, Response<String> response) {
                            tvResendOTP.setClickable(true);
                            tvResendOTP.setText("Resend OTP");
                        }

                        @Override
                        public void onResponseUnsuccessful(Call<String> call, Response<String> response) {
                            tvResendOTP.setClickable(true);
                            tvResendOTP.setText("Resend OTP");
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            tvResendOTP.setClickable(true);
                            tvResendOTP.setText("Resend OTP");
                        }

                        @Override
                        public void onInternalServerError() {
                            tvResendOTP.setClickable(true);
                            tvResendOTP.setText("Resend OTP");
                        }
                    });

                }else{
                    tvResendOTP.setClickable(true);
                    tvResendOTP.setText("Resend OTP");
                    Toast.makeText(getContext(), getContext().getResources().getString(R.string.noInternet), Toast.LENGTH_SHORT).show();
                }

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
                InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(etNext,InputMethodManager.SHOW_IMPLICIT);

            }
        }, 100);

    }


    private void performBack(EditText etprevious, EditText etCurrent)
    {
        etCurrent.setTransformationMethod(PasswordTransformationMethod.getInstance());
        etprevious.setSelection(etprevious.getText().length());
        etprevious.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
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
}
