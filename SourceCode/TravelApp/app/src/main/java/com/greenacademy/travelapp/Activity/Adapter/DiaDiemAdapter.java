package com.greenacademy.travelapp.Activity.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by PhamNhuVu on 2/25/2017.
 */

public class DiaDiemAdapter extends RecyclerView.Adapter<DiaDiemAdapter.DiaDiemVH> {



    @Override
    public DiaDiemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(DiaDiemVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class DiaDiemVH extends RecyclerView.ViewHolder {
        public DiaDiemVH(View itemView) {
            super(itemView);
        }
    }
}
