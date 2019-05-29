package com.example.dell.domea;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dell on 2019/5/29.
 */

public class OKutils {
    private static ApiService apiService;
    public static ApiService getapi(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        if (apiService==null) {
            if (BuildConfig.DEBUG) {
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            } else {
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
            }
            final OkHttpClient build = new OkHttpClient.Builder()
                    .readTimeout(2000, TimeUnit.SECONDS)
                    .writeTimeout(2000, TimeUnit.SECONDS)
                    .connectTimeout(2000, TimeUnit.SECONDS)
                    .build();
            final Retrofit build1 = new Retrofit.Builder()
                    .client(build)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(ApiService.Url)
                    .build();
            apiService=build1.create(ApiService.class);
        }
        return apiService;
    }
}
