package com.greenacademy.travelapp.Activity.Adapter.AdapterQuanAn;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenacademy.travelapp.Activity.Model.KhachSanItem;
import com.greenacademy.travelapp.R;

import java.util.ArrayList;

/**
 * Created by GIT on 2/11/2017.
 */

public class KhachSanAdapter extends RecyclerView.Adapter<KhachSanAdapter.ViewHolder> {
    private ArrayList<KhachSanItem> khachSanItems;

    public KhachSanAdapter(ArrayList<KhachSanItem> khachSanItems) {
        this.khachSanItems = khachSanItems;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.khach_san_list_view_item,parent);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTenKhachSan.setText(khachSanItems.get(position).getTenKhachSan());
        holder.tvDiaChiKhachSan.setText(khachSanItems.get(position).getDiaChi());
        holder.tvSoDanhGia.setText("Danh gia: "+khachSanItems.get(position).getSoDanhGia());
        holder.tvGia.setText(khachSanItems.get(position).getGiaTien() + " VND");
    }

    @Override
    public int getItemCount() {
        return khachSanItems.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTenKhachSan;
        TextView tvDiaChiKhachSan;
        TextView tvGia;
        TextView tvSoDanhGia;
        ImageView danhGiaImage;

        ViewHolder(View itemView) {
            super(itemView);
            tvDiaChiKhachSan = (TextView) itemView.findViewById(R.id.tv_khach_san_item_dia_chi);
            tvTenKhachSan = (TextView) itemView.findViewById(R.id.khach_san_item_ten);
            tvGia = (TextView) itemView.findViewById(R.id.tv_khach_san_item_gia);
            tvSoDanhGia = (TextView) itemView.findViewById(R.id.tv_khach_san_item_danh_gia);
            danhGiaImage = (ImageView) itemView.findViewById(R.id.khach_san_item_hinh_anh);
        }
    }
}
