package com.greenacademy.travelapp.Activity.AsyncTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.greenacademy.travelapp.Activity.Interface.DownloadImageInterface;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jake on 2/24/2017.
 */

public class DownloadImageTask extends AsyncTask<String, String, Bitmap> {
    String stringUrl;
    DownloadImageInterface callBackData;

    public DownloadImageTask(String stringUrl, DownloadImageInterface callBackData) {
        this.stringUrl = stringUrl;
        this.callBackData = callBackData;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap result = null;
        try{
            URL url = new URL(stringUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream is = new BufferedInputStream(connection.getInputStream());
            Bitmap btm = BitmapFactory.decodeStream(is);
            result = btm;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        this.callBackData.CallBackData(bitmap);
    }
}
