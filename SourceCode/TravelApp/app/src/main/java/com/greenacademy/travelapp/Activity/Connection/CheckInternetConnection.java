package com.greenacademy.travelapp.Activity.Connection;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.greenacademy.travelapp.Activity.Constant.Constant;

/**
 * Created by User on 1/14/2017.
 */

public class CheckInternetConnection {
    Context context;
    BroadcastReceiver broadcastReceiver;

    public CheckInternetConnection(Context context){
        this.context = context;
    }

    public void start(){
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Constant.INTERNET_CONNECTION = isNetworkAvailable(context);
                Toast.makeText(context, Constant.INTERNET_CONNECTION + "", Toast.LENGTH_LONG).show();
            }
        };

        context.registerReceiver(broadcastReceiver, intentFilter);
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

}
