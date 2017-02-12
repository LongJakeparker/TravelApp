package com.greenacademy.travelapp.Activity.Activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.greenacademy.travelapp.Activity.Fragment.DiaDiemFragment;
import com.greenacademy.travelapp.Activity.Fragment.QuanAnFragment;
import com.greenacademy.travelapp.Activity.Utils.FragmentUtils;
import com.greenacademy.travelapp.R;

import java.util.ArrayList;

public class ManHinhChinhActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Fragment> arrFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh);
        initBottomMenu();
        FragmentUtils.replaceFragment(this, arrFragment.get(0), R.id.framelayout_container, null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Menu_DiaDiem:
                FragmentUtils.replaceFragment(this, arrFragment.get(0), R.id.framelayout_container, null);
                break;
            case R.id.Menu_QuanAn:
                FragmentUtils.replaceFragment(this, arrFragment.get(1), R.id.framelayout_container, null);
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

    private void initBottomMenu() {
        findViewById(R.id.Menu_DiaDiem).setOnClickListener(this);
        findViewById(R.id.Menu_QuanAn).setOnClickListener(this);
        findViewById(R.id.Menu_HanhTrinh).setOnClickListener(this);
        findViewById(R.id.Menu_BanThan).setOnClickListener(this);

        arrFragment = new ArrayList<>();
        arrFragment.add(0, new DiaDiemFragment());
        arrFragment.add(1, new QuanAnFragment());
    }
}
