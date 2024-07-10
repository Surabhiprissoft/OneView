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


    public static void transitMiniStatement(
            Context context,
            TransitMiniStatementRequestModel transitMiniStatementRequestModel,
            NetworkResponseCallback<TransitMiniStatementResponseModel> callback
    ){
        App.apiClientencrypt.getTransitMiniStatement(transitMiniStatementRequestModel).enqueue(new Callback<TransitMiniStatementResponseModel>() {
            @Override
            public void onResponse(Call<TransitMiniStatementResponseModel> call, Response<TransitMiniStatementResponseModel> response) {
                callback.onSuccess(call,response);
            }

            @Override
            public void onFailure(Call<TransitMiniStatementResponseModel> call, Throwable t) {
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
            LimitEnquiryRequestModel limitEnquiryRequestModel,
            NetworkResponseCallback<InrLimitEnquiryResponseModel> callback
    ){
        App.apiClientencrypt.getInrLimitEnquiry(limitEnquiryRequestModel).enqueue(new Callback<InrLimitEnquiryResponseModel>() {
            @Override
            public void onResponse(Call<InrLimitEnquiryResponseModel> call, Response<InrLimitEnquiryResponseModel> response) {
                callback.onSuccess(call,response);
            }

            @Override
            public void onFailure(Call<InrLimitEnquiryResponseModel> call, Throwable t) {
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

}

