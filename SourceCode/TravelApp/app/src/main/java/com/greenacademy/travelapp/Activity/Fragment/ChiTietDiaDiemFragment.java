package com.greenacademy.travelapp.Activity.Fragment;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenacademy.travelapp.Activity.Activity.ManHinhChinhActivity;
import com.greenacademy.travelapp.Activity.AsyncTask.GetKhuVucDiaDiemThread;
import com.greenacademy.travelapp.R;


public class ChiTietDiaDiemFragment extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chi_tiet_dia_diem, container, false);
        initView();
        return view;
    }

    ImageView imageDiaDiem;
    TextView tvTenDiaDiem, tvMoTa, tvDiaChi, tvCheckIn, tvYeuThich, tvSoLuotXem, tvDanhGia;

    private void initView(){
        Bundle bundle = getArguments();
        ((ManHinhChinhActivity) getActivity()).linearControlDuLich.setVisibility(View.VISIBLE);

        imageDiaDiem = (ImageView) view.findViewById(R.id.image_ChiTietDiaDiem);

        tvTenDiaDiem    = (TextView) view.findViewById(R.id.tvTenDiaDiem);
        tvMoTa          = (TextView) view.findViewById(R.id.tvMoTaDiaDiem);
        tvDiaChi        = (TextView) view.findViewById(R.id.tvDiaChi);
        tvCheckIn       = (TextView) view.findViewById(R.id.tvCheckIn);
        tvYeuThich      = (TextView) view.findViewById(R.id.tvLike);
        tvSoLuotXem     = (TextView) view.findViewById(R.id.tvView);
        tvDanhGia       = (TextView) view.findViewById(R.id.tvReview);

        new GetKhuVucDiaDiemThread(this, bundle.getString("link_anh"), "get_image").start();

        tvTenDiaDiem    .setText(bundle.getString                  ("ten_dia_diem"));
        tvMoTa          .setText(bundle.getString                  ("mo_ta"));
        tvDiaChi        .setText(bundle.getString                  ("dia_chi"));
        tvCheckIn       .setText(String.valueOf(bundle.getInt      ("check_in")));
        tvYeuThich      .setText(String.valueOf(bundle.getInt      ("yeu_thich")));
        tvSoLuotXem     .setText(String.valueOf(bundle.getInt      ("so_luot_xem")));
        tvDanhGia       .setText(String.valueOf(bundle.getDouble   ("danh_gia")));
    }

    public void setImageDiaDiem(Bitmap bitmap){
        imageDiaDiem.setImageBitmap(bitmap);
    }

}
