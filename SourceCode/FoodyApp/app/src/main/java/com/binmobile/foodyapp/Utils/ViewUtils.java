package com.binmobile.foodyapp.Utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by nthanhphong on 9/8/2016.
 */

public class ViewUtils {

    /**
     * hàm download hình ảnh từ link
     *
     * @param url
     * @return
     */
    public static Bitmap DownloadBitmap(String url) {
        HttpURLConnection urlConnection = null;
        try {
            URL uri = new URL(url);
            urlConnection = (HttpURLConnection) uri.openConnection();
            int statusCode = urlConnection.getResponseCode();
            if (statusCode != HttpsURLConnection.HTTP_OK) {
                return null;
            }

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (Exception e) {
            urlConnection.disconnect();
            Log.w("ViewUtils", "Error downloading image from " + url);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }

    //hàm lấy danh sách ảnh từ sdcard
    public static List<String> GetImageSdCard() {
        List<String> result = new LinkedList<>();
        String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
        //lấy forder gốc chứa ảnh
        File sdCardFile = new File(sdcard + "/DCIM/Camera/");
        //lấy danh sách ảnh
        File[] listImage = sdCardFile.listFiles();
        if (listImage != null) {
            //lấy danh sách đường dẫn ảnh
            for (File file :
                    listImage) {
                result.add(file.getAbsolutePath());
            }
        }
        //kết quả trả vể
        return result;
    }

    public static Bitmap resizeBitmap(Bitmap bitmap, int width, int height) {
        return bitmap.createScaledBitmap(bitmap, width, height, false);
    }

    //chuyển dp thành pixel
    public static int convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return (int) px;
    }

    public static Bitmap resizeBitmapByScale(Bitmap bitmap, float scale, boolean recycle) {
        int width = Math.round(bitmap.getWidth() * scale);
        int height = Math.round(bitmap.getHeight() * scale);
        if (width == bitmap.getWidth() && height == bitmap.getHeight()) return bitmap;
        Bitmap target = Bitmap.createBitmap(width, height, getConfig(bitmap));
        Canvas canvas = new Canvas(target);
        canvas.scale(scale, scale);
        Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG | Paint.DITHER_FLAG);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        if (recycle) bitmap.recycle();
        return target;
    }

    private static Bitmap.Config getConfig(Bitmap bitmap) {
        Bitmap.Config config = bitmap.getConfig();
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        return config;
    }


    public static int congso(int a, int b) {
        return a + b;
    }
}
