package com.greenacademy.travelapp.Activity.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.greenacademy.travelapp.R;

/**
 * Created by BINH on 2/9/2017.
 */

public class QuenMatKhauActivity extends AppCompatActivity {
    EditText edEmail;
    Button btnLayMK;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quyenmatkhau);
        edEmail=(EditText)findViewById(R.id.edEmail_Quenmatkhau);
        btnLayMK=(Button)findViewById(R.id.btnLayMK);
        btnLayMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
