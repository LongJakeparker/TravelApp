package com.greenacademy.travelapp.Activity.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.greenacademy.travelapp.Activity.Adapter.CustomLayoutManager;
import com.greenacademy.travelapp.Activity.Adapter.Delete.LoaiQuanAnAdapter;
import com.greenacademy.travelapp.Activity.Adapter.Delete.QuanGanToiAdapter;
import com.greenacademy.travelapp.Activity.Adapter.Delete.TopCheckinAdapter;
import com.greenacademy.travelapp.Activity.Model.Delete.LoaiQuanAn;
import com.greenacademy.travelapp.Activity.Model.Delete.QuanGanToi;
import com.greenacademy.travelapp.R;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by DAVIDSON on 2/7/2017.
 */

public class QuanAnFragment extends Fragment {

    Toolbar toolbar;
    RecyclerView recyLoaiQuan, recyQuanGanToi, recyTopCheckin;
    LoaiQuanAnAdapter adapterLoaiQuan;
    QuanGanToiAdapter adapterQuanGanToi;
    TopCheckinAdapter adapterTopCheckin;
    CustomLayoutManager customLayoutManager;
    ArrayList<LoaiQuanAn> listImageLoaiQuan;
    ArrayList<QuanGanToi> listImageQuanGanToi;
    SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quanan_fragment, container, false);

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        recyLoaiQuan = (RecyclerView) view.findViewById(R.id.recyclerViewLoaiQuan);
        recyQuanGanToi = (RecyclerView) view.findViewById(R.id.recyclerViewQuanGanToi);
        recyTopCheckin = (RecyclerView) view.findViewById(R.id.recyclerViewTopCheckin);
        searchView = (SearchView) view.findViewById(R.id.searchViewQuanAn);
        customLayoutManager = new CustomLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        listImageLoaiQuan = new ArrayList<LoaiQuanAn>();
        listImageQuanGanToi = new ArrayList<QuanGanToi>();

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchView = (SearchView) view.findViewById(R.id.searchViewQuanAn);
        EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.colorWhite));
        searchEditText.setHintTextColor(getResources().getColor(R.color.colorWhite));

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
        return  view;
    }
}
