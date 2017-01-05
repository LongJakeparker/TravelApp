package com.binmobile.foodyapp.Fragment.Home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binmobile.foodyapp.Adapter.HomeViewPagerAdapter;
import com.binmobile.foodyapp.R;

/**
 * Created by nthanhphong on 9/1/2016.
 */

public class FragmentHome extends Fragment {
    private View rootView;
    private ViewPager mViewPager;
    private HomeViewPagerAdapter mPagerAdapter;
    private TabLayout tabHeader;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, null);
        InitView();
        SetData();
        return rootView;
    }

    public void SetData(){
        mPagerAdapter = new HomeViewPagerAdapter(this.getFragmentManager(),getContext());
        mViewPager.setAdapter(mPagerAdapter);
        tabHeader.setupWithViewPager(mViewPager);
    }

    private void InitView() {
        mViewPager = (ViewPager) rootView.findViewById(R.id.view_pager);
        tabHeader = (TabLayout) rootView.findViewById(R.id.home_tab_layout);
    }
}
