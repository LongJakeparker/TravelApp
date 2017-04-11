package com.greenacademy.travelapp.Activity.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;
import com.greenacademy.travelapp.Activity.Adapter.AdapterQuanAn.KhachSanAdapter;
import com.greenacademy.travelapp.Activity.Model.KhachSanItem;
import com.greenacademy.travelapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by GIT on 2/11/2017.
 */

public class KhachSanFragment extends Fragment implements ChuyenDuLieuKhachSan {
    RecyclerView khachSanRecyclerView;
    ArrayList<KhachSanItem> khachSanItems = new ArrayList<>();
    RecyclerView.LayoutManager recyclerViewMn;
    private LatLng viTriCuaToi = null;
    private long idKhuVuc = -1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.khach_san_fragment_layout,container,false);
        khachSanRecyclerView = (RecyclerView) v.findViewById(R.id.khach_san_recycler_view);
        getKhachSanList();
        khachSanRecyclerView.setHasFixedSize(true);
        recyclerViewMn = new LinearLayoutManager(getContext());
        khachSanRecyclerView.setLayoutManager(recyclerViewMn);
        return v;
    }

    private ArrayList<KhachSanItem> getKhachSanList() {
        ArrayList<KhachSanItem> result = new ArrayList<>();
        TaiListKhachSan taiListKhachSan = new TaiListKhachSan(this);
        taiListKhachSan.execute();
        return result;
    }

    @Override
    public void ChuyenListKhachSan(String listKhachSanString) {
        khachSanItems = XuLyDuLieuKhachSanList(listKhachSanString);
        KhachSanAdapter khachSanAdapter = new KhachSanAdapter(khachSanItems);
        khachSanRecyclerView.setAdapter(khachSanAdapter);
    }

    private ArrayList<KhachSanItem> XuLyDuLieuKhachSanList(String listKhachSanString) {
        ArrayList<KhachSanItem> result = new ArrayList<>();
        if (listKhachSanString.equals("")){
            result = LayDataMacDinh();
        }else{
            Log.i("data json",listKhachSanString);
            try {
                JSONObject jsonObject = new JSONObject(listKhachSanString);
                JSONArray jsonArrayKhachSan = jsonObject.getJSONArray("KhachSans");
                for (int i = 0; i < jsonArrayKhachSan.length(); i++) {
                    JSONObject temp = jsonArrayKhachSan.getJSONObject(i);
                    KhachSanItem kSanTemp = new KhachSanItem();
                    kSanTemp.setTenKhachSan(temp.getString("Ten"));
                    Long l = Math.round(temp.getDouble("DanhGia")/2);
                    kSanTemp.setDanhGia(Integer.valueOf(l.intValue()));
                    kSanTemp.setGiaTien(String.valueOf(temp.getInt("Gia")));
                    kSanTemp.setDiaChi(temp.getString("Address"));
                    kSanTemp.setSoDanhGia(temp.getInt("YeuThich"));
                    kSanTemp.setId(temp.getInt("Id"));
                    kSanTemp.setMoTa(temp.getString("MoTa"));
                    kSanTemp.setCheckIn(temp.getInt("CheckIn"));
                    kSanTemp.setKhuVucId(temp.getInt("KhuVucId"));
                    kSanTemp.setLinkAnh(temp.getString("LinkAnh"));
                    kSanTemp.setLatLng(new LatLng(temp.getDouble("Lat"),temp.getDouble("Lng")));
                    result.add(kSanTemp);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private ArrayList<KhachSanItem> LayDataMacDinh() {
        ArrayList<KhachSanItem> result = new ArrayList<>();
        for (int i = 0; i<5;i++){
            KhachSanItem temp = new KhachSanItem();
            temp.setTenKhachSan("Khách sạn không tên");
            temp.setDanhGia(3);
            temp.setDiaChi("Ngay ngã tư quẹo phải");
            temp.setSoDanhGia(1000);
            temp.setGiaTien("5.000.000");
            temp.setDanhGia(5);
            result.add(temp);
        }
        return result;
    }

    private class TaiListKhachSan extends AsyncTask<Void,Integer,String>{
        private ChuyenDuLieuKhachSan chuyenDuLieu;

        public TaiListKhachSan(ChuyenDuLieuKhachSan chuyenDuLieu) {
            this.chuyenDuLieu = chuyenDuLieu;
        }

        @Override
        protected String doInBackground(Void... voids) {
            String duLieuListKhachSan = "";
            String urlString = "";
            if (viTriCuaToi!=null){
                double lat = viTriCuaToi.latitude;
                double lng = viTriCuaToi.longitude;
                urlString = "http://103.237.147.137:9045/KhachSan/KhachSangByNear?lat="+String.valueOf(lat)+"&lng="+String.valueOf(lng);
            }else if (idKhuVuc!=-1){
                urlString = "http://103.237.147.137:9045/KhachSan/KhachSanByKhuVuc?idKhuVuc="+ idKhuVuc;
            }else{
                urlString = "http://103.237.147.137:9045/KhachSan/AllKhachSan";
            }
            URL url;
            HttpURLConnection httpURLConnection = null;
            InputStream inputStream = null;
            try {
                url = new URL(urlString);//tao url
                httpURLConnection = (HttpURLConnection) url.openConnection();//tao va khoi chay httpurlconnection
                inputStream = new BufferedInputStream(httpURLConnection.getInputStream());//tao input stream lay du lieu gui ve
                duLieuListKhachSan = readStreamData(inputStream);//doc du lieu gui ve
                readStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (httpURLConnection!=null)
                httpURLConnection.disconnect();//ngat ket noi url
            }
            return duLieuListKhachSan;
        }

        private void readStream(InputStream inputStream) {
            Log.i("InputStream",inputStream.toString());
        }

        private String readStreamData(InputStream inputStream) {
            String result ="";
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));//tao buffer reader de doc du lieu tu input stream
            StringBuilder sb = new StringBuilder();//tao string builder
            String line;

            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append('\n');//doc tung dong du lieu gui ve
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();//dong input stream
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            result = sb.toString();//tao ket qua
            return  result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            chuyenDuLieu.ChuyenListKhachSan(s);
        }
    }

    public void setViTriCuaToi(LatLng viTriCuaToi) {
        this.viTriCuaToi = viTriCuaToi;
    }

    public void setIdKhuVuc(long idKhuVuc) {
        this.idKhuVuc = idKhuVuc;
    }
}

  interface ChuyenDuLieuKhachSan{
    void ChuyenListKhachSan(String listKhachSanString);
}
