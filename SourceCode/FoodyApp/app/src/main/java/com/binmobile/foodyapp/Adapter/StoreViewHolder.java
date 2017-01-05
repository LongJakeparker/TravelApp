package com.binmobile.foodyapp.Adapter;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.binmobile.foodyapp.CustomView.DownloadHinhTask;
import com.binmobile.foodyapp.CustomView.ImageDownloaderTask;
import com.binmobile.foodyapp.Model.DiaDiem;
import com.binmobile.foodyapp.R;

import org.w3c.dom.Text;

/**
 * Created by nthanhphong on 9/1/2016.
 */

public class StoreViewHolder extends RecyclerView.ViewHolder {

    private ImageView imgStore;
    private TextView txtMark;
    private TextView txtName;
    private TextView txtAddress;
    private TextView txtDistance;

    public StoreViewHolder(View itemView) {
        super(itemView);
        imgStore = (ImageView) itemView.findViewById(R.id.img_store);
        txtMark = (TextView) itemView.findViewById(R.id.txt_mark);
        txtName = (TextView) itemView.findViewById(R.id.txt_name);
        txtAddress = (TextView) itemView.findViewById(R.id.txt_address);
        txtDistance = (TextView) itemView.findViewById(R.id.txt_distance);
    }

    public void setData(int imageId, String mark, String name, String address, String distance, String imageUrl) {
        if (imgStore != null) {
            new DownloadHinhTask(imgStore).execute(imageUrl,"hgds","ddddd");
        }
        txtMark.setText(mark);
        txtName.setText(name);
        txtAddress.setText(address);
        txtDistance.setText(distance);
    }

    public void setData(DiaDiem diaDiem){
        if (imgStore != null) {
            new DownloadHinhTask(imgStore).execute(diaDiem.anh,"hgds","ddddd");
        }
        txtMark.setText(""+diaDiem.diem);
        txtName.setText(diaDiem.ten);
        txtAddress.setText(diaDiem.diachi);
        txtDistance.setText("");
    }
}
