package com.binmobile.foodyapp.Services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import com.binmobile.foodyapp.R;

public class PlayMusicService extends Service {
    public PlayMusicService() {
    }

    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("service", "onCreate");
        //khởi tạo đối tượng play nhạc, và import file nhạc muốn play
        mediaPlayer=MediaPlayer.create(getApplicationContext(), R.raw.nhammatlai);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("service", "onStartCommand");
        //khởi chạy nhạc
        mediaPlayer.start();
        //nói cho android biết, nếu đủ ram thì hồi sinh lại cho tớ
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d("service", "onDestroy");
        mediaPlayer.stop();
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("service", "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d("service", "onRebind");
        super.onRebind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("service", "onBind");
        return null;
    }
}
