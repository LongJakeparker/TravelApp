package com.greenacademy.travelapp.Activity.Fragment;

/**
 * Created by nguyenhuuanh on 13/01/2017.
 */

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
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
import java.util.ArrayList;
import java.util.HashMap;

import static com.facebook.HttpMethod.GET;

public class BanDoFragment extends Fragment implements OnMapReadyCallback, chuyenDuLieu, GoogleMap.OnMarkerClickListener, View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private GoogleMap mMap;
    private ArrayList<DiaDiemQuanAn> quanAnGanToiList = new ArrayList<>();
    private Location location;
    private LocationManager lm;
    private HashMap<Marker, Integer> markerIntegerHashMap = new HashMap<>();
    private Dialog dialog;
    private Button btnTatCa;
    private Button btnDiaDiem;
    private Button btnQuanAn;
    private Button btnKhachSan;
    private GoogleApiClient mGoogleApiClient;
    private int buttonDuocChon = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        mGoogleApiClient.connect();


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
        btnTatCa = (Button) getView().findViewById(R.id.btn_tat_ca_map);
        btnDiaDiem = (Button) getView().findViewById(R.id.btn_dia_diem_map);
        btnQuanAn = (Button) getView().findViewById(R.id.btn_quan_an_map);
        btnKhachSan = (Button) getView().findViewById(R.id.btn_khach_san_map);
        btnTatCa.setTextColor(Color.WHITE);
        btnTatCa.setBackgroundResource(R.drawable.button_map_fragment_chon);

        btnTatCa.setOnClickListener(this);
        btnKhachSan.setOnClickListener(this);
        btnQuanAn.setOnClickListener(this);
        btnDiaDiem.setOnClickListener(this);
        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        } else {
            Log.e("permission", "xin quyen thanh cong!!!");
            mMap = googleMap;
            Log.i("Ban do fragment Log", "Khoi tao thanh cong map");
            mMap.setMyLocationEnabled(true);
            View locationButton = ((View) getView().findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
            RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
            // position on right bottom
            rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
            rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            rlp.setMargins(0, 0, 30, 30);
            Log.i("Ban do fragment Log", "Tao nut truy cap vi tri hien tai");
            Log.i("Ban do fragment Log", "nhan dia diem cac quan an");

        }
    }

    private void addMarker() {
        int i = 0;
        markerIntegerHashMap = new HashMap<>();
        mMap.clear();
        for (DiaDiemQuanAn ddqa : quanAnGanToiList
                ) {
            Marker marker = null;
            if (checkDieuKien(ddqa.getIdKhuVuc())) {
                marker = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(ddqa.getLat(), ddqa.getLng()))
                        .title(ddqa.getTenDiaDiem())
                        .icon(BitmapDescriptorFactory.fromResource(ddqa.getBitMapId()))
                        .title(ddqa.getTenDiaDiem()));
            }
            try {
                markerIntegerHashMap.put(marker, i);
            } catch (Exception e) {
                Log.e("hashmap", e.toString());
            }
            i++;
            Log.i("Ban do fragment Log", "Add marker quan an vao ban do");
            if (marker != null) {
                Log.i("Ban do fragment Log", marker.toString());
            }
        }
        mMap.setOnMarkerClickListener(this);
    }

    private boolean checkDieuKien(String idKhuVuc) {
        boolean thoaMan = false;
        thoaMan = (buttonDuocChon == 0) || (buttonDuocChon == 3 && idKhuVuc.contains("DiaDiem")) || (buttonDuocChon == 2 && idKhuVuc.contains("QuanAn")) || (buttonDuocChon == 1 && idKhuVuc.contains("KhachSan"));
        return thoaMan;
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
        String diaChi = "http://103.237.147.137:9045/DiaDiem/MapMarker?lat=" + latLng.latitude + "&lng=" + latLng.longitude;
        layListQuanAn mlaylist = new layListQuanAn(this);
        mlaylist.execute(diaChi);
    }

    private void readStream(InputStream in) {

        Log.i("Stream In", "readStream: " + in);
    }

    private LatLng LaydiaDiemhientai() {
        LatLng result;


        while (location == null) {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //check quyen
            }
            location = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);

        }
        result = new LatLng(location.getLatitude(), location.getLongitude());
        return result;
    }

    @Override
    public void chuyendulieu(String data) {
        try {
            JSONObject jsonObj = new JSONObject(data);
            JSONArray list = jsonObj.getJSONArray("MapMarkerTranfers");
            for (int i = 0; i < list.length(); i++) {
                JSONObject temp = list.getJSONObject(i);
                DiaDiemQuanAn tempQuanAn = new DiaDiemQuanAn();
                tempQuanAn.setId(String.valueOf(temp.getInt("Id")));
                tempQuanAn.setTenDiaDiem(temp.getString("Ten"));
                tempQuanAn.setMoTa(temp.getString("MoTa"));
                tempQuanAn.setDanhGia((float) temp.getDouble("DanhGia"));
                tempQuanAn.setSoLuotXem(temp.getInt("SoLuotXem"));
                tempQuanAn.setYeuThich(temp.getInt("YeuThich"));
                tempQuanAn.setCheckIn(temp.getInt("CheckIn"));
                tempQuanAn.setIdKhuVuc(temp.getString("LoaiMarker"));
                tempQuanAn.setLinkAnh(temp.getString("LinkAnh"));
                tempQuanAn.setLat((float) temp.getDouble("Lat"));
                tempQuanAn.setLng((float) temp.getDouble("Lng"));
                if (tempQuanAn.getIdKhuVuc().contains("QuanAn")) {
                    tempQuanAn.setBitMapId(R.drawable.ic_local_dining_black_24px);
                }
                quanAnGanToiList.add(tempQuanAn);
            }
            addMarker();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void chuyenAnh(Bitmap bitmap) {
        ImageView image = (ImageView) dialog.findViewById(R.id.map_dialog_hinh_anh);
        if (bitmap != null) {
            image.setImageBitmap(bitmap);
        }
    }

    @Override
    public void chuyenLocation(Location location) {
        layDiaDiemGanToi();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        int pos = markerIntegerHashMap.get(marker);
        DiaDiemQuanAn diaDiemQuanAnTemp = quanAnGanToiList.get(pos);
        //ham su ly khi marker duoc chon
        dialog = new Dialog(this.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.map_dialog_layout);



        // set the custom dialog components - text, image and button
        TextView tendiadiem = (TextView) dialog.findViewById(R.id.tv_ten_dia_diem_map);
        tendiadiem.setText(diaDiemQuanAnTemp.getTenDiaDiem());
        TextView mota = (TextView) dialog.findViewById(R.id.tv_mo_ta_2_map);
        mota.setText(diaDiemQuanAnTemp.getMoTa());
        TextView like = (TextView) dialog.findViewById(R.id.tv_like_map);
        like.setText("Like: " + String.valueOf(diaDiemQuanAnTemp.getYeuThich()));
        TextView danhgia = (TextView) dialog.findViewById(R.id.tv_danh_gia_map);
        danhgia.setText("Đánh Giá: " + String.valueOf(diaDiemQuanAnTemp.getDanhGia()));
        TextView luotview = (TextView) dialog.findViewById(R.id.tv_view_map);
        luotview.setText("View: " + String.valueOf(diaDiemQuanAnTemp.getSoLuotXem()));

        downloadAnh downloadAnh = new downloadAnh(this);
        downloadAnh.execute(diaDiemQuanAnTemp.getLinkAnh());

        Button dialogButton = (Button) dialog.findViewById(R.id.chi_tiet_btn_map);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to do
                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_tat_ca_map:
                btnTatCa.setTextColor(Color.WHITE);
                btnTatCa.setBackgroundResource(R.drawable.button_map_fragment_chon);
                buttonDuocChon = 0;
                btnKhachSan.setTextColor(Color.BLACK);
                btnKhachSan.setBackgroundResource(R.drawable.button_map_fragment);
                btnQuanAn.setTextColor(Color.BLACK);
                btnQuanAn.setBackgroundResource(R.drawable.button_map_fragment);
                btnDiaDiem.setTextColor(Color.BLACK);
                btnDiaDiem.setBackgroundResource(R.drawable.button_map_fragment);
                addMarker();
                break;
            case R.id.btn_khach_san_map:
                btnKhachSan.setTextColor(Color.WHITE);
                btnKhachSan.setBackgroundResource(R.drawable.button_map_fragment_chon);
                buttonDuocChon = 1;
                btnTatCa.setTextColor(Color.BLACK);
                btnTatCa.setBackgroundResource(R.drawable.button_map_fragment);
                btnQuanAn.setTextColor(Color.BLACK);
                btnQuanAn.setBackgroundResource(R.drawable.button_map_fragment);
                btnDiaDiem.setTextColor(Color.BLACK);
                btnDiaDiem.setBackgroundResource(R.drawable.button_map_fragment);
                addMarker();
                break;
            case R.id.btn_quan_an_map:
                btnQuanAn.setTextColor(Color.WHITE);
                btnQuanAn.setBackgroundResource(R.drawable.button_map_fragment_chon);
                buttonDuocChon = 2;
                btnTatCa.setTextColor(Color.BLACK);
                btnTatCa.setBackgroundResource(R.drawable.button_map_fragment);
                btnKhachSan.setTextColor(Color.BLACK);
                btnKhachSan.setBackgroundResource(R.drawable.button_map_fragment);
                btnDiaDiem.setTextColor(Color.BLACK);
                btnDiaDiem.setBackgroundResource(R.drawable.button_map_fragment);
                addMarker();
                break;
            case R.id.btn_dia_diem_map:
                btnDiaDiem.setTextColor(Color.WHITE);
                btnDiaDiem.setBackgroundResource(R.drawable.button_map_fragment_chon);
                buttonDuocChon = 3;
                btnTatCa.setTextColor(Color.BLACK);
                btnTatCa.setBackgroundResource(R.drawable.button_map_fragment);
                btnKhachSan.setTextColor(Color.BLACK);
                btnKhachSan.setBackgroundResource(R.drawable.button_map_fragment);
                btnQuanAn.setTextColor(Color.BLACK);
                btnQuanAn.setBackgroundResource(R.drawable.button_map_fragment);
                addMarker();
                break;
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        layDiaChiHienTai layDiaChi = new layDiaChiHienTai(this);
        layDiaChi.execute();

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    private class downloadAnh extends AsyncTask<String, Integer, Bitmap> {
        private chuyenDuLieu chuyenDuLieu;

        public downloadAnh(chuyenDuLieu chuyenDuLieu) {
            this.chuyenDuLieu = chuyenDuLieu;
        }

        ;

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (IOException e) {
                // Log exception
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            chuyenDuLieu.chuyenAnh(bitmap);
        }
    }

    //AsyncTask lay du lieu dia diem quan an
    private class layListQuanAn extends AsyncTask<String, Integer, String> {
        URL url;
        private chuyenDuLieu thisChuyenDuLieu;

        public layListQuanAn(chuyenDuLieu cdulieu) {
            thisChuyenDuLieu = cdulieu;
        }

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                inputStream = new BufferedInputStream(urlConnection.getInputStream());
                result = readStreamData(inputStream);
                readStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
            return result;
        }

        private String readStreamData(InputStream inputStream) {
            String result;
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;

            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append('\n');
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            result = sb.toString();
            return result;
        }

        @Override
        protected void onPostExecute(String jsonfile) {
            super.onPostExecute(jsonfile);
            thisChuyenDuLieu.chuyendulieu(jsonfile);
            Log.i("input", "onPostExecute: " + jsonfile);
        }
    }

    private class layDiaChiHienTai extends AsyncTask<Void, Integer, Location> {
        chuyenDuLieu chuyenDuLieu;

        public layDiaChiHienTai(com.greenacademy.travelapp.Activity.Fragment.chuyenDuLieu chuyenDuLieu) {
            this.chuyenDuLieu = chuyenDuLieu;
        }

        @Override
        protected Location doInBackground(Void... voids) {
            Location temp = null;
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            }
            while (temp==null) {
                temp = LocationServices.FusedLocationApi.getLastLocation(
                        mGoogleApiClient);
            }
            return temp;
        }

        @Override
        protected void onPostExecute(Location location) {
            super.onPostExecute(location);
            super.onPostExecute(location);
            chuyenLocation(location);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mGoogleApiClient.disconnect();
    }

}

interface chuyenDuLieu{
    public void chuyendulieu(String data);
    public void chuyenAnh(Bitmap bitmap);
    public void chuyenLocation(Location location);
}
