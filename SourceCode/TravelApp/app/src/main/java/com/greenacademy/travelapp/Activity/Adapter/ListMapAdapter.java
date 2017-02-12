package com.greenacademy.travelapp.Activity.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenacademy.travelapp.Activity.Item.ListMapItem;
import com.greenacademy.travelapp.R;

import java.util.List;


/**
 * Created by Administrator on 19/01/2017.
 */

public class ListMapAdapter extends ArrayAdapter {
    private List<ListMapItem> itemList;
    private Context context;
    private int resLayout;

    private ImageView iconBitmap;
    private TextView tvTitle;
    private TextView tvDescribe;
    private TextView tvDistance;

    public ListMapAdapter(Context context, int resource, List<ListMapItem> objects) {
        super(context, resource, objects);
        this.context = context;
        resLayout = resource;
        itemList = objects;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = View.inflate(context, resLayout, null);
            iconBitmap = (ImageView) convertView.findViewById(R.id.ivItemListMapNear);
            tvTitle = (TextView) convertView.findViewById(R.id.tvTitleItemListMapNear);
            tvDistance = (TextView) convertView.findViewById(R.id.tvDistanceItemListMapNear);
            tvDescribe = (TextView) convertView.findViewById(R.id.tvDescribeItemListMapNear);
        }

        ListMapItem listMapItem = itemList.get(position);
        iconBitmap.setImageResource(listMapItem.getClassifyLocation());
        tvTitle.setText(listMapItem.getNameLocation());
        tvDistance.setText(listMapItem.getDistanceLocation());
        tvDescribe.setText(listMapItem.getDescribeLocation());


        return convertView;
    }
}
