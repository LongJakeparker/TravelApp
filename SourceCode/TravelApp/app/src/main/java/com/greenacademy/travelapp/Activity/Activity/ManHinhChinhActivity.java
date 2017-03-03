package com.greenacademy.travelapp.Activity.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.greenacademy.travelapp.Activity.Fragment.DiaDiemFragment;
import com.greenacademy.travelapp.Activity.Fragment.ScheduleFragment;
import com.greenacademy.travelapp.Activity.Fragment.TaiKhoanFragment;
import com.greenacademy.travelapp.Activity.Fragment.TatCaQuanAnFragment;
import com.greenacademy.travelapp.Activity.Utils.FragmentUtils;
import com.greenacademy.travelapp.R;

import java.util.ArrayList;

public class ManHinhChinhActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Fragment> arrFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null){
            arrFragment = new ArrayList<>();
            arrFragment.add(0, new DiaDiemFragment());
            arrFragment.add(1, new TatCaQuanAnFragment());
            arrFragment.add(2, new ScheduleFragment());
            arrFragment.add(3, new TaiKhoanFragment());
        }
        setContentView(R.layout.activity_man_hinh_chinh);
        initBottomMenu();
        FragmentUtils.replaceFragment(this, arrFragment.get(0), R.id.framelayout_container, null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Menu_DiaDiem:
                FragmentUtils.replaceFragmentWithouBackStack(this, arrFragment.get(0), R.id.framelayout_container, null);
                break;
            case R.id.Menu_QuanAn:
                FragmentUtils.replaceFragmentWithouBackStack(this, arrFragment.get(1), R.id.framelayout_container, null);
                break;
            case R.id.Menu_HanhTrinh:
                FragmentUtils.replaceFragmentWithouBackStack(this, arrFragment.get(2), R.id.framelayout_container, null);
                break;
            case R.id.Menu_BanThan:
                FragmentUtils.replaceFragmentWithouBackStack(this, arrFragment.get(3), R.id.framelayout_container, null);
                break;
        }
    }

    private void initBottomMenu() {
        findViewById(R.id.Menu_DiaDiem).setOnClickListener(this);
        findViewById(R.id.Menu_QuanAn).setOnClickListener(this);
        findViewById(R.id.Menu_HanhTrinh).setOnClickListener(this);
        findViewById(R.id.Menu_BanThan).setOnClickListener(this);


    }
}
