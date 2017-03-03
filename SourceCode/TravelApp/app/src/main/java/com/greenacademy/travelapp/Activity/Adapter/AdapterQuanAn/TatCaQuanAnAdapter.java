package com.greenacademy.travelapp.Activity.Adapter.AdapterQuanAn;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenacademy.travelapp.Activity.Connection.Interface.ItemRecyclerClickListener;
import com.greenacademy.travelapp.Activity.Constant.Constant;
import com.greenacademy.travelapp.Activity.Model.QuanAnChung;
import com.greenacademy.travelapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by DAVIDSON on 2/11/2017.
 */

public class TatCaQuanAnAdapter extends RecyclerView.Adapter<TatCaQuanAnAdapter.TatCaQuanAnViewholder> implements Filterable{
    private ArrayList<QuanAnChung> listData = new ArrayList<QuanAnChung>();
    private Context context;
    private ItemRecyclerClickListener itemRecyclerClickListener;
    private QuanAnFilter quanAnFilter;

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

    //phần filter chon adapter
    @Override
    public Filter getFilter() {
        if (quanAnFilter == null){
            quanAnFilter = new QuanAnFilter(this, listData);
        }
        return quanAnFilter;
    }

    public class QuanAnFilter extends Filter{
        private TatCaQuanAnAdapter adapter;
        private ArrayList<QuanAnChung> initialList;
        private ArrayList<QuanAnChung> filterList;

        private QuanAnFilter(TatCaQuanAnAdapter adapter, ArrayList<QuanAnChung> initialList){
            super();
            this.adapter = adapter;
            this.initialList = new ArrayList<>(initialList);
            this.filterList = new ArrayList<QuanAnChung>();
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            filterList.clear();
            FilterResults results = new FilterResults();
            if (charSequence.length() == 0){
                filterList.addAll(initialList);
            }else {
                String filter = charSequence.toString().toLowerCase();

                for (QuanAnChung quan: initialList){
                    if (quan.getTenLoaiQuanAn().toLowerCase().contains(filter)){
                        filterList.add(quan);
                    }
                }
            }

            results.values = filterList;
            results.count = filterList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            adapter.listData.clear();
            adapter.listData.addAll((Collection<? extends QuanAnChung>) filterResults.values);
            adapter.notifyDataSetChanged();
        }
    }

    //phần viewHolder
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

        // phần click item
        @Override
        public void onClick(View v) {
            if (itemRecyclerClickListener != null){
                itemRecyclerClickListener.onClick(v, getAdapterPosition(), Constant.TATCAQUANAN_ADAPTER);
            }
        }
    }

    public void onItemRecyclerClickListener(ItemRecyclerClickListener itemRecyclerClickListener){
        this.itemRecyclerClickListener = itemRecyclerClickListener;
    }
}
