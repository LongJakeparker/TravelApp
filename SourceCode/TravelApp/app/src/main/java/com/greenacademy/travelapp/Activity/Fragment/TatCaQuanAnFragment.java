package com.greenacademy.travelapp.Activity.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenacademy.travelapp.Activity.Adapter.AdapterQuanAn.TatCaQuanAnAdapter;
import com.greenacademy.travelapp.Activity.Connection.Interface.GetAllQuanAn;
import com.greenacademy.travelapp.Activity.Connection.Task.TaskGetServerAllQuanAn;
import com.greenacademy.travelapp.Activity.Model.QuanAnChung;
import com.greenacademy.travelapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by DAVIDSON on 2/10/2017.
 */

public class TatCaQuanAnFragment extends Fragment implements GetAllQuanAn{
    RecyclerView recyclerTatCaQuanAn;
    TaskGetServerAllQuanAn getAllQuanAn;
    ArrayList<QuanAnChung>  listQuanAnChung;
    TatCaQuanAnAdapter tatcaAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tatca_quanan_fragment, container, false);
        recyclerTatCaQuanAn = (RecyclerView) view.findViewById(R.id.recyclerViewTatCaQuanAn);
        listQuanAnChung = new ArrayList<QuanAnChung>();

        getAllQuanAn = new TaskGetServerAllQuanAn(this);
        getAllQuanAn.execute("http://103.237.147.137:9045/QuanAn/AllLoaiQuanAn");

        return view;
    }

    @Override
    public void ketquaAllQuanAn(String kq) {
//        Toast.makeText(getContext(), kq, Toast.LENGTH_SHORT).show();
        try {
            JSONObject jsonQbject = new JSONObject(kq);

            JSONArray jsonQuanAn = jsonQbject.getJSONArray("LoaiQuanAns");

            for (int i = 0; i < jsonQuanAn.length(); i++){
                JSONObject jQuanAn = jsonQuanAn.getJSONObject(i);
                QuanAnChung quanAnChung = new QuanAnChung();
                quanAnChung.setTenLoaiQuanAn(jQuanAn.getString("TenLoaiQuanAn"));
                quanAnChung.setDanhGia(jQuanAn.getDouble("DanhGia"));
                quanAnChung.setLinkAnh(jQuanAn.getString("LinkAnh"));
                quanAnChung.setMoTa(jQuanAn.getString("MoTa"));
                quanAnChung.setId(jQuanAn.getInt("Id"));
                quanAnChung.setSoLuotXem(jQuanAn.getInt("SoLuotXem"));
                quanAnChung.setYeuThich(jQuanAn.getInt("YeuThich"));
                listQuanAnChung.add(quanAnChung);
            }
            tatcaAdapter = new TatCaQuanAnAdapter(listQuanAnChung, getContext());

            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerTatCaQuanAn.setLayoutManager(layoutManager);
            recyclerTatCaQuanAn.setAdapter(tatcaAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
