package com.binmobile.foodyapp.CustomView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.binmobile.foodyapp.Utils.ViewUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by nthanhphong on 9/15/2016.
 */

public class DownloadHinhTask extends AsyncTask<String,Void,Bitmap> {

    private ImageView imageView;

    public DownloadHinhTask(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            HttpURLConnection httpURLConnection;
            URL url=new URL(params[0]);
            //tao 1 ket noi len server
            httpURLConnection=(HttpURLConnection)url.openConnection();
            if( httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream stream=httpURLConnection.getInputStream();
                if(stream!=null){
                    return BitmapFactory.decodeStream(stream);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(bitmap!=null){
            imageView.setImageBitmap(bitmap);
        }
    }
}
