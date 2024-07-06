package com.sbi.oneview.network;

import android.content.Context;


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
            ValidateCaptchaRequestModel validateCaptchaRequestModel,
            NetworkResponseCallback<ValidateCaptchaResponseModel> callback
    ){
        App.apiClientencrypt.ValidateCaptcha(validateCaptchaRequestModel).enqueue(new Callback<ValidateCaptchaResponseModel>() {
            @Override
            public void onResponse(Call<ValidateCaptchaResponseModel> call, Response<ValidateCaptchaResponseModel> response) {
                callback.onSuccess(call,response);
            }

            @Override
            public void onFailure(Call<ValidateCaptchaResponseModel> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }


    public static void loginWithOTP(
            Context context,
            String loginWithOtpRequestModel,
            NetworkResponseCallback<String> callback
    ){
        App.apiClientencrypt.loginUserWithOtp(loginWithOtpRequestModel).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(call, response);
                }
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
            CardMiniStatementRequestModel cardMiniStatementRequestModel,
            NetworkResponseCallback<CardMiniStatementResponseModel> callback
    ){
        App.apiClientencrypt.getCardMiniStatement(cardMiniStatementRequestModel).enqueue(new Callback<CardMiniStatementResponseModel>() {
            @Override
            public void onResponse(Call<CardMiniStatementResponseModel> call, Response<CardMiniStatementResponseModel> response) {
                callback.onSuccess(call,response);
            }

            @Override
            public void onFailure(Call<CardMiniStatementResponseModel> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }


    public static void cardStatement(
            Context context,
            InrCardStatementRequestModel inrCardStatementRequestModel,
            NetworkResponseCallback<InrCardStatementResponseModel> callback
    ){
        App.apiClientencrypt.getCardStatement(inrCardStatementRequestModel).enqueue(new Callback<InrCardStatementResponseModel>() {
            @Override
            public void onResponse(Call<InrCardStatementResponseModel> call, Response<InrCardStatementResponseModel> response) {
                callback.onSuccess(call,response);
            }

            @Override
            public void onFailure(Call<InrCardStatementResponseModel> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }


    public static void CardBlock(
            Context context,
            CardBlockUnblockRequestModel cardBlockUnblockRequestModel,
            NetworkResponseCallback<CardBlockUnblockResponseModel> callback
    ){
        App.apiClientencrypt.getCardBlock(cardBlockUnblockRequestModel).enqueue(new Callback<CardBlockUnblockResponseModel>() {
            @Override
            public void onResponse(Call<CardBlockUnblockResponseModel> call, Response<CardBlockUnblockResponseModel> response) {
                callback.onSuccess(call,response);
            }

            @Override
            public void onFailure(Call<CardBlockUnblockResponseModel> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }



    public static void CardUnBlock(
            Context context,
            CardBlockUnblockRequestModel cardBlockUnblockRequestModel,
            NetworkResponseCallback<CardBlockUnblockResponseModel> callback
    ){
        App.apiClientencrypt.getCardUnBlock(cardBlockUnblockRequestModel).enqueue(new Callback<CardBlockUnblockResponseModel>() {
            @Override
            public void onResponse(Call<CardBlockUnblockResponseModel> call, Response<CardBlockUnblockResponseModel> response) {
                callback.onSuccess(call,response);
            }

            @Override
            public void onFailure(Call<CardBlockUnblockResponseModel> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }



    public static void CardHotlist(
            Context context,
            CardHotlistRequestModel cardHotlistRequestModel,
            NetworkResponseCallback<CardHotlistResponseModel> callback
    ){
        App.apiClientencrypt.getCardHotlist(cardHotlistRequestModel).enqueue(new Callback<CardHotlistResponseModel>() {
            @Override
            public void onResponse(Call<CardHotlistResponseModel> call, Response<CardHotlistResponseModel> response) {
                callback.onSuccess(call,response);
            }

            @Override
            public void onFailure(Call<CardHotlistResponseModel> call, Throwable t) {
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
            SetPinRequestModel setPinRequestModel,
            NetworkResponseCallback<SetPinResponseModel> callback
    ){
        App.apiClientencrypt.getSetPin(setPinRequestModel).enqueue(new Callback<SetPinResponseModel>() {
            @Override
            public void onResponse(Call<SetPinResponseModel> call, Response<SetPinResponseModel> response) {
                callback.onSuccess(call,response);
            }

            @Override
            public void onFailure(Call<SetPinResponseModel> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

}

