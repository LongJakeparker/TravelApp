package com.greenacademy.travelapp.Activity.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.greenacademy.travelapp.Activity.Adapter.CustomLayoutManager;
import com.greenacademy.travelapp.Activity.Adapter.LoaiQuanAnAdapter;
import com.greenacademy.travelapp.Activity.Adapter.QuanGanToiAdapter;
import com.greenacademy.travelapp.Activity.Adapter.TopCheckinAdapter;
import com.greenacademy.travelapp.Activity.Model.LoaiQuanAn;
import com.greenacademy.travelapp.Activity.Model.QuanGanToi;
import com.greenacademy.travelapp.R;

import java.util.ArrayList;

public class QuanAnActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyLoaiQuan, recyQuanGanToi, recyTopCheckin;
    LoaiQuanAnAdapter adapterLoaiQuan;
    QuanGanToiAdapter adapterQuanGanToi;
    TopCheckinAdapter adapterTopCheckin;
    CustomLayoutManager customLayoutManager;
    ArrayList<LoaiQuanAn> listImageLoaiQuan;
    ArrayList<QuanGanToi> listImageQuanGanToi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_an);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyLoaiQuan = (RecyclerView) findViewById(R.id.recyclerViewLoaiQuan);
        recyQuanGanToi = (RecyclerView) findViewById(R.id.recyclerViewQuanGanToi);
        recyTopCheckin = (RecyclerView) findViewById(R.id.recyclerViewTopCheckin);
        customLayoutManager = new CustomLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        listImageLoaiQuan = new ArrayList<LoaiQuanAn>();
        listImageQuanGanToi = new ArrayList<QuanGanToi>();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // phần này dùng dữ liệu giả
        listImageLoaiQuan.add(new LoaiQuanAn(R.drawable.test));
        listImageLoaiQuan.add(new LoaiQuanAn(R.mipmap.ic_launcher));
        listImageLoaiQuan.add(new LoaiQuanAn(R.mipmap.ic_launcher));
        listImageLoaiQuan.add(new LoaiQuanAn(R.mipmap.ic_launcher));
        listImageLoaiQuan.add(new LoaiQuanAn(R.mipmap.ic_launcher));
        listImageLoaiQuan.add(new LoaiQuanAn(R.mipmap.ic_launcher));
        listImageLoaiQuan.add(new LoaiQuanAn(R.mipmap.ic_launcher));
        listImageLoaiQuan.add(new LoaiQuanAn(R.mipmap.ic_launcher));
        listImageLoaiQuan.add(new LoaiQuanAn(R.mipmap.ic_launcher));
        listImageLoaiQuan.add(new LoaiQuanAn(R.mipmap.ic_launcher));
        listImageLoaiQuan.add(new LoaiQuanAn(R.drawable.test));
        listImageLoaiQuan.add(new LoaiQuanAn(R.mipmap.ic_launcher));
        listImageLoaiQuan.add(new LoaiQuanAn(R.mipmap.ic_launcher));
        listImageLoaiQuan.add(new LoaiQuanAn(R.mipmap.ic_launcher));
        listImageLoaiQuan.add(new LoaiQuanAn(R.mipmap.ic_launcher));
        listImageLoaiQuan.add(new LoaiQuanAn(R.mipmap.ic_launcher));
        listImageLoaiQuan.add(new LoaiQuanAn(R.mipmap.ic_launcher));
        listImageLoaiQuan.add(new LoaiQuanAn(R.mipmap.ic_launcher));
        listImageLoaiQuan.add(new LoaiQuanAn(R.mipmap.ic_launcher));
        listImageLoaiQuan.add(new LoaiQuanAn(R.mipmap.ic_launcher));

        listImageQuanGanToi.add(new QuanGanToi(R.drawable.test_b));
        listImageQuanGanToi.add(new QuanGanToi(R.mipmap.ic_launcher));
        listImageQuanGanToi.add(new QuanGanToi(R.mipmap.ic_launcher));
        listImageQuanGanToi.add(new QuanGanToi(R.mipmap.ic_launcher));

        adapterLoaiQuan = new LoaiQuanAnAdapter(listImageLoaiQuan);
        adapterQuanGanToi = new QuanGanToiAdapter(listImageQuanGanToi);
        adapterTopCheckin = new TopCheckinAdapter(listImageLoaiQuan);

        LinearLayoutManager layoutManagerLoaiQuan = new LinearLayoutManager(getApplicationContext());
        layoutManagerLoaiQuan.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyLoaiQuan.setLayoutManager(layoutManagerLoaiQuan);
        recyLoaiQuan.setAdapter(adapterLoaiQuan);


        LinearLayoutManager layoutManagerQuanGanToi = new LinearLayoutManager(getApplicationContext());
        layoutManagerQuanGanToi.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyQuanGanToi.setLayoutManager(layoutManagerQuanGanToi);
        recyQuanGanToi.setAdapter(adapterQuanGanToi);

//        layoutManager = new CustomLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
//        recyQuanGanToi.setLayoutManager(layoutManager);
//        recyQuanGanToi.setAdapter(adapterQuanGanToi);
        recyTopCheckin.setLayoutManager(customLayoutManager);
        recyTopCheckin.setAdapter(adapterTopCheckin);
    }
}
