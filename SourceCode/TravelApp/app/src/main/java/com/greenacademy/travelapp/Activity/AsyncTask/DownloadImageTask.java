package com.greenacademy.travelapp.Activity.AsyncTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;

/**
 * Created by Jake on 2/24/2017.
 */

public class DownloadImageTask extends AsyncTask<String, String, Bitmap> {
    String stringUrl;
    ImageView imageView;
    public static Hashtable<String, Bitmap> ht = new Hashtable<>();

    public DownloadImageTask(ImageView imageView,String stringUrl) {
        this.stringUrl = stringUrl;
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        if (ht.containsKey(stringUrl)){
            return ht.get(stringUrl);
        }
        Bitmap result = null;
        try{
            URL url = new URL(stringUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
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
        if (bitmap != null){
            ht.put(stringUrl, bitmap);
        }
        imageView.setImageBitmap(ht.get(stringUrl));
    }
}
