package com.greenacademy.travelapp.Activity.Activity.ManHinhChinh;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.greenacademy.travelapp.R;

public class ManHinhChinhActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh);
        initBottomMenu();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Menu_DiaDiem:
                Log.d("0", "0");
                break;
            case R.id.Menu_QuanAn:
                Log.d("0", "1");
                break;
            case R.id.Menu_HanhTrinh:
                Log.d("0", "2");
                break;
            case R.id.Menu_BanThan:
                Log.d("0", "3");
                break;
        }
    }

    private void transactionFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framlayout_container, fragment)
                .addToBackStack(null)
                .commit();
    }


    private void initBottomMenu() {
        findViewById(R.id.Menu_DiaDiem).setOnClickListener(this);
        findViewById(R.id.Menu_QuanAn).setOnClickListener(this);
        findViewById(R.id.Menu_HanhTrinh).setOnClickListener(this);
        findViewById(R.id.Menu_BanThan).setOnClickListener(this);
    }
}
