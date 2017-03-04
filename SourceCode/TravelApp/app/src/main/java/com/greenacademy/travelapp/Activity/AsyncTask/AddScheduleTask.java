package com.greenacademy.travelapp.Activity.AsyncTask;

import android.os.AsyncTask;

import com.greenacademy.travelapp.Activity.Enum.StatusAddSchedule;
import com.greenacademy.travelapp.Activity.Interface.AddScheduleInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jake on 2/25/2017.
 */

public class AddScheduleTask extends AsyncTask<String, String, StatusAddSchedule> {
    private int IdDiaDiem;
    private int IdLoaiDiaDiem;
    private String Description;
    private AddScheduleInterface callBackData;

    public AddScheduleTask(int idDiaDiem, int idLoaiDiaDiem, String description, AddScheduleInterface callBackData) {
        IdDiaDiem = idDiaDiem;
        IdLoaiDiaDiem = idLoaiDiaDiem;
        Description = description;
        this.callBackData = callBackData;
    }

    @Override
    protected StatusAddSchedule doInBackground(String... params) {
        try{
            URL url = new URL("http://103.237.147.137:9045/MyTravel/ThemDiem");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.addRequestProperty("Content-Type"," application/json");
            connection.addRequestProperty("Accept","text/json");
            connection.setRequestMethod("POST");
            connection.connect();

            OutputStream os = connection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("IdDiaDiem", IdDiaDiem);
            jsonObject.put("LoaiDiaDiem", IdLoaiDiaDiem);
            jsonObject.put("NoiDungCheckIn", Description);
            bufferedWriter.write(jsonObject.toString());
            bufferedWriter.close();
            os.close();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            StringBuilder data = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null){
                data.append(line);
            }
            JSONObject object = new JSONObject(data.toString());
            if (object.getInt("Status") == 1)
                return StatusAddSchedule.THANH_CONG;
            return StatusAddSchedule.THAT_BAI;
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
    protected void onPostExecute(StatusAddSchedule statusAddSchedule) {
        this.callBackData.CallBackData(statusAddSchedule);
    }
}
