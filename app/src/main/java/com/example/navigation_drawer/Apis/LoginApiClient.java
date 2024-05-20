package com.example.navigation_drawer.Apis;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginApiClient {

    private static final String BASE_URL = "https://mastheadtechnologies.co.in/dentalapp/api/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS) // Adjust timeout as needed
                    .readTimeout(30, TimeUnit.SECONDS).build();

            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();
        }
        return retrofit;
    }
}
