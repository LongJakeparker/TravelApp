package com.greenacademy.travelapp.Activity.Fragment;

/**
 * Created by nguyenhuuanh on 13/01/2017.
 */

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Permission;
import java.util.ArrayList;

import static com.facebook.HttpMethod.GET;

public class BanDoFragment extends Fragment implements OnMapReadyCallback, chuyenDuLieu {
    private GoogleMap mMap;
    private ArrayList<DiaDiemQuanAn> quanAnGanToiList = new ArrayList<>();
    private Location location;
    private LocationManager lm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        lm = (LocationManager) this.getContext().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
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

            return;
        } else {
            Log.e("permission", "xin quyen thanh cong!!!");
            mMap = googleMap;
            Log.i("Ban do fragment Log", "Khoi tao thanh cong map");
            mMap.setMyLocationEnabled(true);
            Log.i("Ban do fragment Log", "Tao nut truy cap vi tri hien tai");
            Log.i("Ban do fragment Log", "nhan dia diem cac quan an");
            layDiaDiemGanToi();
            addMarker();
        }

    }

    private void addMarker() {
        for (DiaDiemQuanAn ddqa : quanAnGanToiList
                ) {
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(ddqa.getLat(), ddqa.getLng()))
                    .title(ddqa.getTitle())
                    .icon(BitmapDescriptorFactory.fromResource(ddqa.getBitMapId())));
            Log.i("Ban do fragment Log", "Add marker quan an vao ban do");
            Log.i("Ban do fragment Log", marker.toString());
        }
    }

    private void layDiaDiemGanToi() {

        //ham Api xu ly lay du lieu quan gan toi
        layListQuanAn(LaydiaDiemhientai());

        if (quanAnGanToiList.size() != 0) {
            Log.i("Ban do fragment Log", "Nhan dia diem cac quan an thanh cong");

        } else {
            Log.i("Ban do fragment Log", "So luong quan an nhan duoc" + quanAnGanToiList.size());
        }
    }

    private void layListQuanAn(LatLng latLng) {
        ArrayList<DiaDiemQuanAn> result = new ArrayList<>();
        String diaChi = "http://103.237.147.137:9045/DiaDiem/DiaDiemByToaDo?lat=" + latLng.latitude + "&lng=" + latLng.longitude;
        layListQuanAn mlaylist = new layListQuanAn(this);
        mlaylist.execute(diaChi);
    }

    private void readStream(InputStream in) {

        Log.i("Stream In", "readStream: " + in);
    }

    private LatLng LaydiaDiemhientai() {
        LatLng result;
        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        } else {
            location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Log.i("location", "LaydiaDiemhientai: " + location);
        }

        if (location != null) {
            result = new LatLng(location.getLatitude(), location.getLongitude());
        } else {
            result = new LatLng(0, 0);
        }
        return result;
    }

    @Override
    public void chuyendulieu(String data) {
        try {
            JSONObject jsonObj = new JSONObject(data);
            JSONArray list = jsonObj.getJSONArray("DiaDiems");
            for (int i = 0; i < list.length(); i++) {
                JSONObject temp = list.getJSONObject(i);
                DiaDiemQuanAn tempQuanAn = new DiaDiemQuanAn();
                tempQuanAn.setId(String.valueOf(temp.getInt("Id")));
                tempQuanAn.setTenDiaDiem(temp.getString("TenDiaDiem"));
                tempQuanAn.setMoTa(temp.getString("Mota"));
                tempQuanAn.setDanhGia((float) temp.getDouble("DanhGia"));
                tempQuanAn.setSoLuotXem(temp.getInt("SoLuotXem"));
                tempQuanAn.setYeuThich(temp.getInt("YeuThich"));
                tempQuanAn.setCheckIn(temp.getInt("CheckIn"));
                tempQuanAn.setIdKhuVuc(temp.getInt("IdKhuVuc"));
                tempQuanAn.setLinkAnh(temp.getString("LinkAnh"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //AsyncTask lay du lieu dia diem quan an
    private class layListQuanAn extends AsyncTask<String,Integer,String >{
        URL url;
        private chuyenDuLieu thisChuyenDuLieu;
        public layListQuanAn(chuyenDuLieu cdulieu){
            thisChuyenDuLieu = cdulieu;
        }
        @Override
        protected String doInBackground(String... urls) {
            try {
                url = new URL(urls[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection urlConnection = null;
            InputStream in =null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Log.i("hoat dong","lay dia chi quan an");
                in = new BufferedInputStream(urlConnection.getInputStream());
                readStream(in);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();

            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append('\n');
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String result = sb.toString();
            return result;
        }

        @Override
        protected void onPostExecute(String jsonfile) {
            super.onPostExecute(jsonfile);
            thisChuyenDuLieu.chuyendulieu(jsonfile);
            Log.i("input", "onPostExecute: "+jsonfile);
        }
    }


}


interface chuyenDuLieu{
    public void chuyendulieu(String data);
}


