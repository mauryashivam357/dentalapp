package com.example.navigation_drawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigation_drawer.Apis.LoginApiClient;
import com.example.navigation_drawer.Apis.LoginApiService;
import com.example.navigation_drawer.ApiRequest.LoginRequest;
import com.example.navigation_drawer.ResponseData.VerificationResponse;
import com.example.navigation_drawer.utils.SessionManager;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class LoginAcount extends AppCompatActivity {
    private EditText etEmail, etPassword;
    ProgressDialog progressDialog;
    private Button button, skip_button;
    TextView signup;
    String token;
    private SessionManager sessionManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acount);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setIndeterminate(true);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        button = findViewById(R.id.btnLogin);
        signup=findViewById(R.id.button_sign_up);
        skip_button = findViewById(R.id.skip_login);
        sessionManager = new SessionManager(this);
        Log.d("TAG", "onCreate: "+sessionManager);
//        Toast.makeText(this, sessionManager.getAuthToken(), Toast.LENGTH_SHORT).show();
        skip_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              onBackPressed();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginAcount.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                LoginRequest loginRequest = new LoginRequest(email, password);
                loginWithEmail(loginRequest);

            }

        });


    }

    private void loginWithEmail(LoginRequest loginRequest) {
        progressDialog.show();
        LoginApiService apiService = LoginApiClient.getClient().create(LoginApiService.class);

        Call<VerificationResponse> loginCall = apiService.loginWithEmail(loginRequest);

        loginCall.enqueue(new Callback<VerificationResponse>() {
            @Override
            public void onResponse(@NonNull Call<VerificationResponse> call, @NonNull Response<VerificationResponse> response) {
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
                        sessionManager.saveUserData(userId, username, email, mobile, authToken);

                        Log.d("LoginAcount", "User ID: " + userId);
                        Log.d("LoginAcount", "Username: " + username);
                        Log.d("LoginAcount", "Email: " + email);
                        Log.d("LoginAcount", "Mobile: " + mobile);
                        Log.d("LoginAcount", "Auth Token: " + authToken);

                        navigateToProfileActivity();

                    } else {
                        Log.e("LoginAcount", "Response body or userInfo is null");
                    }
                } else {
                    Log.e("LoginAcount", "Error Response: " + new Gson().toJson(response.errorBody()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<VerificationResponse> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Log.e("LoginAcount", "Network failure: " + t.getMessage());
            }
        });
    }

    private void navigateToProfileActivity() {
        Intent intent = new Intent(LoginAcount.this, NearestClinics.class);
        startActivity(intent);

    }
    private void navigateProfileActivity() {
        Intent intent = new Intent(LoginAcount.this, DesboardFragment.class);
        startActivity(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if( token!=""){

        }
        else {



        }


    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "user_test", Toast.LENGTH_SHORT).show();
        if( token!=""){
//            navigateProfileActivity();
        }
        else {



        }
        super.onBackPressed();
    }
}
