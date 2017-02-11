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
import com.greenacademy.travelapp.Activity.Adapter.AdapterQuanAn.TopCheckinAdapter;
import com.greenacademy.travelapp.Activity.Connection.Interface.GetTopQuanAn;
import com.greenacademy.travelapp.Activity.Connection.Task.TaskGetTopQuanAn;
import com.greenacademy.travelapp.Activity.Model.QuanAnChiTiet;
import com.greenacademy.travelapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by DAVIDSON on 2/7/2017.
 */

public class QuanAnFragment extends Fragment implements GetTopQuanAn {

    Toolbar toolbar;
    RecyclerView recyLoaiQuan, recyQuanGanToi, recyTopCheckin;
    LoaiQuanAnAdapter adapterLoaiQuan;
    QuanGanToiAdapter adapterQuanGanToi;
    TopCheckinAdapter adapterTopCheckin;
    CustomLayoutManager customLayoutManager;
    ArrayList<QuanAnChiTiet> listTop, listNear, listType;
    SearchView searchView;
    TaskGetTopQuanAn taskGetTopQuanAn;

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

        listTop = new ArrayList<QuanAnChiTiet>();
        listNear = new ArrayList<QuanAnChiTiet>();
        listType = new ArrayList<QuanAnChiTiet>();

        taskGetTopQuanAn = new TaskGetTopQuanAn(this);
        taskGetTopQuanAn.execute("http://103.237.147.137:9045/QuanAn/QuanAnByTop");

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchView = (SearchView) view.findViewById(R.id.searchViewQuanAn);
        EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.colorWhite));
        searchEditText.setHintTextColor(getResources().getColor(R.color.colorWhite));


//        LinearLayoutManager layoutManagerLoaiQuan = new LinearLayoutManager(getApplicationContext());
//        layoutManagerLoaiQuan.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyLoaiQuan.setLayoutManager(layoutManagerLoaiQuan);
//        recyLoaiQuan.setAdapter(adapterLoaiQuan);
//
//
//        LinearLayoutManager layoutManagerQuanGanToi = new LinearLayoutManager(getApplicationContext());
//        layoutManagerQuanGanToi.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyQuanGanToi.setLayoutManager(layoutManagerQuanGanToi);
//        recyQuanGanToi.setAdapter(adapterQuanGanToi);

//        layoutManager = new CustomLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
//        recyQuanGanToi.setLayoutManager(layoutManager);
//        recyQuanGanToi.setAdapter(adapterQuanGanToi);
        recyTopCheckin.setLayoutManager(customLayoutManager);
        recyTopCheckin.setAdapter(adapterTopCheckin);
        return  view;
    }

    @Override
    public void ketquaTopQuanAn(String kq) {
        try {
            JSONObject jsonObject = new JSONObject(kq);
            JSONArray jsonArray = jsonObject.getJSONArray("QuanAns");

            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jQuanAn = jsonArray.getJSONObject(i);

                QuanAnChiTiet quanAnChiTiet = new QuanAnChiTiet();
                quanAnChiTiet.setId(jQuanAn.getInt("Id"));
                quanAnChiTiet.setTenQuanAn(jQuanAn.getString("TenQuanAn"));
                quanAnChiTiet.setMoTa(jQuanAn.getString("MoTa"));
                quanAnChiTiet.setDanhGia(jQuanAn.getInt("DanhGia"));
                quanAnChiTiet.setSoLuotXem(jQuanAn.getInt("SoLuotXem"));
                quanAnChiTiet.setYeuThich(jQuanAn.getInt("YeuThich"));
                quanAnChiTiet.setCheckIn(jQuanAn.getInt("CheckIn"));
                quanAnChiTiet.setLoaiQuanId(jQuanAn.getInt("LoaiQuanId"));
                quanAnChiTiet.setLinkAnh(jQuanAn.getString("LinkAnh"));
                quanAnChiTiet.setLat(jQuanAn.getDouble("Lat"));
                quanAnChiTiet.setLng(jQuanAn.getDouble("Lng"));

                listTop.add(quanAnChiTiet);

                adapterTopCheckin = new TopCheckinAdapter(listTop, getContext());
                recyTopCheckin.setLayoutManager(customLayoutManager);
                recyTopCheckin.setAdapter(adapterTopCheckin);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
