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

public class LoaiQuanAnAdapter extends RecyclerView.Adapter<LoaiQuanAnAdapter.LoaiQuanViewHolder> {
    private ArrayList<LoaiQuanAn> listData = new ArrayList<>();
    public LoaiQuanAnAdapter(ArrayList<LoaiQuanAn> listData) {
        this.listData = listData;
    }

    @Override
    public LoaiQuanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dong_quanan_loaiquan, parent, false);
        return new LoaiQuanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LoaiQuanViewHolder holder, int position) {
        holder.img.setImageResource(listData.get(position).imageLQA);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class LoaiQuanViewHolder extends RecyclerView.ViewHolder{
        ImageView img;

        public LoaiQuanViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.imv);
        }
    }
}
