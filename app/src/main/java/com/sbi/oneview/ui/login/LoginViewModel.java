package com.sbi.oneview.ui.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sbi.oneview.base.App;
import com.sbi.oneview.network.APIClientSSL;
import com.sbi.oneview.network.APIInterface;
import com.sbi.oneview.network.NetworkResponseCallback;
import com.sbi.oneview.network.RequestModel.LoginRequestModel;
import com.sbi.oneview.network.ResponseModel.LoginResponseModel;
import com.sbi.oneview.network.UserRepository;

import retrofit2.Call;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginResponseModel> userLiveData = new MutableLiveData<>();
    private UserRepository userRepository;



    public LiveData<LoginResponseModel> getUserLiveData() {
        return userLiveData;
    }

    public void login(String username, String password) {
        LoginRequestModel user = new LoginRequestModel();
        user.setEmail(username);
        user.setPassword(password);

        userRepository.checkUSerLogin(user, new NetworkResponseCallback<LoginResponseModel>() {
            @Override
            public void onSuccess(Call<LoginResponseModel> call, Response<LoginResponseModel> response)
            {

                Log.e("LOGIN SUCCESSFUL","DATA IS"+response.body().toString());
            }

            @Override
            public void onResponseBodyNull(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {

            }

            @Override
            public void onResponseUnsuccessful(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {

            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {

            }

            @Override
            public void onInternalServerError() {

            }
        });
    }
}