package com.greenacademy.travelapp.Activity.LoginActivity;

import android.os.AsyncTask;

import com.greenacademy.travelapp.Activity.LoginActivity.InterfaceLogin.CheckUser;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by User on 1/12/2017.
 */

public class TaskLogin extends Thread {
    String email, password;
    CheckUser checkUser;
    public TaskLogin(String email, String password, CheckUser checkUser){
        this.email = email;
        this.password = password;
        this.checkUser = checkUser;
    }

    @Override
    public void run() {
        super.run();

    }

    private String docNoiDungServer(String theUrl)
    {
        StringBuilder content = new StringBuilder();

        try
        {
            URL url = new URL(theUrl);

            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }
}
