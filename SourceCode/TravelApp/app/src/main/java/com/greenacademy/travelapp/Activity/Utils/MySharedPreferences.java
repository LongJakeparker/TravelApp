package com.greenacademy.travelapp.Activity.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 10/01/2017.
 */

public class MySharedPreferences {
    String Mydata;
    Activity MyActivity;
    SharedPreferences sharedPreferences;


    public MySharedPreferences(Activity activity,String mydata){
        Mydata = mydata;
        MyActivity = activity;
        sharedPreferences = MyActivity.getSharedPreferences(Mydata, Context.MODE_PRIVATE);
    }

    public void putString(String key, String data){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, data);
    }

    public String getString(String key){
        return sharedPreferences.getString(key,null);
    }
}
