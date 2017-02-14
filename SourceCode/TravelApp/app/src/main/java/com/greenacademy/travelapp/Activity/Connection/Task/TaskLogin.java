package com.greenacademy.travelapp.Activity.Connection.Task;

import android.os.AsyncTask;

import com.greenacademy.travelapp.Activity.Connection.CommunicateSever;
import com.greenacademy.travelapp.Activity.Connection.Interface.CheckUser;
import com.greenacademy.travelapp.Activity.Model.UserLogin;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by User on 1/12/2017.
 */

public class TaskLogin extends AsyncTask<String, String, String> {
    UserLogin userLogin;
    CheckUser checkUser;
    CommunicateSever login;
    public TaskLogin(UserLogin userLogin, CheckUser checkUser){
        this.userLogin = userLogin;
        this.checkUser = checkUser;
        login = new CommunicateSever();
    }

    @Override
    protected String doInBackground(String... params) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.accumulate("Username", userLogin.Username);
            jsonObject.accumulate("MatKhau", userLogin.MatKhau);
            jsonObject.accumulate("KieuTk", userLogin.KieuTk);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return login.postServer(params[0], jsonObject.toString());
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        checkUser.ketqua(s);
    }
}
