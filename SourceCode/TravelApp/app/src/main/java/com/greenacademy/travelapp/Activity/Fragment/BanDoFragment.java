package com.greenacademy.travelapp.Activity.Fragment;

/**
 * Created by nguyenhuuanh on 13/01/2017.
 */

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.greenacademy.travelapp.Activity.Model.DiaDiemQuanAn;
import com.greenacademy.travelapp.R;

import java.util.ArrayList;

public class BanDoFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    private ArrayList<DiaDiemQuanAn> quanAnGanToiList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //tao map Fragment
        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        //nhan su kien map tra ve
        mapFragment.getMapAsync(this);
        //thay fragment vao view chua
        this.getChildFragmentManager().beginTransaction().replace(R.id.ban_do, mapFragment).commit();
        return inflater.inflate(R.layout.ban_do_fragment, container, false);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap = googleMap;
        Log.i("Ban do fragment Log","Khoi tao thanh cong map");
        mMap.setMyLocationEnabled(true);
        Log.i("Ban do fragment Log","Tao nut truy cap vi tri hien tai");
        Log.i("Ban do fragment Log","nhan dia diem cac quan an");
        quanAnGanToiList = layDiaDiemGanToi();

        addMarker();
    }

    private void addMarker() {
        for (DiaDiemQuanAn ddqa: quanAnGanToiList
             ) {
             Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(ddqa.getLat(),ddqa.getLng()))
                    .title(ddqa.getTitle())
                    .icon(BitmapDescriptorFactory.fromResource(ddqa.getBitMapId())));
            Log.i("Ban do fragment Log","Add marker quan an vao ban do");
            Log.i("Ban do fragment Log",marker.toString());
        }
    }

    private ArrayList<DiaDiemQuanAn> layDiaDiemGanToi() {
        ArrayList<DiaDiemQuanAn> temp = new ArrayList<>();

        //ham Api xu ly lay du lieu quan gan toi
        if (temp.size()!=0) {
            Log.i("Ban do fragment Log", "Nhan dia diem cac quan an thanh cong");
        }else{
            Log.i("Ban do fragment Log", "So luong quan an nhan duoc" + temp.size());
        }
        return temp;
    }
}
