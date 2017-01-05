package com.binmobile.foodyapp.Activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.binmobile.foodyapp.Fragment.CheckIn.FragmentCheckIn;
import com.binmobile.foodyapp.R;
import com.google.android.gms.vision.text.Text;

/**
 * Created by User on 12/24/2016.
 */

public class NotificationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_activity);
        final EditText tieuDe=(EditText) findViewById(R.id.txt_tieude);
        final EditText noiDung=(EditText)findViewById(R.id.txt_noidung);
        Button ok=(Button)findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //khởi tạo buidler
                NotificationCompat.Builder builder=new NotificationCompat.Builder(NotificationActivity.this);
                //tự động hủy notification khi người dùng nhấn
                builder.setAutoCancel(true);
                //set icon cho notification
                builder.setSmallIcon(R.drawable.icon_account);
                //set data cho tiêu đề và nội dung
                builder.setContentTitle(tieuDe.getText().toString());
                builder.setContentText(noiDung.getText().toString());
                //set intent
                Intent intent=new Intent(NotificationActivity.this,MainActivity.class);
                intent.putExtra("notification","từ notification");
                //PendingIntent.getActivity sẽ start mới 1 activity và trả về đối tượng PendingIntent
                PendingIntent pendingIntent=PendingIntent.getActivity(NotificationActivity.this,100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                //lấy dịch vụ notification của hệ thống
                NotificationManager notificationManager=(NotificationManager)NotificationActivity.this.getSystemService(Context.NOTIFICATION_SERVICE);
                //xây dựng đối tượng totification và gửi data thông báo cho nó
                Notification notification=builder.build();
                //xuất thông báo notification ra màn hình
                notificationManager.notify(9999,notification);
            }
        });
    }
}
