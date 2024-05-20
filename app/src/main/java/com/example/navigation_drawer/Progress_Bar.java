package com.example.navigation_drawer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;

public class Progress_Bar extends AppCompatActivity {
    public static ProgressBar ppp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        ppp = (ProgressBar) findViewById(R.id.ppp);
    }
}