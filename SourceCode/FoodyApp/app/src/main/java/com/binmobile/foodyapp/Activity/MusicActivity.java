package com.binmobile.foodyapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.binmobile.foodyapp.R;
import com.binmobile.foodyapp.Services.PlayMusicService;

/**
 * Created by User on 12/27/2016.
 */

public class MusicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        Button play=(Button)findViewById(R.id.play);
        Button stop=(Button)findViewById(R.id.stop);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bật service
                BatService();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tắt service
                TatService();
            }
        });
    }

    // khởi tạo service và chạy nó
    public void BatService(){
        //tạo 1 intent cho service, có thể truyền data như activity
        Intent service=new Intent(MusicActivity.this, PlayMusicService.class);
        //chạy service
        startService(service);
    }

    public void TatService(){
        //tạo 1 intent cho service, có thể truyền data như activity
        Intent service=new Intent(MusicActivity.this, PlayMusicService.class);
        //tắt service
        stopService(service);
    }
}
