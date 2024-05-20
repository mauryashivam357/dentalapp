package com.example.navigation_drawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class SplaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splace);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));
        }
        Thread t = new Thread(){

            public void run(){
                try{
                    sleep(4000);
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    Intent start = new Intent(SplaceActivity.this,MainActivity.class);
                    startActivity(start);
                    finish();
                }

            }
        };

        t.start();


    }

    }
