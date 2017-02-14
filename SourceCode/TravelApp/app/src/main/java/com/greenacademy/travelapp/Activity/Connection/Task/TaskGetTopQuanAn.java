package com.greenacademy.travelapp.Activity.Connection.Task;

import android.os.AsyncTask;

import com.greenacademy.travelapp.Activity.Connection.CommunicateSever;
import com.greenacademy.travelapp.Activity.Connection.Interface.GetTopQuanAn;

/**
 * Created by GIT on 2/11/2017.
 */

public class TaskGetTopQuanAn extends AsyncTask<String, String, String> {
    CommunicateSever communicateSever;
    GetTopQuanAn getTopQuanAn;

    public TaskGetTopQuanAn(GetTopQuanAn getTopQuanAn) {
        this.getTopQuanAn = getTopQuanAn;
        communicateSever = new CommunicateSever();
    }

    @Override
    protected String doInBackground(String... params) {
        return communicateSever.getServer(params[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        getTopQuanAn.ketquaTopQuanAn(s);
    }
}
