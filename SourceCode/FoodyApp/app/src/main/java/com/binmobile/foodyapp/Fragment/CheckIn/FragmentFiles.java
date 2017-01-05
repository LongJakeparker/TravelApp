package com.binmobile.foodyapp.Fragment.CheckIn;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.binmobile.foodyapp.Activity.MainActivity;
import com.binmobile.foodyapp.Adapter.CheckIn.DanhSachDaChonAdapter;
import com.binmobile.foodyapp.Adapter.CheckIn.DanhSachFileAdapter;
import com.binmobile.foodyapp.CustomView.CheckImageCallback;
import com.binmobile.foodyapp.Network.CallBackData;
import com.binmobile.foodyapp.R;
import com.binmobile.foodyapp.Utils.ViewUtils;

/**
 * Created by nthanhphong on 9/24/2016.
 */

public class FragmentFiles extends Fragment implements CheckImageCallback {
    private View root;
    private RecyclerView danhSachFile, danhSachDaChon;
    private DanhSachDaChonAdapter danhSachDaChonAdapter;
    private MainActivity mainActivity;
    private Button back;

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //xin quyền trên android 5
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            }
        }
        root = inflater.inflate(R.layout.fragment_files, null);
        danhSachFile = (RecyclerView) root.findViewById(R.id.danhsach_anh);
        danhSachDaChon = (RecyclerView) root.findViewById(R.id.danhsach_dachon);
        danhSachFile.setLayoutManager(new GridLayoutManager(getContext(), 3));
        danhSachDaChon.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        danhSachFile.setAdapter(new DanhSachFileAdapter(getContext(), ViewUtils.GetImageSdCard(), this));
        danhSachDaChonAdapter = new DanhSachDaChonAdapter(getContext());
        danhSachDaChon.setAdapter(danhSachDaChonAdapter);

        back=(Button)root.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.getSupportFragmentManager().popBackStack();
            }
        });
        return root;
    }

    @Override
    public void CheckCallback(Bitmap bitmap, boolean isCheck) {
        if (bitmap != null) {
            danhSachDaChonAdapter.AddData(bitmap, isCheck);
        }
    }
}
