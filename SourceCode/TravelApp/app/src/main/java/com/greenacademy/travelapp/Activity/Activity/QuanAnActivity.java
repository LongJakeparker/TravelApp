package com.greenacademy.travelapp.Activity.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.greenacademy.travelapp.Activity.Fragment.TatCaQuanAnFragment;
import com.greenacademy.travelapp.R;

public class QuanAnActivity extends AppCompatActivity {

    TatCaQuanAnFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_an);
        fragment = new TatCaQuanAnFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayoutTest, fragment, "gjfyu");
        fragmentTransaction.commit();

    }
}
