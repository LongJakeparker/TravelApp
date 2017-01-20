package com.greenacademy.travelapp.Activity.Utils;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.greenacademy.travelapp.Activity.Constant.Constant;

import java.util.ArrayList;

/**
 * Created by User on 1/12/2017.
 */

public class SignInGmail {

//    public static final int REQUEST_CODE_GOOGLE_SIGN_IN = 1564;

    GoogleApiClient googleApiClient;
    AppCompatActivity activity;
    GoogleSignInAccount googleSignInAccount;

    public SignInGmail(AppCompatActivity activity) {
        this.activity = activity;
    }

    // Khai báo thiết lập và tích hợp tính năng đăng nhập ở hàm onCreate
    public void startTichHopGoogleSignIn() {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestId()
                .build();

        googleApiClient = new GoogleApiClient.Builder(activity)
                .enableAutoManage(activity, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
    }

    // Tiến hành xử lý đăng nhập khi người dùng nhấn nút đăng nhập
    public void startSignIn() {
        Intent intentSignIn = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        activity.startActivityForResult(intentSignIn, Constant.REQUEST_CODE_GOOGLE_SIGN_IN);
    }


    // Xử lý kết quả trả về từ Intent trong onActivityResult
    public ArrayList<String> startXuLyKetQuaTraVe(Intent intent) {
        ArrayList<String> arrUserInfo = new ArrayList<>();
        // Đối tượng kết quả
        GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(intent);

        if (googleSignInResult.isSuccess()) {
            Log.d("Result", "Đăng nhập thành công");
            googleSignInAccount = googleSignInResult.getSignInAccount();
            String userName = googleSignInAccount.getDisplayName();
            String userEmail = googleSignInAccount.getEmail();
            String userId = googleSignInAccount.getId();
            Uri userUriPhoto = googleSignInAccount.getPhotoUrl();
            if (userUriPhoto != null){
                String userPhoto = userUriPhoto.toString();
                arrUserInfo.add(userId);
                arrUserInfo.add(userName);
                arrUserInfo.add(userEmail);
                arrUserInfo.add(userPhoto);
            } else {
                arrUserInfo.add(userId);
                arrUserInfo.add(userName);
                arrUserInfo.add(userEmail);
            }

            Log.d("Account", googleSignInAccount.toString());
        } else {
            Log.d("Result", "Đăng nhập thất bại");
        }
        return arrUserInfo;
    }


}
