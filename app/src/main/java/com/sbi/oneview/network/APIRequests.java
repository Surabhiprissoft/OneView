package com.sbi.oneview.network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.sbi.oneview.base.App;
import com.sbi.oneview.utils.SharedConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIRequests {
   /* public static void checkUserLogin(
            Context context,
            String user,
            String ChannelID,
            NetworkResponseCallback<String> callback
    ) {


        try {
            App.apiClientencrypt.authenticateUser(ChannelID, user).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        callback.onSuccess(call, response);
                    } else {
                        callback.onInternalServerError();
                        Toast.makeText(context, App.getAppContext().getString(R.string.internal_server_error_please_try_again_later), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    callback.onFailure(call, t);
                }
            });
        }catch (Exception e) {
            Log.d("EXCEPTION",""+e.getMessage());
        }
    }

    public static void validateCardLinking(Context context,
                                           String validateCardLinkingRequestModelRequestBaseModel, NetworkResponseCallback<String> callback) {

        try {
            App.apiClientencrypt.validatecardLinking(SharedConfig.getInstance(context).getChannelID(), validateCardLinkingRequestModelRequestBaseModel).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        callback.onSuccess(call, response);
                    } else {
                        callback.onInternalServerError();
                        Toast.makeText(context, App.getAppContext().getString(R.string.internal_server_error_please_try_again_later), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    callback.onFailure(call, t);
                }
            });
        }catch (Exception e) {
            Log.d("EXCEPTION",""+e.getMessage());
        }
    }


    public static void processCardLinking(
            Context context,
            String processCardLinkingRequestModelRequestBaseModel,
            NetworkResponseCallback<String> callback
    ) {
        try {
            App.apiClientencrypt.processcardLinking(SharedConfig.getInstance(context).getChannelID(), processCardLinkingRequestModelRequestBaseModel).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        callback.onSuccess(call, response);
                    } else {
                        callback.onInternalServerError();
                        Toast.makeText(context, App.getAppContext().getString(R.string.internal_server_error_please_try_again_later), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    callback.onFailure(call, t);
                }
            });
        }catch (Exception e) {
            Log.d("EXCEPTION",""+e.getMessage());
        }
    }

    public static void getCustomerDetails(Context context, String customerDetailsRequestModelRequestBaseModel, NetworkResponseCallback<String> callback) {
        try {
            App.apiClientencrypt.getCustomerData(SharedConfig.getInstance(context).getChannelID(), customerDetailsRequestModelRequestBaseModel).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        callback.onSuccess(call, response);
                    } else {
                        callback.onInternalServerError();
                        Toast.makeText(context, App.getAppContext().getString(R.string.internal_server_error_please_try_again_later), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    callback.onFailure(call, t);
                }
            });
        }catch (Exception e) {
            Log.d("EXCEPTION",""+e.getMessage());
        }
    }

    public static void processEformLink(
            Context context,
            String cardLinkingEformRequestModelRequestBaseModel,
            NetworkResponseCallback<String> callback
    ) {
        try {
            App.apiClientencrypt.processEcardLink(SharedConfig.getInstance(context).getChannelID(), cardLinkingEformRequestModelRequestBaseModel).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        callback.onSuccess(call, response);
                    } else {
                        callback.onInternalServerError();
                        Toast.makeText(context, App.getAppContext().getString(R.string.internal_server_error_please_try_again_later), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    callback.onFailure(call, t);
                }
            });
        }catch (Exception e) {
            Log.d("EXCEPTION",""+e.getMessage());
        }
    }


    public static void getCustomerCardData(
            Context context,
            String customerCardDataRequestModelRequestBaseModel,
            NetworkResponseCallback<String> callback
    ) {
        try {
            App.apiClientencrypt.getCustomerCardData(SharedConfig.getInstance(context).getChannelID(), customerCardDataRequestModelRequestBaseModel).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        callback.onSuccess(call, response);
                    } else {
                        callback.onInternalServerError();
                        Toast.makeText(context, App.getAppContext().getString(R.string.internal_server_error_please_try_again_later), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    callback.onFailure(call, t);
                }
            });
        }catch (Exception e) {
            Log.d("EXCEPTION",""+e.getMessage());
        }
    }


    public static void requestCardBlock(
            Context context,
            String requestCardBlockRequestModelRequestBaseModel,
            NetworkResponseCallback<String> callback) {
        try {
            App.apiClientencrypt.requestCardBlock(SharedConfig.getInstance(context).getChannelID(), requestCardBlockRequestModelRequestBaseModel).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        callback.onSuccess(call, response);
                    } else {
                        callback.onInternalServerError();
                        Toast.makeText(context, App.getAppContext().getString(R.string.internal_server_error_please_try_again_later), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    callback.onFailure(call, t);
                }
            });
        }catch (Exception e) {
            Log.d("EXCEPTION",""+e.getMessage());
        }
    }


    public static void processCardBlock(
            Context context,
            String processCardBlockRequestModelRequestBaseModel,
            NetworkResponseCallback<String> callback
    ) {
        try {
            App.apiClientencrypt.processCardBlock(SharedConfig.getInstance(context).getChannelID(), processCardBlockRequestModelRequestBaseModel).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        callback.onSuccess(call, response);
                    } else {
                        callback.onInternalServerError();
                        Toast.makeText(context, App.getAppContext().getString(R.string.internal_server_error_please_try_again_later), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    callback.onFailure(call, t);
                }
            });
        }catch (Exception e) {
            Log.d("EXCEPTION",""+e.getMessage());
        }
    }


    public static void checkReplacementEligibility(
            Context context,
            String checkReplacementEligibilityRequestModelRequestBaseModel,
            NetworkResponseCallback<String> callback
    ) {
        try {
            App.apiClientencrypt.checkReplacementEligibility(SharedConfig.getInstance(context).getChannelID(), checkReplacementEligibilityRequestModelRequestBaseModel).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        callback.onSuccess(call, response);
                    } else {
                        callback.onInternalServerError();
                        Toast.makeText(context, App.getAppContext().getString(R.string.internal_server_error_please_try_again_later), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    callback.onFailure(call, t);
                }
            });
        }catch (Exception e) {
            Log.d("EXCEPTION",""+e.getMessage());
        }
    }

    public static void resendOtp(
            Context context,
            String resendOtpRequestModelRequestBaseModel,
            NetworkResponseCallback<String> callback
    ) {
        try {
            App.apiClientencrypt.resendOtp(SharedConfig.getInstance(context).getChannelID(), resendOtpRequestModelRequestBaseModel).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        callback.onSuccess(call, response);
                    } else {
                        callback.onInternalServerError();
                        Toast.makeText(context, App.getAppContext().getString(R.string.internal_server_error_please_try_again_later), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    callback.onFailure(call, t);
                }
            });
        }catch (Exception e) {
            Log.d("EXCEPTION",""+e.getMessage());
        }
    }


    public static void replaceCard(
            Context context,
            String replaceCardRequestModelRequestBaseModel,
            NetworkResponseCallback<String> callback
    ) {
        try {
            App.apiClientencrypt.replaceCard(SharedConfig.getInstance(context).getChannelID(), replaceCardRequestModelRequestBaseModel).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        callback.onSuccess(call, response);
                    } else {
                        callback.onInternalServerError();
                        Toast.makeText(context, App.getAppContext().getString(R.string.internal_server_error_please_try_again_later), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    callback.onFailure(call, t);
                }
            });
        }catch (Exception e) {
            Log.d("EXCEPTION",""+e.getMessage());
        }
    }


    public static void logoutUser(
            Context context,
            String logoutRequestModelRequestBaseModel,
            NetworkResponseCallback<String> callback
    ) {
        try {
            App.apiClientencrypt.logoutUser(SharedConfig.getInstance(context).getChannelID(), logoutRequestModelRequestBaseModel).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        callback.onSuccess(call, response);
                    } else {
                        callback.onInternalServerError();
                        Toast.makeText(context, App.getAppContext().getString(R.string.internal_server_error_please_try_again_later), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    callback.onFailure(call, t);
                }
            });
        }catch (Exception e) {
            Log.d("EXCEPTION",""+e.getMessage());
        }
    }*/
}

