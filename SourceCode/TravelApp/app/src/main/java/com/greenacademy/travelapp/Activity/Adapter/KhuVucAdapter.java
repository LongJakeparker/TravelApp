package com.greenacademy.travelapp.Activity.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenacademy.travelapp.Activity.Model.KhuVucDuLich;
import com.greenacademy.travelapp.R;

import java.util.ArrayList;

/**
 * Created by PhamNhuVu on 2/11/2017.
 */

public class KhuVucAdapter extends RecyclerView.Adapter<KhuVucAdapter.KhuVucVH> {

    ArrayList<KhuVucDuLich> arrKhuVuc;

    public KhuVucAdapter(ArrayList<KhuVucDuLich> arrKhuVuc) {
        this.arrKhuVuc = arrKhuVuc;
    }

    @Override
    public KhuVucVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new KhuVucVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_khuvuc, parent, false));
    }

    @Override
    public void onBindViewHolder(KhuVucVH holder, int position) {
        KhuVucDuLich khuvuc = arrKhuVuc.get(position);
        holder.tvTitleKhuVuc.setText(khuvuc.getTitle());
        holder.imageKhuVuc.setImageResource(khuvuc.getImage());
        holder.tvDescription.setText(khuvuc.getDescription());
        holder.tvLike.setText(String.valueOf(khuvuc.getLike()));
        holder.tvView.setText(String.valueOf(khuvuc.getView()));
        holder.tvStar.setText(String.valueOf(khuvuc.getStar()));
    }

    @Override
    public int getItemCount() {
        return arrKhuVuc.size();
    }

    class KhuVucVH extends RecyclerView.ViewHolder{

        ImageView imageKhuVuc;
        TextView tvTitleKhuVuc, tvDescription, tvLike, tvView, tvStar;

        public KhuVucVH(View itemView) {
            super(itemView);
            imageKhuVuc     = (ImageView) itemView.findViewById(R.id.image_KhuVuc);
            tvTitleKhuVuc   = (TextView) itemView.findViewById(R.id.tvTitle_KhuVuc);
            tvDescription   = (TextView) itemView.findViewById(R.id.tvDescription_KhuVuc);
            tvLike          = (TextView) itemView.findViewById(R.id.tvLike_KhuVuc);
            tvView          = (TextView) itemView.findViewById(R.id.tvView_KhuVuc);
            tvStar          = (TextView) itemView.findViewById(R.id.tvStar_KhuVuc);
        }
    }
}
