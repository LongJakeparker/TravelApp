package com.greenacademy.travelapp.Activity.LoginActivity;

import android.os.AsyncTask;

/**
 * Created by User on 1/12/2017.
 */

public class TaskLogin extends Thread {
    String email, password;
    public TaskLogin(String email, String password){
        this.email = email;
        this.password = password;
    }

    @Override
    public void run() {
        super.run();

    }
}
