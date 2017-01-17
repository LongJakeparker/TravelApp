package com.greenacademy.travelapp.Activity.LoginActivity.Facebook;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.greenacademy.travelapp.Activity.Connection.CheckInternetConnection;

/**
 * Created by User on 1/10/2017.
 */

public class InitialFacebook extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        CheckInternetConnection connection = new CheckInternetConnection(getApplicationContext());
        connection.start();
    }
}
