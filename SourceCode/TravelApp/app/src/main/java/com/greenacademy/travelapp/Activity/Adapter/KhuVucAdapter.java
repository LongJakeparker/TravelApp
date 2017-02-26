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
    KhuVucVH.InterfaceKhuVuc interfaceKhuVuc;

    public KhuVucAdapter(ArrayList<KhuVucDuLich> arrKhuVuc, KhuVucVH.InterfaceKhuVuc interfaceKhuVuc) {
        this.arrKhuVuc = arrKhuVuc;
        this.interfaceKhuVuc = interfaceKhuVuc;
    }

    @Override
    public KhuVucVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new KhuVucVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_khuvuc, parent, false));
    }

    @Override
    public void onBindViewHolder(KhuVucVH holder, int position) {
        final KhuVucDuLich khuvuc = arrKhuVuc.get(position);
        holder.initView(khuvuc);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceKhuVuc.callbackKhuVucFragment(khuvuc.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrKhuVuc.size();
    }

    public static class KhuVucVH extends RecyclerView.ViewHolder {
        View itemView;
        ImageView imageKhuVuc;
        TextView tvTitleKhuVuc, tvDescription, tvLike, tvView, tvStar;

        public KhuVucVH(View itemView) {
            super(itemView);
            this.itemView = itemView;
            imageKhuVuc     = (ImageView) itemView.findViewById(R.id.image_KhuVuc);
            tvTitleKhuVuc   = (TextView) itemView.findViewById(R.id.tvTitle_KhuVuc);
            tvDescription   = (TextView) itemView.findViewById(R.id.tvDescription_KhuVuc);
            tvLike          = (TextView) itemView.findViewById(R.id.tvLike_KhuVuc);
            tvView          = (TextView) itemView.findViewById(R.id.tvView_KhuVuc);
            tvStar          = (TextView) itemView.findViewById(R.id.tvStar_KhuVuc);

        }

        public void initView(KhuVucDuLich khuvuc){
            tvTitleKhuVuc.setText(khuvuc.getTitle());
            imageKhuVuc.setImageResource(khuvuc.getImage());
            tvDescription.setText(khuvuc.getDescription());
            tvLike.setText(String.valueOf(khuvuc.getLike()));
            tvView.setText(String.valueOf(khuvuc.getView()));
            tvStar.setText(String.valueOf(khuvuc.getStar()));
        }

        public interface InterfaceKhuVuc{
            void callbackKhuVucFragment(int id);
        }
    }
}
