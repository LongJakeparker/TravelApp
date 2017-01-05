package com.binmobile.foodyapp.CustomView;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.binmobile.foodyapp.R;
import com.binmobile.foodyapp.Utils.ViewUtils;

import java.lang.ref.WeakReference;

/**
 * Created by nthanhphong on 9/8/2016.
 */

public class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {
    //tạo biến có thể android đi giải phóng vùng nhớ khi không dùng đến
    private final WeakReference<ImageView> imageViewReference;

    public ImageDownloaderTask(ImageView imageView) {
        imageViewReference = new WeakReference<ImageView>(imageView);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        return ViewUtils.DownloadBitmap(params[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (isCancelled()) {
            bitmap = null;
        }

        if (imageViewReference != null) {
            ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                } else {
                    Drawable placeholder = imageView.getContext().getResources().getDrawable(R.drawable.placeholder);
                    imageView.setImageDrawable(placeholder);
                }
            }
        }
    }
}