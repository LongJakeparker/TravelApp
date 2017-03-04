package com.greenacademy.travelapp.Activity.Adapter.AdapterQuanAn;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenacademy.travelapp.Activity.Connection.Interface.ItemRecyclerClickListener;
import com.greenacademy.travelapp.Activity.Constant.Constant;
import com.greenacademy.travelapp.Activity.Model.QuanAnChiTiet;
import com.greenacademy.travelapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by User on 1/17/2017.
 */

public class LoaiQuanAnAdapter extends RecyclerView.Adapter<LoaiQuanAnAdapter.LoaiQuanViewHolder> {
    private ArrayList<QuanAnChiTiet> listData = new ArrayList<QuanAnChiTiet>();
    private Context context;
    private ItemRecyclerClickListener itemRecyclerClickListener;

    public LoaiQuanAnAdapter(ArrayList<QuanAnChiTiet> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public LoaiQuanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dong_quanan_loaiquan, parent, false);
        return new LoaiQuanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LoaiQuanViewHolder holder, int position) {
        Picasso.with(context).load(listData.get(position).getLinkAnh()).into(holder.imgHinhLoaiQuan);
        holder.txtTenLoai.setText(listData.get(position).getTenQuanAn());
        holder.txtLuotXem.setText("" + listData.get(position).getSoLuotXem());
        holder.txtYeuThich.setText("" + listData.get(position).getYeuThich());
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public void onItemRecyclerClickListener(ItemRecyclerClickListener itemRecyclerClickListener){
        this.itemRecyclerClickListener = itemRecyclerClickListener;
    }

    public class LoaiQuanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgHinhLoaiQuan;
        TextView txtTenLoai, txtLuotXem, txtYeuThich;

        public LoaiQuanViewHolder(View itemView) {
            super(itemView);
            imgHinhLoaiQuan = (ImageView) itemView.findViewById(R.id.imageViewHinhLoaiQuan);
            txtTenLoai = (TextView) itemView.findViewById(R.id.textViewTenLoaiQuan);
            txtLuotXem = (TextView) itemView.findViewById(R.id.textViewLuotXemLoaiQuan);
            txtYeuThich = (TextView) itemView.findViewById(R.id.textViewYeuLoaiQuan);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemRecyclerClickListener != null){
                itemRecyclerClickListener.onClick(view, getAdapterPosition(), Constant.LOAIQUANAN_ADAPTER);
            }
        }
    }
}
