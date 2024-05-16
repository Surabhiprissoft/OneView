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

import com.sbi.oneview.R;

public class OTPVerificationDialog extends Dialog {

    EditText etFirstOTP, etSecondOTP, etThirdOTP, etFourthOTP,et5,et6;
    Button btnVerify;
    TextView tvResendOTP;


    public OTPVerificationDialog(@NonNull Context context) {
        super(context);
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

                    CommonUtils.showSuccessDialogue(getContext());
                }
                else {
                    // Toast.makeText(getContext(), ""+R.string.enter_otp, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), "Please enter otp to proceed further", Toast.LENGTH_SHORT).show();
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
