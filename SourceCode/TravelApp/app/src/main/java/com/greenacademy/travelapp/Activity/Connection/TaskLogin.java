package com.greenacademy.travelapp.Activity.Connection;

import android.os.AsyncTask;

import com.greenacademy.travelapp.Activity.LoginActivity.InterfaceLogin.CheckUser;
import com.greenacademy.travelapp.Activity.LoginActivity.UserLogin;

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
 * Created by User on 1/12/2017.
 */

public class TaskLogin extends AsyncTask<String, String, String> {
    UserLogin userLogin;
    CheckUser checkUser;
    public TaskLogin(UserLogin userLogin, CheckUser checkUser){
        this.userLogin = userLogin;
        this.checkUser = checkUser;
    }

    //post lên server

    /**
     *
     * @param link: link url
     * @param json: chuoi json
     * @return: chuoi ket qua tu server
     *
     */

    public String readURLPost(String link, String json){
        StringBuilder stringBuilder = new StringBuilder();

        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("POST");
            connection.connect();

            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.write(json);
            writer.close();
            outputStream.close();

            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;

            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }
            bufferedReader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    @Override
    protected String doInBackground(String... params) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.accumulate("Username", userLogin.Username);
            jsonObject.accumulate("MatKhau", userLogin.MatKhau);
            jsonObject.accumulate("KieuTk", userLogin.KieuTk);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return readURLPost(params[0], jsonObject.toString());
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        checkUser.ketqua(s);
    }
}
