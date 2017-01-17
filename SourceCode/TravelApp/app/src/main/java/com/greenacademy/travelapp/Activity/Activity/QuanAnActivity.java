package com.greenacademy.travelapp.Activity.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;

import com.greenacademy.travelapp.Activity.Adapter.LoaiQuanAnAdapter;
import com.greenacademy.travelapp.Activity.Model.LoaiQuanAn;
import com.greenacademy.travelapp.R;

import java.util.ArrayList;

public class QuanAnActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView rcvLoaiQuan;
    LoaiQuanAnAdapter adapter;
    ArrayList<LoaiQuanAn> listImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_an);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rcvLoaiQuan = (RecyclerView) findViewById(R.id.recyclerViewLoaiQuan);
        listImage = new ArrayList<LoaiQuanAn>();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listImage.add(new LoaiQuanAn(R.mipmap.ic_launcher));
        listImage.add(new LoaiQuanAn(R.mipmap.ic_launcher));
        listImage.add(new LoaiQuanAn(R.mipmap.ic_launcher));
        listImage.add(new LoaiQuanAn(R.mipmap.ic_launcher));

        adapter = new LoaiQuanAnAdapter(listImage);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        rcvLoaiQuan.setLayoutManager(layoutManager);
        rcvLoaiQuan.setAdapter(adapter);
    }
}
