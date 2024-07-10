package com.sbi.oneview.ui.registration;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.sbi.oneview.R;
import com.sbi.oneview.base.BaseActivity;
import com.sbi.oneview.base.HeaderRequestModel;
import com.sbi.oneview.base.RequestBaseModel;
import com.sbi.oneview.base.ResponseBaseModel;
import com.sbi.oneview.base.SampleRequestBaseModel;
import com.sbi.oneview.network.APIRequests;
import com.sbi.oneview.network.NetworkResponseCallback;
import com.sbi.oneview.network.RequestModel.LoginWithOtpRequestModel;
import com.sbi.oneview.network.RequestModel.ValidateCaptchaRequestModel;
import com.sbi.oneview.network.ResponseModel.GetCaptcha.GetCaptchaResponseModel;
import com.sbi.oneview.network.ResponseModel.ValidateCaptcha.ValidateCaptchaResponseModel;
import com.sbi.oneview.utils.CommonUtils;
import com.sbi.oneview.utils.NetworkUtils;
import com.sbi.oneview.utils.encryption.CipherEncryption;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    EditText etPhoneNumber,etCaptcha;
    MaterialButton btnRequestOtp;
    int currentImageId;
    ImageView topRightImg,bottomLeftImg,bottomRightImg;
    ImageView imgCaptcha,imgRefreshCaptcha;
    TextView txt_applyforcard;
    boolean isNumberValid=false;
    ConstraintLayout contentLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

       /* requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

        setContentView(R.layout.activity_login);



        initWidgets();
        clickListners();

        //-------------------- animating all widgets on login screen -----------------------
        CommonUtils.changeBallPositionWithTime(topRightImg,bottomRightImg,bottomLeftImg,500);
        contentLogin.setVisibility(View.INVISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Make the layout visible
                contentLogin.setVisibility(View.VISIBLE);

                // Create and start the fade-in animation
                ObjectAnimator fadeIn = ObjectAnimator.ofFloat(contentLogin, "alpha", 0f, 1f);
                fadeIn.setDuration(1500); // Animation duration in milliseconds
                fadeIn.start();
            }
        }, 500); // Delay in milliseconds

        //--------------------- calling captcha and displaying ----------------------------
        implementCaptcha();

    }


    public void initWidgets(){
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        btnRequestOtp = findViewById(R.id.btnRequestOtp);
        topRightImg = findViewById(R.id.topRight_image);
        bottomLeftImg = findViewById(R.id.bottomLeft_image);
        bottomRightImg = findViewById(R.id.bottomRight_image);
        txt_applyforcard = findViewById(R.id.txt_applyforcard);
        contentLogin = findViewById(R.id.contentLogin);
        etCaptcha = findViewById(R.id.etCaptcha);
        imgCaptcha = findViewById(R.id.imgCaptcha);
        imgRefreshCaptcha = findViewById(R.id.imgRefreshCaptcha);

    }

    public void clickListners(){

        etPhoneNumber.setFocusable(true);

        etPhoneNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                CommonUtils.changeBallPosition(topRightImg,bottomRightImg,bottomLeftImg);
            }
        });
        etPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtils.changeBallPosition(topRightImg,bottomRightImg,bottomLeftImg);
            }
        });
        imgRefreshCaptcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                implementCaptcha();
            }
        });

        btnRequestOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNumberValid){

                    if (etCaptcha.getText().toString().isEmpty()){
                        Toast.makeText(LoginActivity.this, "Please enter captcha", Toast.LENGTH_SHORT).show();
                    }else{
                        try {
                            validateCaptcha();
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }

                    }





                }
                CommonUtils.changeBallPosition(topRightImg,bottomRightImg,bottomLeftImg);
            }
        });

        etPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not required for this functionality
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Check if entered text has 10 digits
                String phoneNumber = s.toString();
                if (phoneNumber.length() == 10) {
                    isNumberValid=true;
                    btnRequestOtp.setBackgroundColor(Color.WHITE); // Enable button
                } else {
                    isNumberValid=false;
                    btnRequestOtp.setBackgroundColor(getResources().getColor(R.color.disable_button_color)); // Disable button
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                // Not required for this functionality
            }
        });

        txt_applyforcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(LoginActivity.this, ApplyTransitCardActivity.class);
                startActivity(i);
            }
        });

    }


    public void implementCaptcha(){

        showLoading();


        if(NetworkUtils.isNetworkConnected(LoginActivity.this)){
            Toast.makeText(this, "Called captch", Toast.LENGTH_SHORT).show();

            APIRequests.getCaptcha(LoginActivity.this, new NetworkResponseCallback<GetCaptchaResponseModel>() {
                @Override
                public void onSuccess(Call<GetCaptchaResponseModel> call, Response<GetCaptchaResponseModel> response) {

                    hideLoading();
                    Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();

                    if (response.body()!=null) {
                        if (response.body().getStatusCode() == 200) {

                            if (response.body().getData().getImage() != null) {
                                setBase64Image(response.body().getData().getImage());
                            }
                            currentImageId = response.body().getData().getId();


                        } else {

                            Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(LoginActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onResponseBodyNull(Call<GetCaptchaResponseModel> call, Response<GetCaptchaResponseModel> response) {
                    hideLoading();
                    Toast.makeText(LoginActivity.this, "null body", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponseUnsuccessful(Call<GetCaptchaResponseModel> call, Response<GetCaptchaResponseModel> response) {
                    Toast.makeText(LoginActivity.this, "unsuccess", Toast.LENGTH_SHORT).show();
                    hideLoading();

                }

                @Override
                public void onFailure(Call<GetCaptchaResponseModel> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "failure "+ t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    hideLoading();

                }

                @Override
                public void onInternalServerError() {
                    Toast.makeText(LoginActivity.this, "server error", Toast.LENGTH_SHORT).show();
                    hideLoading();

                }
            });

        }else{
            hideLoading();
            Toast.makeText(this, getResources().getString(R.string.noInternet), Toast.LENGTH_SHORT).show();
        }
    }

    public void validateCaptcha() throws JsonProcessingException {

        showLoading();

        String randomKey = CommonUtils.generateRandomString();
        System.out.println("Random Key: " + randomKey);

        ValidateCaptchaRequestModel validateCaptchaRequestModel = new ValidateCaptchaRequestModel();
        validateCaptchaRequestModel.setUsername(etPhoneNumber.getText().toString());
        validateCaptchaRequestModel.setText(etCaptcha.getText().toString().trim());
        validateCaptchaRequestModel.setId(currentImageId);


        ObjectMapper om = new ObjectMapper();
        String req = om.writeValueAsString(validateCaptchaRequestModel);
        String encryptedMsg = CipherEncryption.encryptMessage(req,randomKey);
        System.out.println("Message : " + encryptedMsg);


        if (NetworkUtils.isNetworkConnected(LoginActivity.this)){

            APIRequests.validateCaptcha(LoginActivity.this, encryptedMsg, randomKey, new NetworkResponseCallback<String>() {
                @Override
                public void onSuccess(Call<String> call, Response<String> response) {

                    if (response.isSuccessful()) {
                        String encryptedResponse = response.body();
                        encryptedResponse = encryptedResponse.replaceAll("^\"|\"$", "");

                        //Log.d("RESPONSE", encryptedResponse);

                        ObjectMapper om = new ObjectMapper();
                        ResponseBaseModel responseBaseModel = null;
                        JsonNode node = (JsonNode) CipherEncryption.decryptMessage(encryptedResponse, randomKey);
                        try {
                            responseBaseModel = om.treeToValue(node, ResponseBaseModel.class);
                        }catch (Exception e)
                        {
                            Log.d("EXCEPTION",e.getLocalizedMessage());
                        }


                        if (responseBaseModel!=null){

                            //received successful response, and redirecting to otp screen if code is 200.
                            if (responseBaseModel.getStatusCode()==200){

                                Intent requestOtpIntent = new Intent(LoginActivity.this, EnterOtp.class);
                                String message = etPhoneNumber.getText().toString();
                                requestOtpIntent.putExtra("PHONE_NUMBER", message);
                                startActivity(requestOtpIntent);

                            }
                        }else{

                            Toast.makeText(LoginActivity.this, getResources().getString(R.string.something_went_wrong_please_refresh_captcha), Toast.LENGTH_SHORT).show();
                        }
                    } else {

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
                            Toast.makeText(LoginActivity.this, ""+responseBaseModel.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        // Handle unsuccessful response
                    }

                    hideLoading();
                }

                @Override
                public void onResponseBodyNull(Call<String> call, Response<String> response) {
                    Log.d("MSG","NULL :"+response);
                    hideLoading();

                }

                @Override
                public void onResponseUnsuccessful(Call<String> call, Response<String> response) {
                    Log.d("MSG","UN_SUCCESS :"+response);
                    hideLoading();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.d("MSG","FAILED");
                    hideLoading();
                }

                @Override
                public void onInternalServerError() {
                    Log.d("MSG","SERVER ERROR");
                    hideLoading();
                }
            });

        }else{
            hideLoading();
            Toast.makeText(this, getResources().getString(R.string.noInternet), Toast.LENGTH_SHORT).show();
        }

    }

    public void setBase64Image(String base64String) {
        // Decode Base64 string to byte array
        byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);

        // Convert byte array to Bitmap
        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        // Set the Bitmap to an ImageView
        imgCaptcha.setImageBitmap(decodedBitmap);
    }


}