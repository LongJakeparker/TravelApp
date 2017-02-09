package com.greenacademy.travelapp.Activity.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.greenacademy.travelapp.R;

/**
 * Created by BINH on 1/17/2017.
 */

public class DangKyActivity extends AppCompatActivity {
    Button btnDangky;
    EditText edTen, edEmail, edPass, edPassNhapLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);

        btnDangky = (Button) findViewById(R.id.btnDangKy_DangKy);
        edTen = (EditText) findViewById(R.id.edTenNguoiDung_DangKy);
        edEmail = (EditText) findViewById(R.id.edEmail_DangKy);
        edPass = (EditText) findViewById(R.id.edPass_DangKy);
        edPassNhapLai = (EditText) findViewById(R.id.edPassNhapLai_DangKy);

        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kiểm tra nhập
                String kiemtranhap=KiemTraNhap();

                if(!kiemtranhap.equals("Dung"))
                {
                    Toast.makeText(getApplicationContext(), kiemtranhap, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //xử lý ở đây
                }
            }
        });
    }
    private String KiemTraNhap()
    {
        if(edTen.getText().toString().trim().equals(""))
            return "Chưa nhập tên người dùng";
        if(edEmail.getText().toString().trim().equals(""))
            return "Chưa nhập email";
        if(edPass.getText().toString().trim().equals(""))
            return "Chưa nhập mật khẩu";
        if(edPass.getText().toString().length()<6)
            return "Mật khẩu phải dài hơn 6 ký tự";
        if(edPass.getText().toString().length()>12)
            return "Mật khẩu tối đa 12 ký tự";
        if(!(edPassNhapLai.getText().toString().equals(edPass.getText().toString())))
        {
            return  "Không trùng mật khẩu";
        }
        return "Dung";
    }
}
