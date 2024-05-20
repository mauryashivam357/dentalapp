package com.example.navigation_drawer.Apis;

import com.example.navigation_drawer.ApiRequest.LoginRequest;
import com.example.navigation_drawer.ApiRequest.SignUpRequest;
import com.example.navigation_drawer.ResponseData.VerificationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginApiService {

    @POST("login/email")
    Call<VerificationResponse> loginWithEmail(@Body LoginRequest loginRequest);

    @POST("signup/form")
    Call<VerificationResponse> signup(@Body SignUpRequest signupRequest);

}
