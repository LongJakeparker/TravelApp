package com.greenacademy.travelapp.Activity.Adapter.AdapterQuanAn;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenacademy.travelapp.Activity.Connection.Interface.ItemRecyclerClickListener;
import com.greenacademy.travelapp.Activity.Constant.Constant;
import com.greenacademy.travelapp.Activity.Model.QuanAnChiTiet;
import com.greenacademy.travelapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by DAVIDSON on 2/19/2017.
 */

public class FullListQuanAnAdapter extends RecyclerView.Adapter<FullListQuanAnAdapter.FullListViewHolder> implements Filterable{
    private ArrayList<QuanAnChiTiet> listData = new ArrayList<QuanAnChiTiet>();
    private Context context;
    private ItemRecyclerClickListener itemRecyclerClickListener;
    private QuanAnFilter quanAnFilter;

    public FullListQuanAnAdapter(ArrayList<QuanAnChiTiet> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public FullListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dong_fulllist_quanan, parent, false);
        return new FullListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FullListViewHolder holder, int position) {
        Picasso.with(context).load(listData.get(position).getLinkAnh()).into(holder.imgHinh);
        holder.txtTen.setText(listData.get(position).getTenQuanAn());
        holder.txtLuotDanhGia.setText("Lượt đánh giá: " + listData.get(position).getDanhGia());
        holder.txtMota.setText(Html.fromHtml(listData.get(position).getMoTa()));
        holder.txtCheckin.setText(listData.get(position).getCheckIn() + "");
        holder.txtLike.setText(listData.get(position).getYeuThich() + "");
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    //phần filter adapter
    @Override
    public Filter getFilter() {
        if (quanAnFilter == null){
            quanAnFilter = new QuanAnFilter(this, listData);
        }
        return quanAnFilter;
    }

    public class QuanAnFilter extends Filter {
        private FullListQuanAnAdapter adapter;
        private ArrayList<QuanAnChiTiet> initialList;
        private ArrayList<QuanAnChiTiet> filterList;

        private QuanAnFilter(FullListQuanAnAdapter adapter, ArrayList<QuanAnChiTiet> initialList){
            super();
            this.adapter = adapter;
            this.initialList = new ArrayList<>(initialList);
            this.filterList = new ArrayList<QuanAnChiTiet>();
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            filterList.clear();
            FilterResults results = new FilterResults();
            if (charSequence.length() == 0){
                filterList.addAll(initialList);
            }else {
                String filter = charSequence.toString().toLowerCase();

                for (QuanAnChiTiet quan: initialList){
                    if (quan.getTenQuanAn().toLowerCase().contains(filter)){
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
            adapter.listData.addAll((Collection<? extends QuanAnChiTiet>) filterResults.values);
            adapter.notifyDataSetChanged();
        }
    }

    //phần viewHolder
    public class FullListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgHinh;
        TextView txtTen, txtLuotDanhGia, txtMota, txtCheckin, txtLike;

        public FullListViewHolder(View itemView) {
            super(itemView);
            imgHinh = (ImageView) itemView.findViewById(R.id.imageViewFull);
            txtTen = (TextView) itemView.findViewById(R.id.textViewTenNhaHangFull);
            txtLuotDanhGia = (TextView) itemView.findViewById(R.id.textViewLuotDanhGiaFull);
            txtMota = (TextView) itemView.findViewById(R.id.textViewMotaFull);
            txtCheckin = (TextView) itemView.findViewById(R.id.textViewLuotCheckinFull);
            txtLike = (TextView) itemView.findViewById(R.id.textViewLikeFull);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemRecyclerClickListener != null){
                itemRecyclerClickListener.onClick(view, getAdapterPosition(), Constant.FULLLIST_ADAPTER);
            }
        }
    }

    //phần click item
    public void onItemRecyclerClickListener(ItemRecyclerClickListener itemRecyclerClickListener){
        this.itemRecyclerClickListener = itemRecyclerClickListener;
    }
}
