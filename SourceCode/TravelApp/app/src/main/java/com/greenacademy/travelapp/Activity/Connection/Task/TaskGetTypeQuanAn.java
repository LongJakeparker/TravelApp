package com.greenacademy.travelapp.Activity.Connection.Task;

import android.content.Context;
import android.os.AsyncTask;

import com.greenacademy.travelapp.Activity.Connection.CommunicateSever;
import com.greenacademy.travelapp.Activity.Connection.Interface.GetTypeQuanAn;
import com.greenacademy.travelapp.Activity.Model.QuanAnChiTiet;

import java.util.ArrayList;

/**
 * Created by GIT on 2/11/2017.
 */

public class TaskGetTypeQuanAn extends AsyncTask<String, String, String> {
    CommunicateSever communicateSever;
    GetTypeQuanAn getTypeQuanAn;

    public TaskGetTypeQuanAn(GetTypeQuanAn getTypeQuanAn) {
        this.getTypeQuanAn = getTypeQuanAn;
        communicateSever = new CommunicateSever();
    }

    @Override
    protected String doInBackground(String... params) {
        return communicateSever.getServer(params[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        getTypeQuanAn.ketquaType(s);
    }
}
