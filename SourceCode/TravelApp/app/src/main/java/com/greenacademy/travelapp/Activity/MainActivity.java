package com.greenacademy.travelapp.Activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.greenacademy.travelapp.Activity.Fragment.BanDoFragment;
import com.greenacademy.travelapp.Activity.Fragment.KhachSanFragment;
import com.greenacademy.travelapp.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //BanDoFragment banDoFragment = new BanDoFragment();
        KhachSanFragment khachSanFragment = new KhachSanFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment,khachSanFragment);
        fragmentTransaction.commit();
    }
}
