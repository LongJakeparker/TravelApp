package com.greenacademy.travelapp.Activity.Connection.Task;

import android.os.AsyncTask;

import com.greenacademy.travelapp.Activity.Connection.CommunicateSever;
import com.greenacademy.travelapp.Activity.Connection.Interface.GetNearQuanAn;

/**
 * Created by GIT on 2/11/2017.
 */

public class TaskGetNearQuanAn extends AsyncTask<String, String, String> {
    CommunicateSever communicateSever;
    GetNearQuanAn getNearQuanAn;

    public TaskGetNearQuanAn(GetNearQuanAn getNearQuanAn) {
        this.getNearQuanAn = getNearQuanAn;
        communicateSever = new CommunicateSever();
    }

    @Override
    protected String doInBackground(String... params) {
        return communicateSever.getServer(params[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        getNearQuanAn.ketquaNearQuan(s);
    }
}
