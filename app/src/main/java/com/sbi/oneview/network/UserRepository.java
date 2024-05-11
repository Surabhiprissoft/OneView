package com.sbi.oneview.network;

import com.sbi.oneview.network.RequestModel.LoginRequestModel;
import com.sbi.oneview.network.ResponseModel.LoginResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private APIInterface apiService;

    public UserRepository(APIInterface apiService) {
        this.apiService = apiService;
    }

    public void checkUSerLogin(LoginRequestModel loginRequestModel, NetworkResponseCallback<LoginResponseModel> callback)
    {
        apiService.authenticateUser(loginRequestModel).enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {

            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {

            }
        });
    }
 /*   public void checkUserLogin(LoginRequestModel loginRequestModel) {
        apiService.authenticateUser().enqueue(new Callback<Lo>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(call, response);
                } else {
                    callback.onInternalServerError();
                    // You can remove the Toast from here and handle it in the UI layer
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }*/
}

