package com.sbi.oneview.network;

import retrofit2.Call;
import retrofit2.Response;

public interface NetworkResponseCallback<T> {

    void onSuccess(Call<T> call, Response<T> response);

    void onResponseBodyNull(Call<T> call, Response<T> response);

    void onResponseUnsuccessful(Call<T> call, Response<T> response);

    void onFailure(Call<T> call, Throwable t);

    void onInternalServerError();
}
