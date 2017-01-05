package com.binmobile.foodyapp.Adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by nthanhphong on 9/24/2016.
 */

public class CheckInAdapter extends RecyclerView.Adapter<CheckInHolder> {

    List<Bitmap> data=new LinkedList<>();

    public void AddBitmap(Bitmap bitmap){
        data.add(bitmap);
        notifyDataSetChanged();
    }

    @Override
    public CheckInHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CheckInHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
