package com.binmobile.foodyapp.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.binmobile.foodyapp.R;

import java.util.ArrayList;

/**
 * Created by nthanhphong on 9/1/2016.
 */

public class CatalogAdapter extends BaseAdapter {
    private String[] data;
    private LayoutInflater inflater;
    private Context context;
    private int[] mImageResIds;

    public CatalogAdapter(Context context, String[] dataInput){
        inflater=LayoutInflater.from(context);
        this.data=dataInput;
        this.context=context;

        final Resources resources = context.getResources();
        this.data = resources.getStringArray(R.array.data_catalog_item);
        final TypedArray typedArray = context.getResources().obtainTypedArray(R.array.data_catalog_image);
        final int imageCount = this.data.length;
        mImageResIds = new int[imageCount];
        for (int i = 0; i < imageCount; i++) {
            mImageResIds[i] = typedArray.getResourceId(i, 0);
        }
        typedArray.recycle();
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final CatalogHolder holder;
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item_catalog, parent, false);
            holder = new CatalogHolder();
            assert view != null;
            holder.imageView = (ImageView) view.findViewById(R.id.img_item);
            holder.textView = (TextView) view.findViewById(R.id.txt_item);
            view.setTag(holder);
        } else {
            holder = (CatalogHolder) view.getTag();
        }
        holder.textView.setText(data[position]);
        holder.imageView.setImageResource(mImageResIds[position]);
        return view;
    }

}

class CatalogHolder {
    ImageView imageView;
    TextView textView;
}
