package com.greenacademy.travelapp.Activity.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenacademy.travelapp.Activity.Model.LoaiQuanAn;
import com.greenacademy.travelapp.R;

import java.util.ArrayList;

/**
 * Created by User on 1/17/2017.
 */

public class LoaiQuanAnAdapter extends RecyclerView.Adapter<LoaiQuanAnAdapter.LoaiQuanViewHolder> {
    private ArrayList<LoaiQuanAn> listData = new ArrayList<LoaiQuanAn>();
    private int resource;
    public LoaiQuanAnAdapter(int resource, ArrayList<LoaiQuanAn> listData) {
        this.resource = resource;
        this.listData = listData;
    }

    @Override
    public LoaiQuanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(this.resource, parent, false);
        return new LoaiQuanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LoaiQuanViewHolder holder, int position) {
        holder.imgHinhLoaiQuan.setImageResource(listData.get(position).imageLQA);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class LoaiQuanViewHolder extends RecyclerView.ViewHolder{
        ImageView imgHinhLoaiQuan;
        TextView txtTenLoai, txtSoLuong;

        public LoaiQuanViewHolder(View itemView) {
            super(itemView);
            imgHinhLoaiQuan = (ImageView) itemView.findViewById(R.id.imageViewHinhLoaiQuan);
            txtTenLoai = (TextView) itemView.findViewById(R.id.textViewTenLoaiQuan);
            txtSoLuong = (TextView) itemView.findViewById(R.id.textViewSoLuongLoaiQuan);
        }
    }
}
