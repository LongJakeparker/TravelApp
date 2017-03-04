package com.greenacademy.travelapp.Activity.Adapter.AdapterQuanAn;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
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

public class QuanGanToiAdapter extends RecyclerView.Adapter<QuanGanToiAdapter.QuanGanToiViewHolder> {
    private ArrayList<QuanAnChiTiet> listData = new ArrayList<QuanAnChiTiet>();
    private Context context;
    private ItemRecyclerClickListener itemRecyclerClickListener;

    public QuanGanToiAdapter(ArrayList<QuanAnChiTiet> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public QuanGanToiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dong_quan_gan_toi, parent, false);
        return new QuanGanToiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QuanGanToiViewHolder holder, int position) {
        Picasso.with(context).load(listData.get(position).getLinkAnh()).into(holder.imgQuan);
        holder.txtTenQuanQGT.setText(listData.get(position).getTenQuanAn());
        holder.txtDanhGiaQGT.setText("Lượt đánh giá: " + listData.get(position).getDanhGia());
        holder.txtMotaQGT.setText(Html.fromHtml(listData.get(position).getMoTa()).toString());
        holder.txtYeuThich.setText("" + listData.get(position).getYeuThich());

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public void onItemRecyclerClickListener(ItemRecyclerClickListener itemClickListener) {
        this.itemRecyclerClickListener = itemClickListener;
    }

    public class QuanGanToiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgQuan;
        TextView txtTenQuanQGT, txtDanhGiaQGT, txtMotaQGT, txtYeuThich;

        public QuanGanToiViewHolder(View itemView) {
            super(itemView);
            imgQuan = (ImageView) itemView.findViewById(R.id.imageViewQuanGanToi);
            txtTenQuanQGT = (TextView) itemView.findViewById(R.id.textViewTenQGT);
            txtDanhGiaQGT = (TextView) itemView.findViewById(R.id.textViewLuotDanhGiaQGT);
            txtMotaQGT = (TextView) itemView.findViewById(R.id.textViewMotaQGT);
            txtYeuThich = (TextView) itemView.findViewById(R.id.textViewYeuThichQGT);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemRecyclerClickListener != null){
                itemRecyclerClickListener.onClick(v, getAdapterPosition(), Constant.QUANGANTOI_ADAPTER);
            }
        }
    }
}
