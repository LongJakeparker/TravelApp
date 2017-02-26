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
import android.widget.ViewFlipper;

import com.greenacademy.travelapp.Activity.Adapter.DiaDiemAdapter;
import com.greenacademy.travelapp.Activity.Adapter.TopDiaDiemAdapter;
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
    ArrayList<DiaDiemDuLich> arrTopDiaDiem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_dia_diem, container, false);
        initView();
        return view;
    }

    private void initView(){

        arrTopDiaDiem = new ArrayList<>();
        arrTopDiaDiem.add(new DiaDiemDuLich("Cà Mau", "Phải đến nơi đây chơi thử một lần", 9.1, 1203, 32));
        arrTopDiaDiem.add(new DiaDiemDuLich("Cà Mau", "Phải đến nơi đây chơi thử một lần", 9.1, 1203, 32));
        arrTopDiaDiem.add(new DiaDiemDuLich("Cà Mau", "Phải đến nơi đây chơi thử một lần", 9.1, 1203, 32));
        arrTopDiaDiem.add(new DiaDiemDuLich("Cà Mau", "Phải đến nơi đây chơi thử một lần", 9.1, 1203, 32));
        arrTopDiaDiem.add(new DiaDiemDuLich("Cà Mau", "Phải đến nơi đây chơi thử một lần", 9.1, 1203, 32));
        flipperDiaDiem = (AdapterViewFlipper) view.findViewById(R.id.flipperTopDiaDiem);
        flipperDiaDiem.setAdapter(new TopDiaDiemAdapter(getActivity(), R.layout.item_recycler_diadiem, arrTopDiaDiem,
                getActivity().getWindowManager().getDefaultDisplay().getWidth()));

        arrDiaDiem = new ArrayList<>();
        arrDiaDiem.add(new DiaDiemDuLich("Cà Mau", "Phải đến nơi đây chơi thử một lần", 9.1, 1203, 32));
        arrDiaDiem.add(new DiaDiemDuLich("Cà Mau", "Phải đến nơi đây chơi thử một lần", 9.1, 1203, 32));
        arrDiaDiem.add(new DiaDiemDuLich("Cà Mau", "Phải đến nơi đây chơi thử một lần", 9.1, 1203, 32));
        arrDiaDiem.add(new DiaDiemDuLich("Cà Mau", "Phải đến nơi đây chơi thử một lần", 9.1, 1203, 32));
        arrDiaDiem.add(new DiaDiemDuLich("Cà Mau", "Phải đến nơi đây chơi thử một lần", 9.1, 1203, 32));
        arrDiaDiem.add(new DiaDiemDuLich("Cà Mau", "Phải đến nơi đây chơi thử một lần", 9.1, 1203, 32));
        recyclerDiaDiem = (RecyclerView) view.findViewById(R.id.recyclerDiaDiem_DuLich);
        recyclerDiaDiem.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false));
        recyclerDiaDiem.setAdapter(new DiaDiemAdapter(arrDiaDiem, this));
    }

    @Override
    public void callbackDiaDiemFragment(int position) {
        getFragmentManager().beginTransaction()
                .replace(R.id.framelayout_container, new ChiTietDiaDiemFragment())
                .addToBackStack(null)
                .commit();
    }
}
