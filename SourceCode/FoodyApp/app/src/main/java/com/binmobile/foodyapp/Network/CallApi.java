package com.binmobile.foodyapp.Network;

import android.os.AsyncTask;
import android.telecom.Call;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by nthanhphong on 9/22/2016.
 */

public class CallApi {
    public CallBackData callBackData;
    public static CallApi callApi;
    public static String urlServer = "http://103.237.147.137:9090/";
    public static String urlDangKy = urlServer + "api/Users/";
    public static String urlDangNhap = urlServer + "api/Users/";
    public static String urlLayDiaDiem = urlServer + "api/DiaDiems/";

    public static CallApi getInstance() {
        if (callApi == null) {
            callApi = new CallApi();
        }
        return callApi;
    }

    public void SetCallback(CallBackData callBackData) {
        this.callBackData = callBackData;
    }

    ///hàm này được View gọi
    public void CallApiServer(ApiType apiType, List<HttpParam> header, JSONObject body) {
        String urlServer = "";
        switch (apiType) {
            case DANG_NHAP:
                urlServer = urlDangNhap;
                break;
            case GET_DIA_DIEM:
                urlServer = urlLayDiaDiem;
                break;
        }
        //tạo data nằm trên url gọi lên server
        if (header != null) {
            for (HttpParam httpPram : header) {
                if (header.indexOf(httpPram) != header.size() - 1) {
                    urlServer += httpPram.parram + "=" + httpPram.value + "&";
                } else {
                    urlServer += httpPram.parram + "=" + httpPram.value;
                }
            }
        }
        //tạo data nằm trong body của gói tin http request
        new AsyncCallServer(body, "GET", callBackData, apiType).execute(urlServer);
    }

    public class AsyncCallServer extends AsyncTask<String, Void, String> {


        public JSONObject body;
        public String method;
        public CallBackData callBack;
        public ApiType apiType;

        public AsyncCallServer(JSONObject body, String method, CallBackData callBack, ApiType apiType) {
            this.body = body;
            this.method = method;
            this.callBack = callBack;
            this.apiType = apiType;
        }

        @Override
        protected String doInBackground(String... params) {
            String responseString = null;
            try {
                URL url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.addRequestProperty("Accept", "text/json");
                //config giao thức truyền lên server
                conn.setRequestMethod(method);
                conn.connect();
                if (body != null) {
                    OutputStream output = conn.getOutputStream();
                    output.write(body.toString().getBytes());
                    output.flush();
                }
                if (conn.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                    InputStream inputStream = conn.getInputStream();
                    BufferedReader buff = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuffer stringBuff = new StringBuffer();
                    String line;
                    while ((line = buff.readLine()) != null) {
                        stringBuff.append(line);
                    }
                    responseString = stringBuff.toString();
                    return responseString;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String data) {
            callBack.Callback(apiType, data);
        }
    }


}
