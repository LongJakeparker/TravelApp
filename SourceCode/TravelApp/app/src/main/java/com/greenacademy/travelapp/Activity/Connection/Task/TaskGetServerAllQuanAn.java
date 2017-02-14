package com.greenacademy.travelapp.Activity.Connection.Task;

import android.os.AsyncTask;

import com.greenacademy.travelapp.Activity.Connection.CommunicateSever;
import com.greenacademy.travelapp.Activity.Connection.Interface.GetAllQuanAn;

/**
 * Created by GIT on 2/9/2017.
 */

public class TaskGetServerAllQuanAn extends AsyncTask<String, String, String> {

    CommunicateSever communicateSever;
    GetAllQuanAn getAllQuanAn;

    public TaskGetServerAllQuanAn(GetAllQuanAn getAllQuanAn) {
        communicateSever = new CommunicateSever();
        this.getAllQuanAn = getAllQuanAn;
    }

    @Override
    protected String doInBackground(String... strings) {

        return communicateSever.getServer(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        getAllQuanAn.ketquaAllQuanAn(s);
    }
}
