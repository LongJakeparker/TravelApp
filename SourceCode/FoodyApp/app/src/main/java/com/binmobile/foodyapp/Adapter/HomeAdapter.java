package com.binmobile.foodyapp.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.binmobile.foodyapp.Model.DiaDiem;
import com.binmobile.foodyapp.R;

import java.util.List;

/**
 * Created by nthanhphong on 9/1/2016.
 */

public class HomeAdapter extends RecyclerView.Adapter<StoreViewHolder> {

    private int[] mImageResIds;
    private String[] mNames;
    private String[] mMark;
    private String[] mAddress;
    private String[] mDistance;
    private String[] mImageUrl;
    private List<DiaDiem> listDiadiem;

    private LayoutInflater mLayoutInflater;

    public HomeAdapter(Context context, List<DiaDiem> diaDiems) {
        this.listDiadiem=diaDiems;
        mLayoutInflater = LayoutInflater.from(context);
        final Resources resources = context.getResources();
        mNames = resources.getStringArray(R.array.data_store_name);
        mMark = resources.getStringArray(R.array.data_store_mark);
        mAddress = resources.getStringArray(R.array.data_store_address);
        mDistance = resources.getStringArray(R.array.data_store_distance);
        mImageUrl = resources.getStringArray(R.array.link_image);
        final TypedArray typedArray = resources.obtainTypedArray(R.array.data_store_image);
        final int imageCount = mNames.length;
        mImageResIds = new int[imageCount];
        for (int i = 0; i < imageCount; i++) {
            mImageResIds[i] = typedArray.getResourceId(i, 0);
        }
        typedArray.recycle();
    }

    @Override
    public StoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StoreViewHolder(mLayoutInflater.inflate(R.layout.item_home, parent, false));
    }

    @Override
    public void onBindViewHolder(StoreViewHolder holder, int position) {
        holder.setData(listDiadiem.get(position));
    }

    @Override
    public int getItemCount() {
        return listDiadiem.size();
    }
}
