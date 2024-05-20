package com.example.navigation_drawer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.navigation_drawer.Apis.LoginApiClient;
import com.example.navigation_drawer.Apis.LoginApiService;
import com.example.navigation_drawer.ApiRequest.SignUpRequest;
import com.example.navigation_drawer.ResponseData.VerificationResponse;
import com.example.navigation_drawer.utils.SessionManager;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private EditText etName, etEmail, etPassword;
    ProgressDialog progressDialog;
    SessionManager session_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }
        session_signup=new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setIndeterminate(true);
        etName = findViewById(R.id.ettName);
        etEmail = findViewById(R.id.ettEmail);
        etPassword = findViewById(R.id.passwordtext);
        Button button = findViewById(R.id.btnRegister);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                // Create a SignUpRequest object and make the API call
                SignUpRequest signUpRequest = new SignUpRequest(name, email, password);
                signUp(signUpRequest);
                finish();
            }
        });

    }

    private void signUp(SignUpRequest signUpRequest) {
        progressDialog.show();
        LoginApiService apiService = LoginApiClient.getClient().create(LoginApiService.class);

        Call<VerificationResponse> signUpCall = apiService.signup(signUpRequest);

        signUpCall.enqueue(new Callback<VerificationResponse>() {
            @Override
            public void onResponse(Call<VerificationResponse> call, Response<VerificationResponse> response) {
              progressDialog.dismiss();
                if (response.isSuccessful()) {
                    VerificationResponse verificationResponse = response.body();

                    if (verificationResponse != null && verificationResponse.getUserInfo() != null) {
                        int userId = verificationResponse.getUserInfo().getUserId();
                        String username = verificationResponse.getUserInfo().getUsername();
                        String email = verificationResponse.getUserInfo().getEmail();
                        String mobile = verificationResponse.getUserInfo().getMobile();
                        String authToken = verificationResponse.getAuthToken();

                        // Save user data to session
                        session_signup.saveUserData(userId, username, email, mobile, authToken);

                        Log.d("LoginAcount", "User ID: " + userId);
                        Log.d("LoginAcount", "Username: " + username);
                        Log.d("LoginAcount", "Email: " + email);
                        Log.d("LoginAcount", "Mobile: " + mobile);
                        Log.d("LoginAcount", "Auth Token: " + authToken);

                        navigateProfileActivity();
                    } else {
                        Log.e("LoginAcount", "Response body or userInfo is null");
                    }
                } else {
                    Log.e("LoginAcount", "Error Response: " + new Gson().toJson(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<VerificationResponse> call, Throwable t) {
                progressDialog.dismiss();
                // Handle network failure
                // Display a message to the user indicating a network error
            }
        });
    }
    private void navigateProfileActivity() {
        Intent intent = new Intent( this, LoginAcount.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
