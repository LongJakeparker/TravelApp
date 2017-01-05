package com.binmobile.foodyapp.Adapter.CheckIn;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.binmobile.foodyapp.CustomView.CheckImageCallback;
import com.binmobile.foodyapp.R;
import com.binmobile.foodyapp.Utils.ViewUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by nthanhphong on 9/24/2016.
 */

public class DanhSachFileAdapter extends RecyclerView.Adapter<DanhSachFileHolder> {

    List<String> duongDanAnhList = new LinkedList<>();
    Context context;
    private LayoutInflater mLayoutInflater;
    private List<Bitmap> bitmapList = new LinkedList<>();
    private CheckImageCallback callback;

    public DanhSachFileAdapter(Context context, List<String> duongDanAnhList, CheckImageCallback callback) {
        this.context = context;
        this.duongDanAnhList = duongDanAnhList;
        mLayoutInflater = LayoutInflater.from(context);
        for (String duongDanAnh :
                duongDanAnhList) {
            Bitmap bitmap = BitmapFactory.decodeFile(duongDanAnh);
            if(bitmap!=null) {
                bitmap = ViewUtils.resizeBitmapByScale(bitmap, 0.2f, false);
                bitmapList.add(bitmap);
            }
        }
        this.callback=callback;
    }

    @Override
    public DanhSachFileHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DanhSachFileHolder(mLayoutInflater.inflate(R.layout.item_file_anh, parent, false),callback);
    }

    @Override
    public void onBindViewHolder(DanhSachFileHolder holder, int position) {
        holder.SetData(bitmapList.get(position));
    }

    @Override
    public int getItemCount() {
        return bitmapList.size();
    }
}
