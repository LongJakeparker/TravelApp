package com.greenacademy.travelapp.Activity.AsyncTask;

import android.os.AsyncTask;

import com.greenacademy.travelapp.Activity.Constant.Constant;
import com.greenacademy.travelapp.Activity.Model.DiaDiemDuLich;
import com.greenacademy.travelapp.Activity.Model.KhuVucDuLich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by PhamNhuVu on 3/1/2017.
 */

public class GetKhuVucDiaDiemAsyncTask extends AsyncTask<String, Void, String> {
    HttpURLConnection connection;
    String type;
    CallbackGetDataDiaDiem callbackGetDataDiaDiem;

    public GetKhuVucDiaDiemAsyncTask(CallbackGetDataDiaDiem callbackGetDataDiaDiem) {
        this.callbackGetDataDiaDiem = callbackGetDataDiaDiem;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            type =      params[0];
            initConnect(params[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultData();
    }

    @Override
    protected void onPostExecute(String string) {
        super.onPostExecute(string);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(string);
            switch (type){

                case Constant.TYPE_DATA_KHU_VUC:
                    callbackGetDataDiaDiem.resultData(parseDataKhuVuc(jsonObject));
                    break;

                case Constant.TYPE_DATA_DIA_DIEM:
                    callbackGetDataDiaDiem.resultData(parseDataDiaDiem(jsonObject));
                    break;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsonObject.toString();
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

    private ArrayList<KhuVucDuLich> parseDataKhuVuc(JSONObject jsonObject) throws JSONException {
        JSONArray jsonArray                 = jsonObject.getJSONArray("KhuVucs");
        ArrayList<KhuVucDuLich> arrKhuVuc   = new ArrayList<>();
        KhuVucDuLich khuvuc                 = new KhuVucDuLich();
        JSONObject object;

        for (int i = 0; i < jsonArray.length(); i++){
            object = (JSONObject) jsonArray.get(i);
            khuvuc  .setId          (object.getInt      ("Id"));
            khuvuc  .setTitle       (object.getString   ("TenKhuVuc"));
            khuvuc  .setLink        (object.getString   ("LinkAnh"));
            khuvuc  .setDescription (object.getString   ("MoTa"));
            khuvuc  .setStar        (object.getInt      ("DanhGia"));
            khuvuc  .setView        (object.getInt      ("SoLuotXem"));
            khuvuc  .setLike        (object.getInt      ("YeuThich"));
            arrKhuVuc.add(khuvuc);
        }
        return arrKhuVuc;
    }

    private ArrayList<DiaDiemDuLich> parseDataDiaDiem(JSONObject jsonObject) throws JSONException {
        JSONArray jsonArray                 = jsonObject.getJSONArray("DiaDiems");
        ArrayList<DiaDiemDuLich> arrDiaDiem = new ArrayList<>();
        DiaDiemDuLich diadiem               = new DiaDiemDuLich();
        JSONObject object;

        for (int i = 0; i < jsonArray.length(); i++) {
            object = (JSONObject) jsonArray.get(i);
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
        }
        return arrDiaDiem;
    }

    public interface CallbackGetDataDiaDiem{
        <D> void resultData(ArrayList<D> data);
    }
}
