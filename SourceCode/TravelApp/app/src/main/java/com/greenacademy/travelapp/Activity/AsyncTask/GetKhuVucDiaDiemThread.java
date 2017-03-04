package com.greenacademy.travelapp.Activity.AsyncTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;

import com.greenacademy.travelapp.Activity.Constant.Constant;
import com.greenacademy.travelapp.Activity.Fragment.DiaDiemFragment;
import com.greenacademy.travelapp.Activity.Fragment.KhuVucFragment;
import com.greenacademy.travelapp.Activity.Model.DiaDiemDuLich;
import com.greenacademy.travelapp.Activity.Model.KhuVucDuLich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by PhamNhuVu on 3/2/2017.
 */

public class GetKhuVucDiaDiemThread extends Thread {
    String link;
    String type;
    Fragment fragment;
    DiaDiemFragment diaDiemFragment;
    KhuVucFragment khuVucFragment;
    HttpURLConnection connection;

    public GetKhuVucDiaDiemThread(Fragment fragment, String link, String type) {
        this.fragment = fragment;
        this.link = link;
        this.type = type;
    }

    @Override
    public void run() {
        JSONObject jsonObject = null;
        try {
            initConnect(link);
            jsonObject = new JSONObject(resultData());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        switch (type){
            case Constant.TYPE_DATA_KHU_VUC:
                khuVucFragment = (KhuVucFragment) fragment;
                try {
                    parseDataKhuVuc(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case Constant.TYPE_DATA_DIA_DIEM:
                diaDiemFragment = (DiaDiemFragment) fragment;
                try {
                    parseDataDiaDiem(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void initConnect(String url) throws IOException {
        connection = (HttpURLConnection) (new URL(url)).openConnection();
        connection.addRequestProperty    ("Accept", "application/json");
        connection.setRequestMethod      ("GET");
        connection.connect               ();
    }

    private String resultData(){
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null){
                builder.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    private void parseDataKhuVuc(JSONObject jsonObject) throws JSONException, IOException {
        JSONArray jsonArray                 = jsonObject.getJSONArray("KhuVucs");

        JSONObject object;
        for (int i = 0; i < jsonArray.length(); i++){
            final KhuVucDuLich khuvuc                 = new KhuVucDuLich();
            object = (JSONObject) jsonArray.get(i);
            khuvuc  .setId          (object.getInt      ("Id"));
            khuvuc  .setTitle       (object.getString   ("TenKhuVuc"));
            khuvuc  .setLink        (object.getString   ("LinkAnh"));
            khuvuc  .setDescription (object.getString   ("MoTa"));
            khuvuc  .setStar        (object.getInt      ("DanhGia"));
            khuvuc  .setView        (object.getInt      ("SoLuotXem"));
            khuvuc  .setLike        (object.getInt      ("YeuThich"));
            khuvuc  .setImageKhuVuc (BitmapFactory.decodeStream(new URL(khuvuc.getLink()).openStream()));
                try {
                    khuVucFragment.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            khuVucFragment.updateData(khuvuc);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }

        }
    }

    private void parseDataDiaDiem(JSONObject jsonObject) throws JSONException {
        JSONArray jsonArray                 = jsonObject.getJSONArray("DiaDiems");
        ArrayList<DiaDiemDuLich> arrDiaDiem = new ArrayList<>();
        JSONObject object;

        for (int i = 0; i < jsonArray.length(); i++) {
            object = (JSONObject) jsonArray.get(i);
            final DiaDiemDuLich diadiem = new DiaDiemDuLich();
            diadiem  .setIntId          (object.getInt       ("Id"));
            diadiem  .setStrTenDiaDiem  (object.getString    ("TenDiaDiem"));
            diadiem  .setStrMoTa        (object.getString    ("MoTa"));
            diadiem  .setDbDanhGia      (object.getDouble    ("DanhGia"));
            diadiem  .setIntSoLuotXem   (object.getInt       ("SoLuotXem"));
            diadiem  .setIntYeuThich    (object.getInt       ("YeuThich"));
            diadiem  .setIntCheckIn     (object.getInt       ("CheckIn"));
            diadiem  .setIntIdKhuVuc    (object.getInt       ("IdKhuVuc"));
            diadiem  .setStrLinkAnh     (object.getString    ("LinkAnh"));
            diadiem  .setDbLat          (object.getDouble    ("Lat"));
            diadiem  .setDbLng          (object.getDouble    ("Lng"));
            diadiem  .setStrDiaChi      (object.getString    ("DiaChi"));
            Bitmap bitmap = getBitmap(diadiem.getStrLinkAnh());
            if (bitmap == null){
                continue;
            } else {
                diadiem  .setImageDiaDiem   (bitmap);
                arrDiaDiem.add(diadiem);
                    try {
                        diaDiemFragment.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                diaDiemFragment.updateData(diadiem);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
            }
        }
        final ArrayList<DiaDiemDuLich> topDiaDiem = new ArrayList<>();
        for (int j = 0; j < arrDiaDiem.size(); j++){
            DiaDiemDuLich diadiem = arrDiaDiem.get(j);
            if (j < 5){
                topDiaDiem.add(diadiem);
            } else {
                for (int i = 0; i < 5; i++){
                    if (diadiem.getIntYeuThich() > arrDiaDiem.get(i).getIntYeuThich()){
                        topDiaDiem.add(i, diadiem);
                        topDiaDiem.remove(topDiaDiem.size()-1);
                    }
                }
            }
        }

        try {
            diaDiemFragment.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    diaDiemFragment.updateTopData(topDiaDiem);
                }
            });
        } catch (Exception e) {

        }
    }

    private Bitmap getBitmap(String link){
        Bitmap bitmap = null;
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(500);
            connection.setDoInput(true);
            connection.connect();
            bitmap = BitmapFactory.decodeStream(connection.getInputStream());
        } catch (IOException e) {
            bitmap = null;
        }
        return bitmap;
    }


}
