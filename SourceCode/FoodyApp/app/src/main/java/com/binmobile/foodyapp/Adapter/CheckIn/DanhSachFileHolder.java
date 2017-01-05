package com.binmobile.foodyapp.Adapter.CheckIn;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.binmobile.foodyapp.CustomView.CheckImageCallback;
import com.binmobile.foodyapp.R;

/**
 * Created by nthanhphong on 9/24/2016.
 */

public class DanhSachFileHolder extends RecyclerView.ViewHolder {
    public ImageView anh;
    public CheckBox checkBox;
    public CheckImageCallback callback;
    private Bitmap bitmap;

    public DanhSachFileHolder(View itemView, final CheckImageCallback callback) {
        super(itemView);
        anh = (ImageView) itemView.findViewById(R.id.hinh);
        checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
        this.callback=callback;
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    callback.CheckCallback(bitmap, true);
                }else{
                    callback.CheckCallback(bitmap, false);
                }
            }
        });
    }

    public void SetData(Bitmap bitmap) {
        this.bitmap=bitmap;
        if (anh != null) {
            if (bitmap != null) {
                anh.setImageBitmap(bitmap);
            }
        }
    }
}
