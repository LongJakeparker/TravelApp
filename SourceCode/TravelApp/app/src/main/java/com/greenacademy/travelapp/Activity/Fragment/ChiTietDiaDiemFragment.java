package com.greenacademy.travelapp.Activity.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenacademy.travelapp.R;


public class ChiTietDiaDiemFragment extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_chi_tiet_dia_diem, container, false);
        initView();
        return view;
    }

    RecyclerView recyclerImage;
    ImageView imageDiaDiem;
    TextView numberLike, numberView, numberReview;

    private void initView(){
        recyclerImage = (RecyclerView) view.findViewById(R.id.recyclerImage);
        imageDiaDiem = (ImageView) view.findViewById(R.id.image_ChiTietDiaDiem);

        numberLike = (TextView) view.findViewById(R.id.numberLike);
        numberView = (TextView) view.findViewById(R.id.numberReview);
        numberReview = (TextView) view.findViewById(R.id.numberReview);

    }

}
