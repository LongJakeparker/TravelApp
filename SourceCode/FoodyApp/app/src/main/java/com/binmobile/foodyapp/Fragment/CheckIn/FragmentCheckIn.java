package com.binmobile.foodyapp.Fragment.CheckIn;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.binmobile.foodyapp.Activity.MainActivity;
import com.binmobile.foodyapp.Adapter.CheckInAdapter;
import com.binmobile.foodyapp.R;

import java.util.LinkedList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by nthanhphong on 9/24/2016.
 */

public class FragmentCheckIn extends Fragment {
    View root;
    Button back, dang;
    ImageButton file, camera;
    List<Bitmap> dataList = new LinkedList<>();
    CheckInAdapter adapter;
    MainActivity mainActivity;

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_checkin, null);
        //check quyền trên > android 6
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getContext().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
            }
        }
        adapter = new CheckInAdapter();

        back = (Button) root.findViewById(R.id.back);
        dang = (Button) root.findViewById(R.id.dang);
        file = (ImageButton) root.findViewById(R.id.file);
        camera = (ImageButton) root.findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getContext().getPackageManager().hasSystemFeature(
                        PackageManager.FEATURE_CAMERA)) {
                    // Mở camera mặc định
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    // Tiến hành gọi Capture Image intent
                    startActivityForResult(intent, 100);
                } else {
                    Toast.makeText(getContext(), "Camera không được hỗ trợ", Toast.LENGTH_LONG).show();
                }
            }
        });
        file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentFiles fragmentFiles = new FragmentFiles();
                fragmentFiles.setMainActivity(mainActivity);
                mainActivity.setFragment(fragmentFiles);
            }
        });
        return root;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //check kết quả trả về thành công thì xử lý
        if (requestCode == RESULT_OK) {
            //Lấy ảnh từ intent Camera của mình về dưới dạng bitmap và hiển thị lên imageview của mình
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            if (bitmap != null) {
                adapter.AddBitmap(bitmap);
            }
        }
    }
}
