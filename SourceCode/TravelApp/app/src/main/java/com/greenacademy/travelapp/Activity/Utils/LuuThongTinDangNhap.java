package com.greenacademy.travelapp.Activity.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 10/01/2017.
 */

public class LuuThongTinDangNhap {
    public final String DEFAULT_VALUES = "";

    public final String TYPE_LOGIN = "Type_login";
    public final String USER_ID = "user_id";
    public final String USER_NAME = "user_name";
    public final String USER_EMAIL = "user_email";
    public final String USER_LINK_PHOTO = "user_link_photo";

    int typeLogin;
    String userId;
    String userName;
    String userEmail;
    String userLinkPhoto;

    AppCompatActivity activity;
    SharedPreferences sharedPreferences;


    public LuuThongTinDangNhap(AppCompatActivity activity, int typeLogin, String userId, String userName, String userEmail, String userLinkPhoto) {
        sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);

        this.activity = activity;
        this.typeLogin = typeLogin;
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userLinkPhoto = userLinkPhoto;
    }

    public LuuThongTinDangNhap(AppCompatActivity activity, int typeLogin, String userId, String userName, String userLinkPhoto) {
        sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);

        this.activity = activity;
        this.typeLogin = typeLogin;
        this.userId = userId;
        this.userName = userName;
        this.userLinkPhoto = userLinkPhoto;
        this.userEmail = "";
    }

    public void startLuuThongTinNguoiDung(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_ID, userId);
        editor.putInt(TYPE_LOGIN, typeLogin);
        editor.putString(USER_NAME, userName);
        editor.putString(USER_EMAIL, userEmail);
        editor.putString(USER_LINK_PHOTO, userLinkPhoto);
        editor.commit();
    }

    public String startLayId(){
        return sharedPreferences.getString(USER_ID, DEFAULT_VALUES);
    }

    public int startLayTypeLogin(){
        return sharedPreferences.getInt(TYPE_LOGIN, -1);
    }

    public String startLayHoTen(){
        return sharedPreferences.getString(USER_NAME, DEFAULT_VALUES);
    }

    public String startLayEmail(){
        return sharedPreferences.getString(USER_EMAIL, DEFAULT_VALUES);
    }

    public String startLayLinkPhoto(){
        return sharedPreferences.getString(USER_LINK_PHOTO, DEFAULT_VALUES);
    }

    public void clearThongTin(){
        sharedPreferences.edit().clear().commit();
    }

}
