package com.greenacademy.travelapp.Activity.LoginActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.greenacademy.travelapp.Activity.Constant.Constant;
import com.greenacademy.travelapp.Activity.MainActivity;
import com.greenacademy.travelapp.R;

public class ActivityLogin extends AppCompatActivity {

    LoginButton btnlgnFacebook;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnlgnFacebook = (LoginButton) findViewById(R.id.buttonLoginFacebook);
        callbackManager = CallbackManager.Factory.create();

        if (AccessToken.getCurrentAccessToken() != null){
            toiManHinhChinh();
        }

        btnlgnFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                toiManHinhChinh();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), Constant.FACEBOOK_ERROR, Toast.LENGTH_LONG).show();
            }
        });

    }

    private void toiManHinhChinh(){
        // MainActivity là màn hình chính sau khi login thành công
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
