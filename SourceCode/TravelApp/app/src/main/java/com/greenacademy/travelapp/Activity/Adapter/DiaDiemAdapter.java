package com.greenacademy.travelapp.Activity.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenacademy.travelapp.Activity.Model.DiaDiemDuLich;
import com.greenacademy.travelapp.R;

import java.util.ArrayList;

/**
 * Created by PhamNhuVu on 2/25/2017.
 */

public class DiaDiemAdapter extends RecyclerView.Adapter<DiaDiemAdapter.DiaDiemVH> {

    ArrayList<DiaDiemDuLich> arrDiaDiem;
    DiaDiemVH.InterfaceDiaDiemDuLich interfaceDiaDiemDuLich;

    public DiaDiemAdapter(ArrayList<DiaDiemDuLich> arrDiaDiem, DiaDiemVH.InterfaceDiaDiemDuLich interfaceDiaDiemDuLich) {
        this.arrDiaDiem = arrDiaDiem;
        this.interfaceDiaDiemDuLich = interfaceDiaDiemDuLich;
    }

    @Override
    public DiaDiemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DiaDiemVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_diadiem, parent, false));
    }

    @Override
    public void onBindViewHolder(DiaDiemVH holder, final int position) {
        DiaDiemDuLich diadiem = arrDiaDiem.get(position);
        holder.initView(diadiem);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceDiaDiemDuLich.callbackDiaDiemFragment(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrDiaDiem.size();
    }

    public static class DiaDiemVH extends RecyclerView.ViewHolder {
        View itemView;
        ImageView imageKhuVuc;
        TextView tvTitleKhuVuc, tvDescription, tvLike, tvView, tvStar;
        
        public DiaDiemVH(View itemView) {
            super(itemView);
            this.itemView = itemView;
            imageKhuVuc     = (ImageView) itemView.findViewById(R.id.image_DiaDiem);
            tvTitleKhuVuc   = (TextView) itemView.findViewById(R.id.tvTitle_DiaDiem);
            tvDescription   = (TextView) itemView.findViewById(R.id.tvDescription_DiaDiem);
            tvLike          = (TextView) itemView.findViewById(R.id.tvLike_DiaDiem);
            tvView          = (TextView) itemView.findViewById(R.id.tvView_DiaDiem);
            tvStar          = (TextView) itemView.findViewById(R.id.tvStar_DiaDiem);
        }
        public void initView(DiaDiemDuLich diadiem){
            tvTitleKhuVuc.setText(diadiem.getStrTenDiaDiem());
//            imageKhuVuc.setImageResource(diadiem.getStrLinkAnh());
            imageKhuVuc.setImageResource(R.drawable.image_khuvuc1_default);
            tvDescription.setText(diadiem.getStrMoTa());
            tvLike.setText(String.valueOf(diadiem.getIntYeuThich()));
            tvView.setText(String.valueOf(diadiem.getIntSoLuotXem()));
            tvStar.setText(String.valueOf(diadiem.getFlDanhGia()));
        }

        public interface InterfaceDiaDiemDuLich{
            void callbackDiaDiemFragment(int position);
        }
    }
}
