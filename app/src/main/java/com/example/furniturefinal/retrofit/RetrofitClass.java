package com.example.furniturefinal.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClass {
    private static OkHttpClient client = new OkHttpClient.Builder().build();
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {

        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.177.69.50:8762/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        return retrofit;
    }
}