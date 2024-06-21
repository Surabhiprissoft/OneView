package com.sbi.oneview.network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.sbi.oneview.base.App;
import com.sbi.oneview.base.RequestBaseModel;
import com.sbi.oneview.network.RequestModel.CardBlockUnblockRequestModel;
import com.sbi.oneview.network.RequestModel.CardHotlistRequestModel;
import com.sbi.oneview.network.RequestModel.CardMiniStatementRequestModel;
import com.sbi.oneview.network.RequestModel.LoginWithOtpRequestModel;
import com.sbi.oneview.network.ResponseModel.BlockUnblockCard.CardBlockUnblockResponseModel;
import com.sbi.oneview.network.ResponseModel.HotlistCard.CardHotlistResponseModel;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.LoginWithOtpResponseModel;
import com.sbi.oneview.network.ResponseModel.MiniStatement.CardMiniStatementResponseModel;
import com.sbi.oneview.utils.SharedConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIRequests {


    public static void loginWithOTP(
            Context context,
            LoginWithOtpRequestModel loginWithOtpRequestModel,
            NetworkResponseCallback<LoginWithOtpResponseModel> callback
    ){
        App.apiClientencrypt.loginUserWithOtp(loginWithOtpRequestModel).enqueue(new Callback<LoginWithOtpResponseModel>() {
            @Override
            public void onResponse(Call<LoginWithOtpResponseModel> call, Response<LoginWithOtpResponseModel> response) {
                callback.onSuccess(call,response);
            }

            @Override
            public void onFailure(Call<LoginWithOtpResponseModel> call, Throwable t) {
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
}

