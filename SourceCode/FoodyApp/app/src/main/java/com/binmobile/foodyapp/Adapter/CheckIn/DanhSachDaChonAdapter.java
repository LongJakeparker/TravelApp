package com.binmobile.foodyapp.Adapter.CheckIn;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.binmobile.foodyapp.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by nthanhphong on 9/29/2016.
 */

public class DanhSachDaChonAdapter extends RecyclerView.Adapter<DanhSachChonHolder> {
    private LayoutInflater mLayoutInflater;
    private List<Bitmap> data=new LinkedList<>();
    private Context context;

    public DanhSachDaChonAdapter(Context context) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void AddData(Bitmap bitmap, boolean isCheck){
        if(isCheck){
            if(!CheckInData(bitmap))
                data.add(bitmap);
        }else{
            if(CheckInData(bitmap))
                data.remove(bitmap);
        }
        notifyDataSetChanged();
    }

    public boolean CheckInData(Bitmap bitmap){
        return data.contains(bitmap);
    }

    @Override
    public DanhSachChonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DanhSachChonHolder(mLayoutInflater.inflate(R.layout.item_file_anh, parent, false));
    }

    @Override
    public void onBindViewHolder(DanhSachChonHolder holder, int position) {
        holder.SetData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
