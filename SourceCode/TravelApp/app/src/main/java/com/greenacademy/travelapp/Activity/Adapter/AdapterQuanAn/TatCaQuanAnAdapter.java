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
import com.greenacademy.travelapp.Activity.Model.QuanAnChung;
import com.greenacademy.travelapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by DAVIDSON on 2/11/2017.
 */

public class TatCaQuanAnAdapter extends RecyclerView.Adapter<TatCaQuanAnAdapter.TatCaQuanAnViewholder> {
    private ArrayList<QuanAnChung> listData = new ArrayList<QuanAnChung>();
    private Context context;
    private ItemRecyclerClickListener itemRecyclerClickListener;

    public TatCaQuanAnAdapter(ArrayList<QuanAnChung> listData, Context context){
        this.listData = listData;
        this.context = context;
    }

    @Override
    public TatCaQuanAnViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dong_tatca_quanan, parent, false);
        return new TatCaQuanAnViewholder(view);
    }

    @Override
    public void onBindViewHolder(TatCaQuanAnViewholder holder, int position) {
        Picasso.with(context).load(listData.get(position).getLinkAnh()).into(holder.imgDaiDien);
        holder.txtTenNhaHang.setText(listData.get(position).getTenLoaiQuanAn());
        holder.txtLuotDanhGia.setText("Lượt đánh giá: " + listData.get(position).getDanhGia());
        holder.txtMota.setText(listData.get(position).getMoTa());
        holder.txtSoLuotXem.setText("" + listData.get(position).getSoLuotXem());
        holder.txtLuotYeuThich.setText("" + listData.get(position).getYeuThich());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void onItemRecyclerClickListener(ItemRecyclerClickListener itemRecyclerClickListener){
        this.itemRecyclerClickListener = itemRecyclerClickListener;
    }

    public class TatCaQuanAnViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgDaiDien;
        TextView txtTenNhaHang, txtLuotDanhGia, txtMota, txtSoLuotXem, txtLuotYeuThich;

        public TatCaQuanAnViewholder(View itemView) {
            super(itemView);
            imgDaiDien = (ImageView) itemView.findViewById(R.id.imageViewTatca);
            txtTenNhaHang = (TextView) itemView.findViewById(R.id.textViewTenNhaHang);
            txtLuotDanhGia = (TextView) itemView.findViewById(R.id.textViewLuotDanhGia);
            txtMota = (TextView) itemView.findViewById(R.id.textViewMota);
            txtSoLuotXem = (TextView) itemView.findViewById(R.id.textViewSoLuotXem);
            txtLuotYeuThich = (TextView) itemView.findViewById(R.id.textViewLuotYeuThich);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemRecyclerClickListener != null){
                itemRecyclerClickListener.onClick(v, getAdapterPosition(), Constant.TATCAQUANAN_ADAPTER);
            }
        }
    }
}
