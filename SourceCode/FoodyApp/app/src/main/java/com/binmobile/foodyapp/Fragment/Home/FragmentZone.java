package com.binmobile.foodyapp.Fragment.Home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binmobile.foodyapp.R;

/**
 * Created by nthanhphong on 9/1/2016.
 */

public class FragmentZone extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_zone, null);
        return rootView;
    }
}
