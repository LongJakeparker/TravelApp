package com.greenacademy.travelapp.Activity.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenacademy.travelapp.R;


public class ChiTietDiaDiemFragment extends Fragment {
    View view;
    RecyclerView recyclerImage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_chi_tiet_dia_diem, container, false);
        return view;
    }

    private void initView(){
        recyclerImage = (RecyclerView) view.findViewById(R.id.recyclerImage);
    }

}
