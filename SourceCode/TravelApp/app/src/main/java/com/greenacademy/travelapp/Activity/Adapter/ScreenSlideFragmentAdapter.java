package com.greenacademy.travelapp.Activity.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 12/01/2017.
 */

public class ScreenSlideFragmentAdapter extends FragmentPagerAdapter {
    List<Fragment> myListFragment;

    public ScreenSlideFragmentAdapter(FragmentManager fm, List<Fragment> myListFragment) {
        super(fm);
        this.myListFragment = myListFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return myListFragment.get(position);
    }

    @Override
    public int getCount() {
        return myListFragment.size();
    }
}
