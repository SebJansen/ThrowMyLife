package com.example.seb.throwmylife.utils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    private String URL = "http://localhost:2403/";
    private Retrofit retrofit;

    public RetrofitHelper() {
        this.retrofit = null;
    }

    public RetrofitHelper build(OkHttpClient okHttpClient) {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(this.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return this;
    }

    public RetrofitHelper build() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return this;
    }

    public Object create(Class c) {
        if (this.retrofit == null)
            return this.retrofit;

        return this.retrofit.create(c);
    }
}
