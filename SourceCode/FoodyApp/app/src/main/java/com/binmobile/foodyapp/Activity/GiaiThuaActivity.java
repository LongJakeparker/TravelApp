package com.binmobile.foodyapp.Activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.binmobile.foodyapp.R;
import com.binmobile.foodyapp.Services.TinhGiaiThuaService;

import org.w3c.dom.Text;

/**
 * Created by User on 12/27/2016.
 */

public class GiaiThuaActivity extends AppCompatActivity {

    EditText gioiHan;
    TextView ketQua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaithua);
        gioiHan=(EditText) findViewById(R.id.gioihan);
        Button tinhGiaiThua=(Button)findViewById(R.id.tinh_giaithua);
        ketQua=(TextView) findViewById(R.id.ketqua);
        tinhGiaiThua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ketQua.setText("Ket qua la: "+tinhGiaiThuaService.TinhBinhPhuong(Integer.parseInt(gioiHan.getText().toString())));
                tinhGiaiThuaService.setSo(Integer.parseInt(gioiHan.getText().toString()));
                tinhGiaiThuaService.StartServiceBind();
            }
        });
    }

    // Khi Activity ngừng hoạt động.
    @Override
    protected void onStop() {
        super.onStop();
        if (binded) {
            // Hủy giàng buộc kết nối với dịch vụ.
           // this.unbindService(giaiThuaConnection);
            binded = false;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // bind service vào hệ thống
        //tạo đối tượng cho service
        Intent service=new Intent(this, TinhGiaiThuaService.class);
        bindService(service,giaiThuaConnection, BIND_AUTO_CREATE);
    }
    //biến check service đã bind hay chưa
    private boolean binded=false;
    //biến lưu trữ service sau khi được bind
    private TinhGiaiThuaService tinhGiaiThuaService;
    //tạo 1 kết nối lắng nghe service đã stop hay start hay chưa
    ServiceConnection giaiThuaConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //lấy binder và kết nối với service
            TinhGiaiThuaService.LocalGiaiThuaBinder binder=(TinhGiaiThuaService.LocalGiaiThuaBinder)service;
            tinhGiaiThuaService=binder.getService();
            tinhGiaiThuaService.setHandler(handler);
            binded=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            binded = false;
        }
    };

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String kq = msg.getData().getString("DATA");
            ketQua.setText(kq);
        }
    };
}
