package com.greenacademy.travelapp.Activity.Adapter.AdapterQuanAn;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.greenacademy.travelapp.Activity.Model.KhachSanItem;
import com.greenacademy.travelapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by GIT on 2/11/2017.
 */

public class KhachSanAdapter extends RecyclerView.Adapter<KhachSanAdapter.ViewHolder> implements View.OnClickListener, chuyenAnh {
    private static final int KHACH_SAN_ITEM_POSITION = 1;
    private ArrayList<KhachSanItem> khachSanItems;

    public KhachSanAdapter(ArrayList<KhachSanItem> khachSanItems) {
        this.khachSanItems = khachSanItems;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.khach_san_list_view_item,null);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTenKhachSan.setText(khachSanItems.get(position).getTenKhachSan());
        holder.tvDiaChiKhachSan.setText(khachSanItems.get(position).getDiaChi());
        holder.tvSoDanhGia.setText("Đánh giá: "+khachSanItems.get(position).getSoDanhGia());
        StringBuilder giaTienString = new StringBuilder();
        int giaTienInt = Integer.valueOf(khachSanItems.get(position).getGiaTien());
        while (giaTienInt/10!=0){
            giaTienString.append(".");
            giaTienString.append(String.valueOf(giaTienInt/10));
            giaTienInt = giaTienInt%10;
        }
        String gia = giaTienString.toString();
        if (gia==""){
            gia = "0";
        }
        holder.tvGia.setText(gia + " VND");
        switch (khachSanItems.get(position).getDanhGia()){
            case 1:
                holder.favIcon.setImageResource(R.drawable.icon_fav_1);
                break;
            case 2:
                holder.favIcon.setImageResource(R.drawable.icon_fav_2);
                break;
            case 3:
                holder.favIcon.setImageResource(R.drawable.icon_fav_3);
                break;
            case 4:
                holder.favIcon.setImageResource(R.drawable.icon_fav_4);
                break;
            case 5:
                holder.favIcon.setImageResource(R.drawable.icon_fav_5);
                break;
        }

        downLoadImage downLoadImage = new downLoadImage(this,position,holder);
        downLoadImage.execute(khachSanItems.get(position).getLinkAnh());
        //holder.itemView.setTag(holder.itemView.getId(),position);
        holder.itemView.setTag(holder.itemView.getId(),position);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return khachSanItems.size();
    }

    @Override
    public void onClick(View view) {
        //int pos = (int) view.getTag(KHACH_SAN_ITEM_POSITION);
        //do something with this event plz!!!
        Toast.makeText(view.getContext(),"This pos" + view.getTag(view.getId()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void chuyenAnh(Bitmap bitmap, int pos, KhachSanAdapter.ViewHolder viewHolder) {
        viewHolder.danhGiaImage.setImageBitmap(bitmap);
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView favIcon;
        TextView tvTenKhachSan;
        TextView tvDiaChiKhachSan;
        TextView tvGia;
        TextView tvSoDanhGia;
        ImageView danhGiaImage;
        View itemView;

        ViewHolder(View itemView) {
            super(itemView);
            tvDiaChiKhachSan = (TextView) itemView.findViewById(R.id.tv_khach_san_item_dia_chi);
            tvTenKhachSan = (TextView) itemView.findViewById(R.id.khach_san_item_ten);
            tvGia = (TextView) itemView.findViewById(R.id.tv_khach_san_item_gia);
            tvSoDanhGia = (TextView) itemView.findViewById(R.id.tv_khach_san_item_danh_gia);
            danhGiaImage = (ImageView) itemView.findViewById(R.id.khach_san_item_hinh_anh);
            favIcon = (ImageView) itemView.findViewById(R.id.khach_san_item_fav_icon);
            this.itemView = itemView;
        }
    }

    private class downLoadImage extends AsyncTask<String,Void,Bitmap>{
        private chuyenAnh mchuyenAnh;
        private int pos;
        private KhachSanAdapter.ViewHolder viewHolder;
        public downLoadImage(chuyenAnh chuyenAnh, int pos, KhachSanAdapter.ViewHolder viewHolder){
            this.mchuyenAnh = chuyenAnh;
            this.pos = pos;
            this.viewHolder = viewHolder;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (IOException e) {
                // Log exception
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            mchuyenAnh.chuyenAnh(bitmap,this.pos,this.viewHolder);
        }
    }
}

interface chuyenAnh{
    void chuyenAnh(Bitmap bitmap, int pos, KhachSanAdapter.ViewHolder viewHolder);
}
