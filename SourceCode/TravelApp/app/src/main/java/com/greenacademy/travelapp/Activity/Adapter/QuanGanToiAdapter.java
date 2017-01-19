package com.greenacademy.travelapp.Activity.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenacademy.travelapp.Activity.Model.LoaiQuanAn;
import com.greenacademy.travelapp.Activity.Model.QuanGanToi;
import com.greenacademy.travelapp.R;

import java.util.ArrayList;

/**
 * Created by User on 1/17/2017.
 */

public class QuanGanToiAdapter extends RecyclerView.Adapter<QuanGanToiAdapter.QuanGanToiViewHolder> {
    private ArrayList<QuanGanToi> listData = new ArrayList<QuanGanToi>();
    public QuanGanToiAdapter(ArrayList<QuanGanToi> listData) {
        this.listData = listData;
    }

    @Override
    public QuanGanToiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dong_quan_gan_toi, parent, false);
        return new QuanGanToiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QuanGanToiViewHolder holder, int position) {
        holder.imgQuan.setImageResource(listData.get(position).Image);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class QuanGanToiViewHolder extends RecyclerView.ViewHolder{
        ImageView imgQuan;

        public QuanGanToiViewHolder(View itemView) {
            super(itemView);
            imgQuan = (ImageView) itemView.findViewById(R.id.imageViewQuanGanToi);

        }
    }
}
