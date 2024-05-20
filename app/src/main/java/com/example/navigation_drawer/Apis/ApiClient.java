package com.example.navigation_drawer.Apis;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {


    private static final String BASE_URL = "https://mastheadtechnologies.co.in/dentalapp/api/";

    private static String TOKEN= ""; // Your token here

    private static Retrofit retrofit = null;


    public static Retrofit getClient(String asd) {
        if (retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(chain -> {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", "Bearer " + asd); // Add token to Authorization header
                Request request = requestBuilder.build();
                return chain.proceed(request);
            });

            OkHttpClient client = httpClient.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }


}
