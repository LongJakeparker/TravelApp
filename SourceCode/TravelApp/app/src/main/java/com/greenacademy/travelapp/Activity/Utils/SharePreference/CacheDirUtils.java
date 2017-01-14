package com.greenacademy.travelapp.Activity.Utils.SharePreference;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;

/**
 * Created by nthanhphong on 10/3/2016.
 */

public class CacheDirUtils {
    private static final String TAG = "CacheDirUtils";

    /**
     * Get a usable cache directory (external if available, internal otherwise).
     *
     * @param context    The context to use
     * @param uniqueName A unique directory name to append to the cache dir
     * @return The cache dir
     */
    public static File getDiskCacheDir(Context context, String uniqueName) {
//        Log.d(TAG, "getDiskCacheDir uniqueName=" + uniqueName);
        String cachePath = null;
        try {
            if (context == null) {
                Log.e(TAG, "Context is null, can not getDiskCache");
                return null;
            }
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !isExternalStorageRemovable()) {
                //get external dir.
                File dir = getExternalCacheDir(context);
                if (dir != null) {
                    cachePath = dir.getPath();
                }
            } else {
                File dir = context.getCacheDir();
                if (dir != null) {
                    cachePath = dir.getPath();
                }
            }
//            Log.d(TAG, "getDiskCacheDir uniqueName=" + uniqueName + ", cachePath=" + cachePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cachePath == null) {
            File dir = context.getFilesDir();
            cachePath = dir.getPath() + File.separator + "cache";
        }
        File cachFile = null;
        if (TextUtils.isEmpty(uniqueName)) {
            cachFile = new File(cachePath);
        } else {
            cachFile = new File(cachePath + File.separator + uniqueName);
        }
        if (!cachFile.exists()) {
            cachFile.mkdirs();
        }
//        Log.d(TAG, "getDiskCacheDir final path=" + cachFile.getPath());
        return cachFile;
    }

    /**
     * support create cache dir with cache name.
     *
     * @param context
     * @param cacheDirName
     * @return
     */
    public static File createCacheDir(Context context, String cacheDirName) {
        return new File(getApplicationExternalDir(context), cacheDirName);
    }

    /**
     * get application external dir
     *
     * @param context
     * @return
     */
    public static File getApplicationExternalDir(Context context) {
        File cacheDir = getDiskCacheDir(context, null);
        if (cacheDir == null) {
            return null;
        }
        return cacheDir.getParentFile();
    }

    /**
     * Get the external app cache directory.
     *
     * @param context The context to use
     * @return The external cache dir
     */
    @TargetApi(8)
    public static File getExternalCacheDir(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            return context.getExternalCacheDir();
        }

        // Before Froyo we need to construct the external cache dir ourselves
        final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache/";
        return new File(Environment.getExternalStorageDirectory().getPath() + cacheDir);
    }

    /**
     * Check if external storage is built-in or removable.
     *
     * @return True if external storage is removable (like an SD card), false
     * otherwise.
     */
    @TargetApi(9)
    public static boolean isExternalStorageRemovable() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return Environment.isExternalStorageRemovable();
        }
        return true;
    }

    /**
     * return path to directory tmp that used for application.
     *
     * @return folder of "temp"
     */
    public static File getTempDir(Context context) {
        File file = context.getFileStreamPath("temp");
        if (!file.exists()) {
            file.mkdir();
        }
        return context.getFileStreamPath("temp");
    }

    /**
     * delete files in temp dir.
     *
     * @param context
     */
    public static void deleteFilesInTempDir(Context context) {
        File dir = getTempDir(context);
        if (dir.exists()) {
            for (File file : dir.listFiles()) {
                file.delete();
            }
            dir.delete();
        }
    }

    /**
     * delete all files and folder in temp dir.
     *
     * @param context
     */
    public static void deleteTempDir(Context context) {
        File dir = getTempDir(context);
        deleteFolder(dir.getAbsolutePath());
    }

    /**
     * delete all files and subfolders in folder
     *
     * @return
     */
    public static void deleteFolder(String path) {
        if (path == null) {
            return;
        }
        File myNewFolder = new File(path);
        File files[] = myNewFolder.listFiles();
        for (int index = 0; index < files.length; index++) {
            try {
                if (files[index].isDirectory()) {
                    deleteFolder(files[index].getAbsolutePath());
                }
                files[index].delete();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        myNewFolder.delete();
    }

    /**
     * support get tmp file from temp directory of application
     *
     * @param context
     * @param filename
     * @return
     */
    public static File getTempFile(Context context, String filename) {
        return new File(getTempDir(context), filename);
    }
}

