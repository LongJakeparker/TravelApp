//package com.greenacademy.travelapp.Activity.AsyncTask;
//
//import android.graphics.BitmapFactory;
//import android.os.AsyncTask;
//import android.support.v4.app.Fragment;
//
//import com.greenacademy.travelapp.Activity.Constant.Constant;
//import com.greenacademy.travelapp.Activity.Fragment.DiaDiemFragment;
//import com.greenacademy.travelapp.Activity.Fragment.KhuVucFragment;
//import com.greenacademy.travelapp.Activity.Model.DiaDiemDuLich;
//import com.greenacademy.travelapp.Activity.Model.KhuVucDuLich;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//
///**
// * Created by PhamNhuVu on 3/1/2017.
// */
//
//public class  GetKhuVucDiaDiemAsyncTask extends AsyncTask<String, D, Void> {
//    HttpURLConnection connection;
//    String type;
//    KhuVucFragment khuVucFragment;
//    DiaDiemFragment diaDiemFragment;
//
//    public GetKhuVucDiaDiemAsyncTask(String type, Fragment fragment) {
//        this.type = type;
//        switch (type){
//            case Constant.TYPE_DATA_KHU_VUC:
//                khuVucFragment = (KhuVucFragment) fragment;
//                break;
//            case Constant.TYPE_DATA_DIA_DIEM:
//                diaDiemFragment = (DiaDiemFragment) fragment;
//                break;
//        }
//
//    }
//
//    @Override
//    protected Void doInBackground(String... params) {
//        try {
//            initConnect(params[0]);
//
//            JSONObject jsonObject = null;
//            try {
//                jsonObject = new JSONObject(resultData());
//                switch (type){
//
//                    case Constant.TYPE_DATA_KHU_VUC:
//                        try {
//                            parseDataKhuVuc(jsonObject);
//                        } catch (MalformedURLException e) {
//                            e.printStackTrace();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        break;
//
//                    case Constant.TYPE_DATA_DIA_DIEM:
//                        try {
//                            parseDataDiaDiem(jsonObject);
//                        } catch (MalformedURLException e) {
//                            e.printStackTrace();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        break;
//                }
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private void initConnect(String url) throws IOException {
//        connection = (HttpURLConnection) (new URL(url)).openConnection();
//        connection.addRequestProperty    ("Accept", "application/json");
//        connection.setRequestMethod      ("GET");
//        connection.connect               ();
//    }
//
//    private String resultData(){
//        StringBuilder builder = new StringBuilder();
//        try {
//            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String inputLine;
//            while ((inputLine = in.readLine()) != null){
//                builder.append(inputLine);
//            }
//            in.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return builder.toString();
//    }
//
//    private Void parseDataKhuVuc(JSONObject jsonObject) throws JSONException, IOException {
//        JSONArray jsonArray                 = jsonObject.getJSONArray("KhuVucs");
//
//        JSONObject object;
//
//        for (int i = 0; i < jsonArray.length(); i++){
//            KhuVucDuLich khuvuc                 = new KhuVucDuLich();
//            object = (JSONObject) jsonArray.get(i);
//            khuvuc  .setId          (object.getInt      ("Id"));
//            khuvuc  .setTitle       (object.getString   ("TenKhuVuc"));
//            khuvuc  .setLink        (object.getString   ("LinkAnh"));
//            khuvuc  .setDescription (object.getString   ("MoTa"));
//            khuvuc  .setStar        (object.getInt      ("DanhGia"));
//            khuvuc  .setView        (object.getInt      ("SoLuotXem"));
//            khuvuc  .setLike        (object.getInt      ("YeuThich"));
//            khuvuc  .setImageKhuVuc (BitmapFactory.decodeStream(new URL(khuvuc.getLink()).openStream()));
//
//            publishProgress(khuvuc);
//        }
//        return null;
//    }
//
//    @Override
//    protected void onProgressUpdate( D... values) {
//        switch (type){
//            case Constant.TYPE_DATA_KHU_VUC:
//                khuVucFragment.updateData(values[0]);
//                break;
//            case Constant.TYPE_DATA_DIA_DIEM:
//
//        }
//
//    }
//
//    private ArrayList<DiaDiemDuLich> parseDataDiaDiem(JSONObject jsonObject) throws JSONException, IOException {
//        JSONArray jsonArray                 = jsonObject.getJSONArray("DiaDiems");
//        ArrayList<DiaDiemDuLich> arrDiaDiem = new ArrayList<>();
//        DiaDiemDuLich diadiem               = new DiaDiemDuLich();
//        JSONObject object;
//
//        for (int i = 0; i < jsonArray.length(); i++) {
//            object = (JSONObject) jsonArray.get(i);
//            diadiem  .setIntId          (object.getInt       ("Id"));
//            diadiem  .setStrTenDiaDiem  (object.getString    ("TenDiaDiem"));
//            diadiem  .setStrMoTa        (object.getString    ("MoTa"));
//            diadiem  .setDbDanhGia      (object.getDouble    ("DanhGia"));
//            diadiem  .setIntSoLuotXem   (object.getInt       ("SoLuotXem"));
//            diadiem  .setIntYeuThich    (object.getInt       ("YeuThich"));
//            diadiem  .setIntCheckIn     (object.getInt       ("CheckIn"));
//            diadiem  .setIntIdKhuVuc    (object.getInt       ("IdKhuVuc"));
//            diadiem  .setStrLinkAnh     (object.getString    ("LinkAnh"));
//            diadiem  .setDbLat          (object.getDouble    ("Lat"));
//            diadiem  .setDbLng          (object.getDouble    ("Lng"));
//            diadiem  .setStrDiaChi      (object.getString    ("DiaChi"));
//            diadiem  .setImageDiaDiem   (BitmapFactory.decodeStream(new URL(diadiem.getStrLinkAnh()).openStream()));
//
//            arrDiaDiem.add(diadiem);
//        }
//        return arrDiaDiem;
//    }
//
//    public interface CallbackGetDataDiaDiem{
//        <D> void resultData(ArrayList<D> data);
//    }
//}
