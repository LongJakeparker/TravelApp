package com.greenacademy.travelapp.Activity.LoginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.greenacademy.travelapp.Activity.Constant.Constant;
import com.greenacademy.travelapp.Activity.LoginActivity.InterfaceLogin.CheckUser;
import com.greenacademy.travelapp.Activity.MainActivity;
import com.greenacademy.travelapp.Activity.Utils.SignInGmail;
import com.greenacademy.travelapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener, CheckUser {
    LoginButton btnlgnFacebook;
    CallbackManager callbackManager;
    SignInButton btnSigninGoogle;
    SignInGmail signInGmail;
    Button btnDangNhap;
    TaskLogin taskLogin;
    TextView txtChuaCoTaiKhoan;
    String LOGIN_ERROR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnSigninGoogle = (SignInButton) findViewById(R.id.btnSigninGoogle);
        btnlgnFacebook = (LoginButton) findViewById(R.id.buttonLoginFacebook);
        btnDangNhap = (Button) findViewById(R.id.buttonLogin);
        callbackManager = CallbackManager.Factory.create();
        signInGmail = new SignInGmail(this);
        txtChuaCoTaiKhoan = (TextView) findViewById(R.id.textViewChuaCoTaiKhoan);
        LOGIN_ERROR = getResources().getString(R.string.login_error);

        // phần Facebook
        if (Constant.INTERNET_CONNECTION){
            if (AccessToken.getCurrentAccessToken() != null) {
                toiManHinhChinh();
            }
        }

        btnlgnFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                layDuLieuFacebook(loginResult);
                toiManHinhChinh();
            }

            @Override
            public void onCancel() {
//                Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), LOGIN_ERROR, Toast.LENGTH_LONG).show();
            }
        });

        // phần Google
        signInGmail.startTichHopGoogleSignIn();

        btnSigninGoogle.setOnClickListener(this);

        // phần Đăng nhập bình thường
        btnDangNhap.setOnClickListener(this);
        txtChuaCoTaiKhoan.setOnClickListener(this);

    }


    private void toiManHinhChinh() {
        // MainActivity là màn hình chính sau khi login thành công
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    private void toiManHinhDangKy(){
        //MainActivityDK là màn hình đăng ký
//        Intent intent = new Intent(getApplicationContext(), MainActivityDK.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
    }

    private void layDuLieuFacebook(final LoginResult loginResult)
    {
        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        // Application code
                        try {

                            String firstName = response.getJSONObject().getString("first_name");
                            String lastName = response.getJSONObject().getString("last_name");
                            String userID = AccessToken.getCurrentAccessToken().getUserId();
                            String avatar = "http://graph.facebook.com/" + userID + "/picture?type=large";

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,email,first_name,last_name,gender, birthday");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            Xử lý sự kiện nút Đăng nhập Gmail
            case R.id.btnSigninGoogle:
                signInGmail.startSignIn();
                break;
            case R.id.buttonLogin:
                break;
            case R.id.textViewChuaCoTaiKhoan:
                toiManHinhDangKy();
                break;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Trả về kết quả đăng nhập
        if (requestCode == Constant.REQUEST_CODE_GOOGLE_SIGN_IN) {
            ArrayList<String> arrUserInfo = signInGmail.startXuLyKetQuaTraVe(data);
            if (arrUserInfo != null){
                toiManHinhChinh();
            }else {
                Toast.makeText(getApplicationContext(), LOGIN_ERROR, Toast.LENGTH_LONG).show();
            }
        }
        //kết quả Facebook
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void ketqua(String kq) {

    }
}
