package com.greenacademy.travelapp.Activity.Activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.greenacademy.travelapp.Activity.Fragment.BanDoFragment;
import com.greenacademy.travelapp.Activity.Fragment.KhuVucFragment;
import com.greenacademy.travelapp.Activity.Fragment.QuanAnFragment;
import com.greenacademy.travelapp.Activity.Fragment.ScheduleFragment;
import com.greenacademy.travelapp.Activity.Utils.FragmentUtils;
import com.greenacademy.travelapp.R;

import java.util.ArrayList;

public class ManHinhChinhActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Fragment> arrFragment;
    int width;
    int height;
    public LinearLayout linearControlDuLich;
    String currenFragment;

    public TextView tvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        width = getResources().getConfiguration().screenWidthDp;
        height = width*2/3;

        if (savedInstanceState == null){
            arrFragment = new ArrayList<>();
            arrFragment.add(0, new KhuVucFragment());
            arrFragment.add(1, new BanDoFragment());
            arrFragment.add(2, new ScheduleFragment());
        }
        setContentView(R.layout.activity_man_hinh_chinh);
        initView();
        tvTitle.setText("Khu Vực");
        FragmentUtils.replaceFragmentWithouBackStack(this, arrFragment.get(0), R.id.framelayout_container, null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Menu_DiaDiem:
                tvTitle.setText("Khu Vực");
                FragmentUtils.replaceFragmentWithouBackStack(this, arrFragment.get(0), R.id.framelayout_container, null);
                break;

            case R.id.Menu_QuanAn:
                tvTitle.setText("Bản đồ");
                FragmentUtils.replaceFragmentWithouBackStack(this, arrFragment.get(1), R.id.framelayout_container, null);
                break;

            case R.id.Menu_HanhTrinh:
                tvTitle.setText("Hành Trình");
                FragmentUtils.replaceFragmentWithouBackStack(this, arrFragment.get(2), R.id.framelayout_container, null);
                break;

            case R.id.Menu_BanThan:
                Log.d("0", "3");
                break;

            case R.id.btnDuLichSub:
                break;

            case R.id.btnQuanAnSub:
                break;

            case R.id.btnKhachSanSub:
                break;
        }
    }

    private void initView() {
        linearControlDuLich = (LinearLayout) findViewById(R.id.linearControlDuLich);
        tvTitle = (TextView) findViewById(R.id.tvTitle);

        findViewById(R.id.Menu_DiaDiem).setOnClickListener(this);
        findViewById(R.id.Menu_QuanAn).setOnClickListener(this);
        findViewById(R.id.Menu_HanhTrinh).setOnClickListener(this);
        findViewById(R.id.Menu_BanThan).setOnClickListener(this);

        findViewById(R.id.btnDuLichSub).setOnClickListener(this);
        findViewById(R.id.btnQuanAnSub).setOnClickListener(this);
        findViewById(R.id.btnKhachSanSub).setOnClickListener(this);
    }

    private void setCurrenFragment(String currenFragment){
        this.currenFragment = currenFragment;
    }
}
