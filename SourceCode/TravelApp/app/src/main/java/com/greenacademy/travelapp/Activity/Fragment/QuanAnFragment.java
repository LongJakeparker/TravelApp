package com.greenacademy.travelapp.Activity.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.greenacademy.travelapp.Activity.Adapter.CustomLayoutManager;
import com.greenacademy.travelapp.Activity.Adapter.AdapterQuanAn.LoaiQuanAnAdapter;
import com.greenacademy.travelapp.Activity.Adapter.AdapterQuanAn.QuanGanToiAdapter;
import com.greenacademy.travelapp.Activity.Adapter.AdapterQuanAn.TopCheckinAdapter;
import com.greenacademy.travelapp.Activity.Connection.Interface.GetNearQuanAn;
import com.greenacademy.travelapp.Activity.Connection.Interface.GetTopQuanAn;
import com.greenacademy.travelapp.Activity.Connection.Interface.GetTypeQuanAn;
import com.greenacademy.travelapp.Activity.Connection.Task.TaskGetNearQuanAn;
import com.greenacademy.travelapp.Activity.Connection.Task.TaskGetTopQuanAn;
import com.greenacademy.travelapp.Activity.Connection.Task.TaskGetTypeQuanAn;
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

public class QuanAnFragment extends Fragment implements GetTopQuanAn, GetTypeQuanAn, GetNearQuanAn {

    int id = 1;
    double lat = 0;
    double lng = 0;
    Toolbar toolbar;
    RecyclerView recyLoaiQuan, recyQuanGanToi, recyTopCheckin;

    LoaiQuanAnAdapter adapterLoaiQuan;
    QuanGanToiAdapter adapterQuanGanToi;
    TopCheckinAdapter adapterTopCheckin;

    CustomLayoutManager customLayoutManager;
    ArrayList<QuanAnChiTiet> listTop, listNear, listType;
    SearchView searchView;

    TaskGetTopQuanAn taskGetTopQuanAn;
    TaskGetTypeQuanAn taskGetTypeQuanAn;
    TaskGetNearQuanAn taskGetNearQuanAn;

    TatCaQuanAnFragment tatCaQuanAnFragment;

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
        tatCaQuanAnFragment = new TatCaQuanAnFragment();

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchView = (SearchView) view.findViewById(R.id.searchViewQuanAn);
        EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.colorWhite));
        searchEditText.setHintTextColor(getResources().getColor(R.color.colorWhite));

        listTop = new ArrayList<QuanAnChiTiet>();
        listNear = new ArrayList<QuanAnChiTiet>();
        listType = new ArrayList<QuanAnChiTiet>();

        id = this.getArguments().getInt("ID");

        //lấy dữ liệu từ server
        taskGetTopQuanAn = new TaskGetTopQuanAn(this);
        taskGetTopQuanAn.execute("http://103.237.147.137:9045/QuanAn/QuanAnByTop");

        taskGetTypeQuanAn = new TaskGetTypeQuanAn(this);
        taskGetTypeQuanAn.execute("http://103.237.147.137:9045/QuanAn/QuanAnByType?idLoaiQuan=" + id);

        taskGetNearQuanAn = new TaskGetNearQuanAn(this);
        taskGetNearQuanAn.execute("http://103.237.147.137:9045/QuanAn/QuanAnByNear?lat=" + lat + "&lng=" + lng);
        return  view;
    }

    @Override
    public void ketquaTopQuanAn(String kq) {
        adapterTopCheckin = new TopCheckinAdapter(addToList(listTop, kq), getContext());
        recyTopCheckin.setLayoutManager(customLayoutManager);
        recyTopCheckin.setAdapter(adapterTopCheckin);
    }

    @Override
    public void ketquaType(String kq) {
        adapterLoaiQuan = new LoaiQuanAnAdapter(addToList(listType, kq), getContext());
        LinearLayoutManager layoutManagerLoaiQuan = new LinearLayoutManager(getApplicationContext());
        layoutManagerLoaiQuan.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyLoaiQuan.setLayoutManager(layoutManagerLoaiQuan);
        recyLoaiQuan.setAdapter(adapterLoaiQuan);
    }

    @Override
    public void ketquaNearQuan(String kq) {
        adapterQuanGanToi = new QuanGanToiAdapter(addToList(listNear, kq), getContext());
        LinearLayoutManager layoutManagerQGT = new LinearLayoutManager(getApplicationContext());
        layoutManagerQGT.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyQuanGanToi.setLayoutManager(layoutManagerQGT);
        recyQuanGanToi.setAdapter(adapterQuanGanToi);
    }

    private ArrayList<QuanAnChiTiet> addToList(ArrayList<QuanAnChiTiet> arrayList, String s){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(s);

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
                arrayList.add(quanAnChiTiet);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.framelayoutTest, tatCaQuanAnFragment, "tatcaquanan");
                fragmentTransaction.commit();
                return true;
            default: return super.onOptionsItemSelected(item);
        }

    }
}
