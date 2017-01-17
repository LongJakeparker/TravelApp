package com.greenacademy.travelapp.Activity.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.greenacademy.travelapp.R;

/**
 * Created by Administrator on 12/01/2017.
 */

public class TutorialFragment1 extends Fragment {
    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_tutorial, null);
        RelativeLayout layout = (RelativeLayout) v.findViewById(R.id.layoutTutorial);
        layout.setBackgroundResource(R.drawable.tutorial_1);
        return v;
    }
}
