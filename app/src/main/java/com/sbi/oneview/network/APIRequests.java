package com.sbi.oneview.network;

import android.content.Context;
import android.util.Log;


import com.sbi.oneview.base.App;
import com.sbi.oneview.network.RequestModel.CardBlockUnblockRequestModel;
import com.sbi.oneview.network.RequestModel.CardHotlistRequestModel;
import com.sbi.oneview.network.RequestModel.CardMiniStatementRequestModel;
import com.sbi.oneview.network.RequestModel.InrCardStatementRequestModel;
import com.sbi.oneview.network.RequestModel.LimitEnquiryRequestModel;
import com.sbi.oneview.network.RequestModel.LoginWithOtpRequestModel;
import com.sbi.oneview.network.RequestModel.SetPinRequestModel;
import com.sbi.oneview.network.RequestModel.TransitMiniStatementRequestModel;
import com.sbi.oneview.network.RequestModel.ValidateCaptchaRequestModel;
import com.sbi.oneview.network.ResponseModel.BlockUnblockCard.CardBlockUnblockResponseModel;
import com.sbi.oneview.network.ResponseModel.GetCaptcha.GetCaptchaResponseModel;
import com.sbi.oneview.network.ResponseModel.HotlistCard.CardHotlistResponseModel;
import com.sbi.oneview.network.ResponseModel.InrCardStatement.InrCardStatementResponseModel;
import com.sbi.oneview.network.ResponseModel.InrLimitEnquiry.InrLimitEnquiryResponseModel;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.LoginWithOtpResponseModel;
import com.sbi.oneview.network.ResponseModel.MiniStatement.CardMiniStatementResponseModel;
import com.sbi.oneview.network.ResponseModel.SetPin.SetPinResponseModel;
import com.sbi.oneview.network.ResponseModel.TransitMiniStatement.TransitMiniStatementResponseModel;
import com.sbi.oneview.network.ResponseModel.ValidateCaptcha.ValidateCaptchaResponseModel;
import com.sbi.oneview.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIRequests {

    public static void getCaptcha(
            Context context,
            NetworkResponseCallback<GetCaptchaResponseModel> callback
    ){
        App.apiClientencrypt.getCaptcha().enqueue(new Callback<GetCaptchaResponseModel>() {
            @Override
            public void onResponse(Call<GetCaptchaResponseModel> call, Response<GetCaptchaResponseModel> response) {
                callback.onSuccess(call,response);
            }

            @Override
            public void onFailure(Call<GetCaptchaResponseModel> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }


    public static void validateCaptcha(
            Context context,
            String validateCaptchaRequestModel,
            String accessKey,
            NetworkResponseCallback<String> callback
    ){
        App.apiClientencrypt.ValidateCaptcha(validateCaptchaRequestModel,accessKey, Constants.BASE_URL_HOSTNAME).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.onSuccess(call, response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }


    public static void loginWithOTP(
            Context context,
            String loginWithOtpRequestModel,
            String accessKey,
            NetworkResponseCallback<String> callback
    ){
        App.apiClientencrypt.loginUserWithOtp(loginWithOtpRequestModel,accessKey,Constants.BASE_URL_HOSTNAME).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                    callback.onSuccess(call, response);

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(call,t);

            }
        });
    }




    public static void cardMiniStatement(
            Context context,
            String cardMiniStatementRequestModel,
            String accessKey,
            String token,
            NetworkResponseCallback<String> callback
    ){
        App.apiClientencrypt.getCardMiniStatement(cardMiniStatementRequestModel,"Bearer "+token,accessKey,Constants.BASE_URL_HOSTNAME).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.onSuccess(call,response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }


    public static void cardStatement(
            Context context,
            String inrCardStatementRequestModel,
            String accessKey,
            String token,
            NetworkResponseCallback<String> callback
    ){
        App.apiClientencrypt.getCardStatement(inrCardStatementRequestModel,"Bearer "+token,accessKey,Constants.BASE_URL_HOSTNAME).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.onSuccess(call,response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }


    public static void CardBlock(
            Context context,
            String cardBlockUnblockRequestModel,
            String accessKey,
            String token,
            NetworkResponseCallback<String> callback
    ){
        App.apiClientencrypt.getCardBlock(cardBlockUnblockRequestModel,"Bearer "+token,accessKey,Constants.BASE_URL_HOSTNAME).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.onSuccess(call,response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }



    public static void CardUnBlock(
            Context context,
            String cardBlockUnblockRequestModel,
            String accessKey,
            String token,
            NetworkResponseCallback<String> callback
    ){
        App.apiClientencrypt.getCardUnBlock(cardBlockUnblockRequestModel,"Bearer "+token,accessKey,Constants.BASE_URL_HOSTNAME).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.onSuccess(call,response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }



    public static void CardHotlist(
            Context context,
            String cardHotlistRequestModel,
            String accessKey,
            String token,
            NetworkResponseCallback<String> callback
    ){
        App.apiClientencrypt.getCardHotlist(cardHotlistRequestModel,"Bearer "+token,accessKey,Constants.BASE_URL_HOSTNAME).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.onSuccess(call,response);

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }


    public static void CardLimitEnquiry(
            Context context,
            String limitEnquiryRequestModel,
            String accessKey,
            String token,
            NetworkResponseCallback<String> callback
    ){
        App.apiClientencrypt.getInrLimitEnquiry(limitEnquiryRequestModel,"Bearer "+token,accessKey,Constants.BASE_URL_HOSTNAME).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.onSuccess(call,response);

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(call,t);

            }
        });
    }


    public static void CardLimitUpdate(
            Context context,
            String limitUpdateRequestModel,
            String accessKey,
            String token,
            NetworkResponseCallback<String> callback
    ){
        App.apiClientencrypt.getInrLimitUpdate(limitUpdateRequestModel,"Bearer "+token,accessKey,Constants.BASE_URL_HOSTNAME).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.onSuccess(call,response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }


    public static void SetPin(
            Context context,
            String setPinRequestModel,
            String accessKey,
            String token,
            NetworkResponseCallback<String> callback
    ){
        App.apiClientencrypt.getSetPin(setPinRequestModel,"Bearer "+token,accessKey,Constants.BASE_URL_HOSTNAME).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.onSuccess(call,response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }



    //---------------------------- TRANSIT -------------------------------------------------------

    public static void validateEform(
            Context context,
            String validateEformRequestModel,
            String accessKey,
            NetworkResponseCallback<String> callback
    ){
        App.apiClientencrypt.getValidateEform(validateEformRequestModel,accessKey,Constants.BASE_URL_HOSTNAME).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.onSuccess(call,response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }


    public static void processEform(
            Context context,
            String processEformRequestModel,
            String accessKey,
            NetworkResponseCallback<String> callback
    ){
        App.apiClientencrypt.getProcessEform(processEformRequestModel,accessKey,Constants.BASE_URL_HOSTNAME).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.onSuccess(call,response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }


    public static void transitStatement(
            Context context,
            String transitStatementRequestModel,
            String accessKey,
            String token,
            NetworkResponseCallback<String> callback
    ){
        App.apiClientencrypt.getTransitStatement(transitStatementRequestModel,"Bearer "+token,accessKey,Constants.BASE_URL_HOSTNAME).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.onSuccess(call,response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }


    public static void transitMiniStatement(
            Context context,
            String transitMiniStatementRequestModel,
            String accessKey,
            String token,
            NetworkResponseCallback<String> callback
    ){
        App.apiClientencrypt.getTransitMiniStatement(transitMiniStatementRequestModel,"Bearer "+token,accessKey,Constants.BASE_URL_HOSTNAME).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.onSuccess(call,response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }



    public static void transitRequestHotlist(
            Context context,
            String transitRequestHotlistRequestModel,
            String accessKey,
            String token,
            NetworkResponseCallback<String> callback
    ){
        App.apiClientencrypt.getTransitHotlistRequest(transitRequestHotlistRequestModel,"Bearer "+token,accessKey,Constants.BASE_URL_HOSTNAME).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.onSuccess(call,response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }


    public static void transitProcessHotlist(
            Context context,
            String transitProcessHotlistRequestModel,
            String accessKey,
            String token,
            NetworkResponseCallback<String> callback
    ){
        App.apiClientencrypt.getTransitHotlistProcess(transitProcessHotlistRequestModel,"Bearer "+token,accessKey,Constants.BASE_URL_HOSTNAME).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.onSuccess(call,response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }


    public static void resendOTP(
            Context context,
            String resendOtpRequestModel,
            String accessKey,
            String token,
            NetworkResponseCallback<String> callback
    ){
        App.apiClientencrypt.getTransitHotlistProcess(resendOtpRequestModel,"Bearer "+token,accessKey,Constants.BASE_URL_HOSTNAME).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.onSuccess(call,response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

}

