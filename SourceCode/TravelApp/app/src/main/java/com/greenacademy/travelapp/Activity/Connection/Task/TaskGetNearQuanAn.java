package com.greenacademy.travelapp.Activity.Connection.Task;

import android.os.AsyncTask;

import com.greenacademy.travelapp.Activity.Connection.CommunicateSever;
import com.greenacademy.travelapp.Activity.Connection.Interface.GetQuanAn;
import com.greenacademy.travelapp.Activity.Constant.Constant;

/**
 * Created by GIT on 2/11/2017.
 */

public class TaskGetNearQuanAn extends AsyncTask<String, String, String> {
    CommunicateSever communicateSever;
    GetQuanAn getQuanAn;

    public TaskGetNearQuanAn(GetQuanAn getQuanAn) {
        this.getQuanAn = getQuanAn;
        communicateSever = new CommunicateSever();
    }

    @Override
    protected String doInBackground(String... params) {
        return communicateSever.getServer(params[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        getQuanAn.ketquaQuanAn(s, Constant.QUANGANTOI_ADAPTER);
    }
}
