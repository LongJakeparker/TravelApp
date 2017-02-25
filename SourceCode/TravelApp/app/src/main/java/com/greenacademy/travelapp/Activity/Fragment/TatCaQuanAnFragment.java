package com.greenacademy.travelapp.Activity.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.greenacademy.travelapp.Activity.Adapter.AdapterQuanAn.TatCaQuanAnAdapter;
import com.greenacademy.travelapp.Activity.Connection.Interface.GetQuanAn;
import com.greenacademy.travelapp.Activity.Connection.Interface.ItemRecyclerClickListener;
import com.greenacademy.travelapp.Activity.Connection.Task.TaskGetServerAllQuanAn;
import com.greenacademy.travelapp.Activity.Constant.Constant;
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

public class TatCaQuanAnFragment extends Fragment implements GetQuanAn, ItemRecyclerClickListener {
    RecyclerView recyclerTatCaQuanAn;
    TaskGetServerAllQuanAn getAllQuanAn;
    ArrayList<QuanAnChung>  listQuanAnChung;
    TatCaQuanAnAdapter tatcaAdapter;
    Toolbar toolbar;
    QuanAnFragment quanAnFragment;
    SearchView searchView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tatca_quanan_fragment, container, false);
        recyclerTatCaQuanAn = (RecyclerView) view.findViewById(R.id.recyclerViewTatCaQuanAn);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        searchView = (SearchView) view.findViewById(R.id.searchViewTatCaQuanAn);
        EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.colorWhite));
        listQuanAnChung = new ArrayList<QuanAnChung>();
        quanAnFragment = new QuanAnFragment();

        tatcaAdapter = new TatCaQuanAnAdapter(listQuanAnChung, getContext());

        getAllQuanAn = new TaskGetServerAllQuanAn(this);
        getAllQuanAn.execute("http://103.237.147.137:9045/QuanAn/AllLoaiQuanAn");

        //phần search
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                tatcaAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return view;
    }

    @Override
    public void ketquaQuanAn(String kq, int adapterID) {
        if (adapterID == Constant.TATCAQUANAN_ADAPTER){
            try {
                JSONObject jsonQbject = new JSONObject(kq);
                JSONArray jsonQuanAn = jsonQbject.getJSONArray("LoaiQuanAns");
                Log.d("AAA", jsonQuanAn.length() + "");
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
                tatcaAdapter.onItemRecyclerClickListener(this);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerTatCaQuanAn.setLayoutManager(layoutManager);
                recyclerTatCaQuanAn.setAdapter(tatcaAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View view, int position, int adapterID) {
        if (adapterID == Constant.TATCAQUANAN_ADAPTER){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.framelayout_container, quanAnFragment, "quananFragment");
            fragmentTransaction.commit();
            Bundle bundle = new Bundle();
            bundle.putInt("ID", listQuanAnChung.get(position).getId());
            quanAnFragment.setArguments(bundle);
        }
    }
}
