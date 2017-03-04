package com.greenacademy.travelapp.Activity.Fragment;


import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterViewFlipper;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.greenacademy.travelapp.Activity.Activity.ManHinhChinhActivity;
import com.greenacademy.travelapp.Activity.Adapter.DiaDiemAdapter;
import com.greenacademy.travelapp.Activity.Adapter.TopDiaDiemAdapter;
import com.greenacademy.travelapp.Activity.AsyncTask.GetKhuVucDiaDiemThread;
import com.greenacademy.travelapp.Activity.Constant.Constant;
import com.greenacademy.travelapp.Activity.Model.DiaDiemDuLich;
import com.greenacademy.travelapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiaDiemFragment extends Fragment implements DiaDiemAdapter.DiaDiemVH.InterfaceDiaDiemDuLich {

    View view;
    RecyclerView recyclerDiaDiem;
    AdapterViewFlipper flipperDiaDiem;
    ArrayList<DiaDiemDuLich> arrDiaDiem;
    DiaDiemAdapter diaDiemAdapter;
    ArrayList<DiaDiemDuLich> arrTopDiaDiem;
    int id;
    private TopDiaDiemAdapter topDiaDiemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_dia_diem, container, false);
        id = getArguments().getInt("id");
//        new GetKhuVucDiaDiemAsyncTask(Constant.TYPE_DATA_DIA_DIEM, this).execute();
        new GetKhuVucDiaDiemThread(this, "http://103.237.147.137:9045/DiaDiem/DiaDiemById?idKhuVuc=".concat(String.valueOf(id)), Constant.TYPE_DATA_DIA_DIEM).start();
        initView();
        return view;
    }

    private void initView(){
        ((ManHinhChinhActivity) getActivity()).linearControlDuLich.setVisibility(View.VISIBLE);
        arrTopDiaDiem = new ArrayList<>();
        flipperDiaDiem = (AdapterViewFlipper) view.findViewById(R.id.flipperTopDiaDiem);
        topDiaDiemAdapter = new TopDiaDiemAdapter(getActivity(), R.layout.item_recycler_diadiem, arrTopDiaDiem,
                getActivity().getWindowManager().getDefaultDisplay().getWidth());
        flipperDiaDiem.setAdapter(topDiaDiemAdapter);

        arrDiaDiem = new ArrayList<>();
        recyclerDiaDiem = (RecyclerView) view.findViewById(R.id.recyclerDiaDiem_DuLich);
        recyclerDiaDiem.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false));
        diaDiemAdapter = new DiaDiemAdapter(arrDiaDiem, this);
        recyclerDiaDiem.setAdapter(diaDiemAdapter);
    }

    @Override
    public void callbackDiaDiemFragment(int position) {
        ChiTietDiaDiemFragment chiTietDiaDiemFragment = (ChiTietDiaDiemFragment) getFragmentManager().findFragmentById(R.id.chitiet_diadiem_fragment);
        if (chiTietDiaDiemFragment == null){
            chiTietDiaDiemFragment = new ChiTietDiaDiemFragment();
            Toast.makeText(getActivity(), "new", Toast.LENGTH_SHORT).show();
        }
        getFragmentManager().beginTransaction()
                .replace(R.id.framelayout_container, chiTietDiaDiemFragment, "chitiet_diadiem")
                .addToBackStack("dulich")
                .commit();
    }

    public void updateData(DiaDiemDuLich diadiem){
        arrDiaDiem.add(diadiem);
        arrTopDiaDiem.add(diadiem);
        topDiaDiemAdapter.notifyDataSetChanged();
        diaDiemAdapter.notifyDataSetChanged();
    }

    public void updateTopData(ArrayList<DiaDiemDuLich> arrDiaDiem){
        arrTopDiaDiem = arrDiaDiem;
        topDiaDiemAdapter.notifyDataSetChanged();
    }

}
