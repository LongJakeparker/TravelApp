package com.binmobile.foodyapp.Fragment.Home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binmobile.foodyapp.Adapter.InfoWindowStore;
import com.binmobile.foodyapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by nthanhphong on 9/5/2016.
 */

public class FragmentMap extends Fragment implements OnMapReadyCallback {
    private View rootView;
    private GoogleMap mMap;
    private String[] lats;
    private String[] lngs;
    private String[] mNames;
    private static final int ZOOM = 15;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_map, null);
        Data();
        InitView();
        return rootView;
    }

    public void Data() {
        lats = getResources().getStringArray(R.array.data_location_lat);
        lngs = getResources().getStringArray(R.array.data_location_lng);
        mNames = getResources().getStringArray(R.array.data_store_name);

    }

    public void DumpData() {
        LatLng store = new LatLng(-34, 151);
        for (int i = 0; i < lats.length; i++) {
            store = new LatLng(Double.parseDouble(lats[i]), Double.parseDouble(lngs[i]));
            mMap.addMarker(new MarkerOptions().position(store).title(mNames[i]).icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_marker)));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(store, ZOOM));
    }

    public void InitView() {
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map_view);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        DumpData();
        mMap.setInfoWindowAdapter(new InfoWindowStore(getContext()));
    }
}
