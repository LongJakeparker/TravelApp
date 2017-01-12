package com.greenacademy.travelapp.Activity.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.greenacademy.travelapp.Activity.Constant.Constant;
import com.greenacademy.travelapp.Activity.MainActivity;
import com.greenacademy.travelapp.Activity.Utils.MySharedPreferences;
import com.greenacademy.travelapp.R;

public class SplashScreenActivity extends AppCompatActivity {
    public static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final MySharedPreferences mySharedPreferences = new MySharedPreferences(SplashScreenActivity.this, Constant.Check_First_Time);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(SPLASH_TIME_OUT);
                    if (mySharedPreferences.getString(Constant.Check_First_Time) == null){
                        mySharedPreferences.putString(Constant.Check_First_Time,Constant.Status_Check_False);
                        Intent intent = new Intent(SplashScreenActivity.this, TutorialActivity.class);
                        startActivity(intent);
                    }else if(mySharedPreferences.getString(Constant.Check_First_Time) == Constant.Status_Check_False){
                        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
