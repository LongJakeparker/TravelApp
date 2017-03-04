package com.greenacademy.travelapp.Activity.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenacademy.travelapp.Activity.Adapter.AdapterQuanAn.LoaiQuanAnAdapter;
import com.greenacademy.travelapp.Activity.Adapter.AdapterQuanAn.QuanGanToiAdapter;
import com.greenacademy.travelapp.Activity.Adapter.AdapterQuanAn.TopCheckinAdapter;
import com.greenacademy.travelapp.Activity.Connection.Interface.GetQuanAn;
import com.greenacademy.travelapp.Activity.Connection.Interface.ItemRecyclerClickListener;
import com.greenacademy.travelapp.Activity.Connection.Task.TaskGetNearQuanAn;
import com.greenacademy.travelapp.Activity.Connection.Task.TaskGetTopQuanAn;
import com.greenacademy.travelapp.Activity.Connection.Task.TaskGetTypeQuanAn;
import com.greenacademy.travelapp.Activity.Constant.Constant;
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

public class QuanAnFragment extends Fragment implements GetQuanAn, ItemRecyclerClickListener, View.OnClickListener {

    int id = 1;
    double lat = 0;
    double lng = 0;
    Toolbar toolbar;
    RecyclerView recyLoaiQuan, recyQuanGanToi, recyTopCheckin;
    LoaiQuanAnAdapter adapterLoaiQuan;
    QuanGanToiAdapter adapterQuanGanToi;
    TopCheckinAdapter adapterTopCheckin;
    ArrayList<QuanAnChiTiet> listTop, listNear, listType;
    TextView txtXemAllTop, txtXemAllNear, txtXemAllType, txtXemTatCa;
    ImageView imgMapIcon;

    TaskGetTopQuanAn taskGetTopQuanAn;
    TaskGetTypeQuanAn taskGetTypeQuanAn;
    TaskGetNearQuanAn taskGetNearQuanAn;

    TatCaQuanAnFragment tatCaQuanAnFragment;
    ChiTietQuanAnFragment chiTietQuanAnFragment;
    FullListQuananFragment fullListQuananFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quanan_fragment, container, false);

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        recyLoaiQuan = (RecyclerView) view.findViewById(R.id.recyclerViewLoaiQuan);
        recyQuanGanToi = (RecyclerView) view.findViewById(R.id.recyclerViewQuanGanToi);
        recyTopCheckin = (RecyclerView) view.findViewById(R.id.recyclerViewTopCheckin);
        txtXemAllNear = (TextView) view.findViewById(R.id.textViewXemTatCaQGT);
        txtXemAllTop = (TextView) view.findViewById(R.id.textViewXemTatCaTCI);
        txtXemAllType = (TextView) view.findViewById(R.id.textViewXemTatCaLQ);
        txtXemTatCa = (TextView) view.findViewById(R.id.textViewXemTatCa);
        imgMapIcon = (ImageView) view.findViewById(R.id.imageViewMapIcon);

        tatCaQuanAnFragment = new TatCaQuanAnFragment();
        chiTietQuanAnFragment = new ChiTietQuanAnFragment();
        fullListQuananFragment = new FullListQuananFragment();

        txtXemAllType.setOnClickListener(this);
        txtXemAllNear.setOnClickListener(this);
        txtXemAllTop.setOnClickListener(this);
        txtXemTatCa.setOnClickListener(this);
        imgMapIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogBanDoFragment dialogBanDoFragment = new DialogBanDoFragment();
                dialogBanDoFragment.show(getFragmentManager(), "sdgf");
            }
        });

        try {
            id = getArguments().getInt("ID");
        }catch (Exception e){

        }

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(tatCaQuanAnFragment, false, "tatcaquanan");
            }
        });

        listTop = new ArrayList<QuanAnChiTiet>();
        listNear = new ArrayList<QuanAnChiTiet>();
        listType = new ArrayList<QuanAnChiTiet>();

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
    public void ketquaQuanAn(String kq, int adapterID) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        switch (adapterID){
            case Constant.LOAIQUANAN_ADAPTER:
                adapterLoaiQuan = new LoaiQuanAnAdapter(addToList(listType, kq), getContext());
                adapterLoaiQuan.onItemRecyclerClickListener(this);
                recyLoaiQuan.setLayoutManager(layoutManager);
                recyLoaiQuan.setAdapter(adapterLoaiQuan);
                break;
            case Constant.QUANGANTOI_ADAPTER:
                adapterQuanGanToi = new QuanGanToiAdapter(addToList(listNear, kq), getContext());
                adapterQuanGanToi.onItemRecyclerClickListener(this);
                recyQuanGanToi.setLayoutManager(layoutManager);
                recyQuanGanToi.setAdapter(adapterQuanGanToi);
                break;
            case Constant.TOPCHECKIN_ADAPTER:
                adapterTopCheckin = new TopCheckinAdapter(addToList(listTop, kq), getContext());
                adapterTopCheckin.onItemRecyclerClickListener(this);
                LinearLayoutManager layoutManagerTop = new LinearLayoutManager(getApplicationContext());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyTopCheckin.setLayoutManager(layoutManagerTop);
                recyTopCheckin.setAdapter(adapterTopCheckin);
                break;
        }
    }

    @Override
    public void onClick(View view, int position, int adapterID) {
        setFragment(chiTietQuanAnFragment, true, "quananChitietFragment");
        Bundle bundle = new Bundle();

        switch (adapterID){
            case Constant.LOAIQUANAN_ADAPTER:
                bundle.putSerializable(Constant.CHITIET_QUANAN, listType.get(position));
                break;
            case Constant.QUANGANTOI_ADAPTER:
                bundle.putSerializable(Constant.CHITIET_QUANAN, listNear.get(position));
                break;
            case Constant.TOPCHECKIN_ADAPTER:
                bundle.putSerializable(Constant.CHITIET_QUANAN, listTop.get(position));
                break;
        }

        chiTietQuanAnFragment.setArguments(bundle);
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
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()){
            case R.id.textViewXemTatCaQGT:
                bundle.putParcelableArrayList(Constant.FULLLIST_QUANAN, listNear);
                break;
            case R.id.textViewXemTatCaTCI:
                bundle.putParcelableArrayList(Constant.FULLLIST_QUANAN, listTop);
                break;
            case R.id.textViewXemTatCaLQ:
                bundle.putParcelableArrayList(Constant.FULLLIST_QUANAN, listType);
                break;
            case R.id.textViewXemTatCa:
                ArrayList<QuanAnChiTiet> list = new ArrayList<QuanAnChiTiet>();
                list.addAll(listNear);
                list.addAll(listTop);
                list.addAll(listType);
                bundle.putParcelableArrayList(Constant.FULLLIST_QUANAN, list);
                break;
        }

        fullListQuananFragment.setArguments(bundle);
        setFragment(fullListQuananFragment, true, "FulllistFrag");
    }

    public void setFragment(Fragment fragment, boolean option, String fragName){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (option){
            fragmentTransaction.add(R.id.framelayout_container, fragment, fragName);
        }else {
            fragmentTransaction.replace(R.id.framelayout_container, fragment, fragName);
        }

        fragmentTransaction.commit();
    }
}
