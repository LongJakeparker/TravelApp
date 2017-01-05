package com.binmobile.foodyapp.Fragment.Account;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.binmobile.foodyapp.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by nthanhphong on 9/21/2016.
 */

public class FragmentRegister extends Fragment {

    private View rootView;
    private Button register,login;
    private EditText username, password, name;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_register, null);
        register = (Button) rootView.findViewById(R.id.register_ok);
        login = (Button) rootView.findViewById(R.id.login_ok);
        username = (EditText) rootView.findViewById(R.id.login_username);
        password = (EditText) rootView.findViewById(R.id.login_password);
        name = (EditText) rootView.findViewById(R.id.login_name);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RegisterTask(getContext()).execute(username.getText().toString(), password.getText().toString(), name.getText().toString());
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.myContainer,new FragmentLogin(),"FragmentLogin").commit();
            }
        });
        return rootView;
    }

    class RegisterTask extends AsyncTask<String, String, String> {

        Context context;

        public RegisterTask(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... data) {
            String responseString = null;
            try {
                URL url = new URL("http://103.237.147.137:9090/User/DangKy?email=" + data[0] + "&matkhau=" + data[1] + "&tenHienThi=" + data[2]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //config server trả về data kiểu xml
                conn.addRequestProperty("Accept", "text/xml");
                //config giao thức truyền lên server
                conn.setRequestMethod("GET");
                conn.connect();
                if (conn.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                    Document doc = null;
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db;
                    try {
                        db = dbf.newDocumentBuilder();
                        doc = db.parse(conn.getInputStream());
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    doc.getDocumentElement().normalize();
                    //thực hiện lấy các trường trong biến
                    // ta dựa vào element để lấy dữ liệu bên trong
                    Element BaseResponse = doc.getDocumentElement();//lấy tag Root ra
                    NodeList nodeDescription = BaseResponse.getElementsByTagName("Description");// lấy tag name "Description" bên trong của tag root ra
                    String Description = nodeDescription.item(0).getTextContent();//lấy nội dung của tag name "Description"
                    NodeList nodeStatus = BaseResponse.getElementsByTagName("Status");// lấy tag name "Status" bên trong của tag root ra
                    String Status = nodeStatus.item(0).getTextContent();//lấy nội dung của tag name "Status"
                    //nếu status = 1 : là thành công, ngược lại thất bại
                    if (Status.equals("1")) {
                        return "Đăng ký tài khoản thành công.";
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "Đăng ký thất bại. Vui lòng thử lại.";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage(result);
            builder1.setCancelable(true);
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }
}
