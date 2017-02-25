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

import com.greenacademy.travelapp.Activity.AsyncTask.DownloadImageTask;
import com.greenacademy.travelapp.Activity.Interface.DownloadImageInterface;
import com.greenacademy.travelapp.Activity.Item.ListMapItem;
import com.greenacademy.travelapp.R;

import java.util.List;


/**
 * Created by Administrator on 19/01/2017.
 */

public class ListMapAdapter extends ArrayAdapter implements DownloadImageInterface{
    private List<ListMapItem> itemList;
    private Context context;
    private int resLayout;
    private ViewHolder viewHolder;
    private Bitmap bitmap;

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
            viewHolder = new ViewHolder();

            viewHolder.iconBitmap = (ImageView) convertView.findViewById(R.id.ivItemListMapNear);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvNameItemListMapNear);
            viewHolder.tvRate = (TextView) convertView.findViewById(R.id.tvRateItemListMapNear);
            viewHolder.tvAddress = (TextView) convertView.findViewById(R.id.tvAddressItemListMapNear);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ListMapItem listMapItem = itemList.get(position);
        new DownloadImageTask(listMapItem.getClassifyLocation(),this).execute();
        viewHolder.iconBitmap.setImageBitmap(this.bitmap);
        viewHolder.tvName.setText(listMapItem.getNameLocation());
        viewHolder.tvRate.setText(String.valueOf(listMapItem.getRateLocation()));
        viewHolder.tvAddress.setText(listMapItem.getAddressLocation());


        return convertView;
    }

    @Override
    public void CallBackData(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public static class ViewHolder{
        private ImageView iconBitmap;
        private TextView tvName;
        private TextView tvAddress;
        private TextView tvRate;
    }
}
