package com.binmobile.foodyapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.binmobile.foodyapp.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by nthanhphong on 9/6/2016.
 */

public class InfoWindowStore implements GoogleMap.InfoWindowAdapter {

    private View myContentsView;
    private Context context;
    private LayoutInflater mLayoutInflater;

    public InfoWindowStore(Context context) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
        myContentsView = mLayoutInflater.inflate(R.layout.info_window_store, null);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return myContentsView;
    }
}
