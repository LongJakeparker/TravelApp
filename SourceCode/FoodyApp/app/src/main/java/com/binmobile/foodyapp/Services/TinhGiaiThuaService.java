package com.binmobile.foodyapp.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

public class TinhGiaiThuaService extends Service {
    public TinhGiaiThuaService() {
    }

    Handler handler;
    int bp;
    CountDownTimer timer;

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("service", "onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("service", "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d("service", "onRebind");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("service", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        Log.d("service", "onCreate");
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("service", "onBind");
        // TODO: Return the communication channel to the service.
        timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("DATA", TinhBinhPhuong(bp) + "");
                message.setData(bundle);
                handler.sendMessage(message);
                bp++;
                Log.d("dem","dem:"+bp);
            }

            @Override
            public void onFinish() {

            }
        };
        return binder;
    }

    public void StartServiceBind(){
        timer.start();
    }

    public void setHandler(Handler handler){
        this.handler = handler;
    }

    public void setSo(int bp){
        this.bp = bp;
    }

    private final IBinder binder=new LocalGiaiThuaBinder();

    //tạo 1 binder kết nối giữa giao diện người dùng với service
    // từ đây có thể gọi service và truyền data qua lại
    public class LocalGiaiThuaBinder extends Binder{
        public TinhGiaiThuaService getService(){
            return TinhGiaiThuaService.this;
        }
    }

    public int TinhBinhPhuong(int j){
        return j*j;
    }
}
