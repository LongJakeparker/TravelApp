package com.greenacademy.travelapp.Activity.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenacademy.travelapp.Activity.Constant.Constant;
import com.greenacademy.travelapp.Activity.Model.QuanAnChiTiet;
import com.greenacademy.travelapp.R;
import com.squareup.picasso.Picasso;

/**
 * Created by DAVIDSON on 2/16/2017.
 */

public class ChiTietQuanAnFragment extends Fragment {
    ImageView imgHinhChitiet;
    TextView txtTenChitiet, txtMotaChitiet, txtDanhGiaChitiet, txtSeenChitiet, txtLikeChitiet, txtCheckinChitiet;
    Toolbar toolbar;
    QuanAnChiTiet quanAnChiTiet;
    QuanAnFragment quanAnFragment;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quanan_chitiet_fragment, container, false);
        imgHinhChitiet = (ImageView) view.findViewById(R.id.imageViewHinhChitiet);
        txtTenChitiet = (TextView) view.findViewById(R.id.textViewTenChitiet);
        txtDanhGiaChitiet = (TextView) view.findViewById(R.id.textViewLuotDanhGiaChitiet);
        txtSeenChitiet = (TextView) view.findViewById(R.id.textViewSeenChitiet);
        txtLikeChitiet = (TextView) view.findViewById(R.id.textViewLikeChitiet);
        txtCheckinChitiet = (TextView) view.findViewById(R.id.textViewCheckinChitiet);
        txtMotaChitiet = (TextView) view.findViewById(R.id.textViewMotaChitiet);
        toolbar = (Toolbar) view.findViewById(R.id.toolbarChitietQuanAn);

        quanAnFragment = new QuanAnFragment();
        quanAnChiTiet = new QuanAnChiTiet();

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(getFragmentManager().findFragmentByTag("quananChitietFragment"));
                fragmentTransaction.commit();
            }
        });

        quanAnChiTiet = (QuanAnChiTiet) getArguments().getSerializable(Constant.CHITIET_QUANAN);

        Picasso.with(getContext()).load(quanAnChiTiet.getLinkAnh()).into(imgHinhChitiet);
        txtMotaChitiet.setText(Html.fromHtml(quanAnChiTiet.getMoTa()));
        txtDanhGiaChitiet.setText(quanAnChiTiet.getDanhGia() + " Đánh giá");
        txtSeenChitiet.setText(quanAnChiTiet.getSoLuotXem() + "");
        txtLikeChitiet.setText(quanAnChiTiet.getYeuThich() + "");
        txtCheckinChitiet.setText(quanAnChiTiet.getCheckIn() + "");
        txtTenChitiet.setText(quanAnChiTiet.getTenQuanAn());
        return view;
    }
}
