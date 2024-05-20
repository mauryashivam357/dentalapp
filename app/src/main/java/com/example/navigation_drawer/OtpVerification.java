package com.example.navigation_drawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class OtpVerification extends AppCompatActivity {
    private EditText inputCode1, inputCode2, inputCode3, inputCode4, inputCode5, inputCode6;
    private TextView textMobile;
    private Button buttonVerify;
    private ProgressBar progressBar;
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_otp_verification);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }

        Button button = findViewById(R.id.buttonGetOTP);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Uncomment the following line if you want to perform OTP verification
                // performVerification();

                // Navigate to the CreateProfile activity (sample navigation)
                Intent intent = new Intent(OtpVerification.this, CreateProfile.class);
                startActivity(intent);
            }
        });

        // Initialize UI elements related to OTP verification
        init();
    }

    private void init() {
        inputCode1 = findViewById(R.id.inputCode1);
        inputCode2 = findViewById(R.id.inputCode2);
        inputCode3 = findViewById(R.id.inputCode3);
        inputCode4 = findViewById(R.id.inputCode4);
        inputCode5 = findViewById(R.id.inputCode5);
        inputCode6 = findViewById(R.id.inputCode6);

        // Uncomment the following lines if needed
//        textMobile = findViewById(R.id.textMobile);
//        buttonVerify = findViewById(R.id.buttonVerify);
//        progressBar = findViewById(R.id.progressBar);
    }

    // Uncomment and implement the following function for OTP verification
    private void performVerification() {
        // Retrieve OTP values
        String otp = inputCode1.getText().toString() +
                inputCode2.getText().toString() +
                inputCode3.getText().toString() +
                inputCode4.getText().toString() +
                inputCode5.getText().toString() +
                inputCode6.getText().toString();

        // Sample verification logic (replace with your actual OTP verification logic)
        String expectedOTP = "123456"; // Replace with your actual expected OTP
        if (otp.equals(expectedOTP)) {
            // OTP is correct, you can proceed with the next steps
            // Add your logic here
        } else {
            // OTP is incorrect, display an error message or take appropriate action
            // Add your error handling logic here
        }
    }
}
