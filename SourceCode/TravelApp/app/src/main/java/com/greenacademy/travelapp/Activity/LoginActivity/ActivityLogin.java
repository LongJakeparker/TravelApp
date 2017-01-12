package com.greenacademy.travelapp.Activity.LoginActivity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.greenacademy.travelapp.Activity.Constant.Constant;
import com.greenacademy.travelapp.Activity.MainActivity;
import com.greenacademy.travelapp.R;

import org.json.JSONException;
import org.json.JSONObject;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    final int RC_SIGN_IN = 313;
    LoginButton btnlgnFacebook;
    CallbackManager callbackManager;

    SignInButton btnSigninGoogle;
    GoogleApiClient googleApiClient;
    GoogleSignInOptions googleSignInOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        Thiết lập các thông số yêu cầu đăng nhập
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
//      Đối tượng truy cập Google Api SignIn và các thông số được thiết lập ở đối tượng trên
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
//        Button Đăng nhập Gmail
        btnSigninGoogle = (SignInButton) findViewById(R.id.btnSigninGoogle);
        btnSigninGoogle.setOnClickListener(this);



        btnlgnFacebook = (LoginButton) findViewById(R.id.buttonLoginFacebook);
        callbackManager = CallbackManager.Factory.create();

        if (AccessToken.getCurrentAccessToken() != null) {
            toiManHinhChinh();
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
                Toast.makeText(getApplicationContext(), Constant.FACEBOOK_ERROR, Toast.LENGTH_LONG).show();
            }
        });

    }


    private void toiManHinhChinh() {
        // MainActivity là màn hình chính sau khi login thành công
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

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
                break;
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Trả về kết quả đăng nhập
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        }

        //kết quả Facebook
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleSignInResult(GoogleSignInResult result) {

        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

        } else {
            // Signed out, show unauthenticated UI.
        }
    }

}
