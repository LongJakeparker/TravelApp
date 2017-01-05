package com.binmobile.foodyapp.Adapter.CheckIn;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.binmobile.foodyapp.CustomView.CheckImageCallback;
import com.binmobile.foodyapp.R;
import com.binmobile.foodyapp.Utils.ViewUtils;

import static android.R.attr.width;

/**
 * Created by nthanhphong on 9/29/2016.
 */

public class DanhSachChonHolder extends RecyclerView.ViewHolder {

    public ImageView anh;
    public CheckBox checkBox;

    public DanhSachChonHolder(View itemView) {
        super(itemView);
        anh = (ImageView) itemView.findViewById(R.id.hinh);
        //set kích thước hình ảnh hiển thị
        anh.setLayoutParams(new FrameLayout.LayoutParams(ViewUtils.convertDpToPixel(70, itemView.getContext()), ViewUtils.convertDpToPixel(70, itemView.getContext())));
        checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
        checkBox.setVisibility(View.GONE);
    }

    public void SetData(Bitmap bitmap) {
        if (anh != null) {
            if (bitmap != null) {
                anh.setImageBitmap(bitmap);
            }
        }
    }
}
