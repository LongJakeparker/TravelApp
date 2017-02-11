package com.greenacademy.travelapp.Activity.Connection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.greenacademy.travelapp.Activity.Constant.Constant;

import static com.greenacademy.travelapp.Activity.Constant.Constant.INTERNET_CONNECTION;

/**
 * Created by User on 1/14/2017.
 */

public class CheckInternetConnection extends Thread {
    Context context;

    public CheckInternetConnection(Context context){
        this.context = context;
    }

    @Override
    public void run() {
        super.run();
        Constant.INTERNET_CONNECTION = isNetworkAvailable(context);

        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Constant.INTERNET_CONNECTION = isNetworkAvailable(context);
                Toast.makeText(context, INTERNET_CONNECTION + "", Toast.LENGTH_SHORT).show();
            }
        };

        context.registerReceiver(receiver, intentFilter);
    }

    private boolean isNetworkAvailable(final Context context) {
        boolean result;
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null){
            if (networkInfo.isConnected()){
                result = true;
            }else {
                result = false;
            }
        }else {
            result = false;
        }
        return result;
    }
}