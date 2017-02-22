package com.greenacademy.travelapp.Activity.Connection.Task;

import android.os.AsyncTask;

import com.greenacademy.travelapp.Activity.Connection.CommunicateSever;
import com.greenacademy.travelapp.Activity.Connection.Interface.GetQuanAn;
import com.greenacademy.travelapp.Activity.Constant.Constant;

/**
 * Created by GIT on 2/9/2017.
 */

public class TaskGetServerAllQuanAn extends AsyncTask<String, String, String> {

    CommunicateSever communicateSever;
    GetQuanAn getQuanAn;

    public TaskGetServerAllQuanAn(GetQuanAn getQuanAn) {
        communicateSever = new CommunicateSever();
        this.getQuanAn = getQuanAn;
    }

    @Override
    protected String doInBackground(String... strings) {

        return communicateSever.getServer(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        getQuanAn.ketquaQuanAn(s, Constant.TATCAQUANAN_ADAPTER);
    }
}
