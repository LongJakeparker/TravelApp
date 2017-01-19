package com.greenacademy.travelapp.Activity.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.greenacademy.travelapp.Activity.Model.LoaiQuanAn;
import com.greenacademy.travelapp.R;

import java.util.ArrayList;

/**
 * Created by User on 1/17/2017.
 */

public class TopCheckinAdapter extends RecyclerView.Adapter<TopCheckinAdapter.LoaiQuanViewHolder> {
    private ArrayList<LoaiQuanAn> listData = new ArrayList<LoaiQuanAn>();
    public TopCheckinAdapter(ArrayList<LoaiQuanAn> listData) {
        this.listData = listData;
    }

    @Override
    public LoaiQuanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dong_top_checkin, parent, false);
        return new LoaiQuanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LoaiQuanViewHolder holder, int position) {
        holder.imgHinhLoaiQuan.setImageResource(listData.get(position).Image);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class LoaiQuanViewHolder extends RecyclerView.ViewHolder{
        ImageView imgHinhLoaiQuan;

        public LoaiQuanViewHolder(View itemView) {
            super(itemView);
            imgHinhLoaiQuan = (ImageView) itemView.findViewById(R.id.imageViewTopCheckin);
        }
    }
}
