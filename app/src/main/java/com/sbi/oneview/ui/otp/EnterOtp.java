package com.sbi.oneview.ui.otp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.sbi.oneview.R;
import com.sbi.oneview.base.App;
import com.sbi.oneview.ui.DashboardActivity;

public class EnterOtp extends AppCompatActivity {

    EditText etFirstOTP, etSecondOTP, etThirdOTP, etFourthOTP;
    MaterialButton btnVerify;
    TextView txtResendOtp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_enter_otp);


        etFirstOTP = findViewById(R.id.et1);
        etSecondOTP = findViewById(R.id.et2);
        etThirdOTP = findViewById(R.id.et3);
        etFourthOTP = findViewById(R.id.et4);
        btnVerify = findViewById(R.id.btnVerify);
        txtResendOtp = findViewById(R.id.txtResendOtp);


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
                    Intent i=new Intent(EnterOtp.this, DashboardActivity.class);
                    startActivity(i);

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

}