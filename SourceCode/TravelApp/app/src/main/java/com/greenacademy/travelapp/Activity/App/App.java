package com.greenacademy.travelapp.Activity.App;

import android.app.Application;
import android.support.annotation.BoolRes;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.greenacademy.travelapp.Activity.Utils.SharePreference.BooleanPrefs;
import com.greenacademy.travelapp.Activity.Utils.SharePreference.StringPrefs;

/**
 * Created by nthan on 1/14/2017.
 */

public class App extends Application {
    //khai báo các biến share preference sử dụng
    public BooleanPrefs GioiThieuLanDau = new BooleanPrefs(this,"GioiThieuLanDau",false);
    public StringPrefs FacebookId = new StringPrefs(this,"FacebookId","");

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }
}
