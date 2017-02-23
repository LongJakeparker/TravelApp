package com.greenacademy.travelapp.Activity.AsyncTask;

import android.os.AsyncTask;

import com.greenacademy.travelapp.Activity.Interface.ScheduleInterface;
import com.greenacademy.travelapp.Activity.Model.ChildModel;
import com.greenacademy.travelapp.Activity.Model.HeaderModel;

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
 * Created by Jake on 2/23/2017.
 */

public class ScheduleTask extends AsyncTask<String, String, List<HeaderModel>> {
    ScheduleInterface callBackData;


    public ScheduleTask(ScheduleInterface callBackData) {
        this.callBackData = callBackData;
    }

    @Override
    protected List<HeaderModel> doInBackground(String... params) {
        List<HeaderModel> result = new LinkedList<>();
        try{
            URL url = new URL("http://103.237.147.137:9045/MyTravel/MyTravel");
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
                JSONObject jobj = new JSONObject(testData);
                JSONArray jsonArray = jobj.getJSONArray("NgayChuyenDiTranfers");
                for (int i = 0; i < jsonArray.length() ; i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    HeaderModel headerModel = new HeaderModel();
                    headerModel = JSONParser(jsonObject, headerModel);
                    result.add(headerModel);
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

    @Override
    protected void onPostExecute(List<HeaderModel> headerModels) {
        this.callBackData.CallBackData(headerModels);
    }

    private HeaderModel JSONParser(JSONObject jsonObject, HeaderModel headerModel) {
        HeaderModel result = new HeaderModel();
        try{
            headerModel.setId(jsonObject.getInt("Id"));
            headerModel.setTitle(jsonObject.getString("NgayChuyenDi"));
            headerModel.setSumLike(jsonObject.getInt("SoLuotLike"));
            headerModel.setSumPic(jsonObject.getInt("SoLuongAnh"));
            List<ChildModel> listChild = new LinkedList<>();
            JSONArray childJSONArray = jsonObject.getJSONArray("DiaDiemChuyenDiTranfers");
            for (int i = 0; i < childJSONArray.length() ; i++) {
                JSONObject childJSONObject = childJSONArray.getJSONObject(i);
                ChildModel childModel = new ChildModel();
                childModel.setId(childJSONObject.getInt("Id"));
                childModel.setIdNgayChuyenDi(childJSONObject.getInt("IdNgayChuyenDi"));
                childModel.setIdDiaDiem(childJSONObject.getInt("IdDiaDiem"));
                childModel.setLoaiDiaDiem(childJSONObject.getInt("LoaiDiaDiem"));
                childModel.setDescribe(childJSONObject.getString("NoiDungCheckIn"));
                childModel.setTime(childJSONObject.getString("NgayCheckIn"));
                childModel.setLikes(childJSONObject.getInt("SoLuotLike"));
                childModel.setPics(childJSONObject.getInt("SoLuongAnh"));
                childModel.setIconDes(childJSONObject.getString("LinkAnh"));
                listChild.add(childModel);
            }
            headerModel.setChild(listChild);
            result = headerModel;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
