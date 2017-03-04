package com.greenacademy.travelapp.Activity.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenacademy.travelapp.Activity.Model.DiaDiemDuLich;
import com.greenacademy.travelapp.R;

import java.util.ArrayList;

/**
 * Created by PhamNhuVu on 2/26/2017.
 */

public class TopDiaDiemAdapter extends ArrayAdapter<DiaDiemDuLich> {

    ImageView imageKhuVuc;
    TextView tvTitleKhuVuc, tvDescription, tvLike, tvView, tvStar;
    int width;

    public TopDiaDiemAdapter(Context context, int resource, ArrayList<DiaDiemDuLich> objects, int width) {
        super(context, resource, objects);
        this.width = width;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DiaDiemDuLich diadiem = getItem(position);
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_recycler_diadiem, null);
            imageKhuVuc     = (ImageView) convertView.findViewById(R.id.image_DiaDiem);
            tvTitleKhuVuc   = (TextView) convertView.findViewById(R.id.tvTitle_DiaDiem);
            tvDescription   = (TextView) convertView.findViewById(R.id.tvDescription_DiaDiem);
            tvLike          = (TextView) convertView.findViewById(R.id.tvLike_DiaDiem);
            tvView          = (TextView) convertView.findViewById(R.id.tvView_DiaDiem);
            tvStar          = (TextView) convertView.findViewById(R.id.tvStar_DiaDiem);
        convertView.setLayoutParams(new RecyclerView.LayoutParams(width, (width*2)/3));
        tvTitleKhuVuc.setText(diadiem.getStrTenDiaDiem());
//            imageKhuVuc.setImageResource(diadiem.getStrLinkAnh());
        imageKhuVuc.setImageBitmap(diadiem.getImageDiaDiem());
        tvDescription.setText(diadiem.getStrMoTa());
        tvLike.setText(String.valueOf(diadiem.getIntYeuThich()));
        tvView.setText(String.valueOf(diadiem.getIntSoLuotXem()));
        tvStar.setText(String.valueOf(diadiem.getDbDanhGia()));
        return convertView;
    }
}
