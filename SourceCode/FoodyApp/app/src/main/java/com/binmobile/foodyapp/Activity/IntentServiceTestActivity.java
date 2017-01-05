package com.binmobile.foodyapp.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.binmobile.foodyapp.R;
import com.binmobile.foodyapp.Services.DemSoIntentService;

import org.w3c.dom.Text;

import static com.binmobile.foodyapp.Services.DemSoIntentService.ACTION_SO_CHAN;
import static com.binmobile.foodyapp.Services.DemSoIntentService.ACTION_SO_LE;

/**
 * Created by User on 12/29/2016.
 */

public class IntentServiceTestActivity extends AppCompatActivity {
    TextView txtSoLe,txtSoChan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_intent_service);
        Button btnLe=(Button)findViewById(R.id.btnLe);
        Button btnChan=(Button)findViewById(R.id.btnChan);
        txtSoLe=(TextView)findViewById(R.id.textLe);
        txtSoChan=(TextView)findViewById(R.id.textChan);
        btnLe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DemSoIntentService.startActionSoLe(getApplicationContext(),"","");
            }
        });
        btnChan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DemSoIntentService.startActionSoChan(getApplicationContext(),"","");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //đăng ký với hệ thống nhận broadcastReceiver đã tạo
        registerReceiver(broadcastReceiver,new IntentFilter(ACTION_SO_CHAN));
        registerReceiver(broadcastReceiver,new IntentFilter(ACTION_SO_LE));
    }

    @Override
    protected void onPause() {
        super.onPause();
        //hủy đăng ký nhận broadcastReceiver
        unregisterReceiver(broadcastReceiver);
    }

    // tạo 1 đối tượng broadcastReceiver để xử dữ liệu khi nhận
    public BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(DemSoIntentService.ACTION_SO_CHAN)){
                int soChan=intent.getIntExtra("data",0);
                txtSoChan.setText(String.valueOf(soChan));
            }else if(intent.getAction().equals(DemSoIntentService.ACTION_SO_LE)){
                int soChan=intent.getIntExtra("data",0);
                txtSoLe.setText(String.valueOf(soChan));
            }
        }
    };
}
