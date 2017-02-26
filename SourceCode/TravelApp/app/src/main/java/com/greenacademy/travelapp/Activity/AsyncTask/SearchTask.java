package com.greenacademy.travelapp.Activity.AsyncTask;

import android.os.AsyncTask;

import com.greenacademy.travelapp.Activity.Interface.SearchInterface;
import com.greenacademy.travelapp.Activity.Item.ListMapItem;

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
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jake on 2/24/2017.
 */

public class SearchTask extends AsyncTask<String, String, List<ListMapItem>> {
    String searchContent;
    SearchInterface callBackData;

    public SearchTask(String searchContent, SearchInterface callBackData) {
        this.searchContent = searchContent;
        this.callBackData = callBackData;
    }

    @Override
    protected List<ListMapItem> doInBackground(String... params) {
        List<ListMapItem> result = new LinkedList<>();
        try {
            URL url = new URL("http://103.237.147.137:9045/MyTravel/SearchDiaDiem?timDiaDiem="+searchContent);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("Accept", "text/json");
            connection.setRequestMethod("GET");
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream is = new BufferedInputStream(connection.getInputStream());
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader bufferedReader = new BufferedReader(isr);
                StringBuilder data = new StringBuilder();
                String chunks;
                while ((chunks = bufferedReader.readLine()) != null){
                    data.append(chunks);
                }
                String testData = data.toString();
                JSONObject obj = new JSONObject(testData);
                JSONArray jsonArray = obj.getJSONArray("TimDiemTranfers");
                for (int i = 0; i < jsonArray.length() ; i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    ListMapItem item = new ListMapItem();
                    item = JSONParser(jsonObject, item);
                    result.add(item);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    private ListMapItem JSONParser(JSONObject jsonObject, ListMapItem item) {
        ListMapItem result = new ListMapItem();
        try{
            item.setId(jsonObject.getInt("Id"));
            item.setIdLoaiDiaDiem(jsonObject.getInt("LoaiDiem"));
            item.setNameLocation(jsonObject.getString("Ten"));
            item.setClassifyLocation(jsonObject.getString("Hinh"));
            item.setRateLocation(jsonObject.getInt("DangGiaSao"));
            item.setAddressLocation(jsonObject.getString("DiaChi"));
            result = item;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(List<ListMapItem> listMapItems) {
        this.callBackData.CallBackData(listMapItems);
    }
}
