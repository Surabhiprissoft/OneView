package com.sbi.oneview.network;

import com.sbi.oneview.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class APIClient {

    static OkHttpClient httpClient = new OkHttpClient.Builder()

            .connectTimeout(10, TimeUnit.MINUTES)
            .writeTimeout(10,TimeUnit.MINUTES)
            .readTimeout(10,TimeUnit.MINUTES)
            .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();


    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create());


    public static <S> S create(Class<S> serviceClass) {

        Retrofit retrofit = builder.client(httpClient)
                .build();


        return retrofit.create(serviceClass);


    }
}
