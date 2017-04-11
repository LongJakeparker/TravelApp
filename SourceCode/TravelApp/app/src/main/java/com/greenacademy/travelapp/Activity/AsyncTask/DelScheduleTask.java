package com.greenacademy.travelapp.Activity.AsyncTask;

import android.os.AsyncTask;

import com.greenacademy.travelapp.Activity.Enum.StatusDelSchedule;
import com.greenacademy.travelapp.Activity.Interface.DelScheduleInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Jake on 2/25/2017.
 */

public class DelScheduleTask extends AsyncTask<String, String, StatusDelSchedule> {
    private int IdDiaDiemChuyenDi;
    private DelScheduleInterface callBackData;

    public DelScheduleTask(int idDiaDiemChuyenDi, DelScheduleInterface callBackData) {
        IdDiaDiemChuyenDi = idDiaDiemChuyenDi;
        this.callBackData = callBackData;
    }

    @Override
    protected StatusDelSchedule doInBackground(String... params) {
        try{
            URL url = new URL("http://103.237.147.137:9045/MyTravel/XoaDiaDiemChuyenDi?idDiaDiemChuyenDi="+ IdDiaDiemChuyenDi);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("Content-Type"," application/json");
            connection.addRequestProperty("Accept","text/json");
            connection.setRequestMethod("GET");
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader bufferedReader = new BufferedReader(isr);
                String line="";
                StringBuilder testData = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null){
                    testData.append(line);
                }
                String data = testData.toString();
                JSONObject jsonObject = new JSONObject(data);
                if (jsonObject.getInt("Status") == 1)
                    return StatusDelSchedule.THANH_CONG;
                
                return StatusDelSchedule.THAT_BAI;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(StatusDelSchedule statusDelSchedule) {
        this.callBackData.CallBackData(statusDelSchedule);
    }
}
