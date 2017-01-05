package com.binmobile.foodyapp.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.binmobile.foodyapp.Fragment.Home.FragmentEatFood;
import com.binmobile.foodyapp.Fragment.Home.FragmentMap;
import com.binmobile.foodyapp.Fragment.Home.FragmentStore;
import com.binmobile.foodyapp.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by nthanhphong on 9/6/2016.
 */

public class HomeViewPagerAdapter extends FragmentStatePagerAdapter {

    private static final int NUM_PAGES = 3;
    private Context context;
    private List<Fragment> listData = new LinkedList<Fragment>();

    public HomeViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        listData.add(new FragmentEatFood());
        listData.add(new FragmentStore());
        listData.add(new FragmentMap());
    }

    @Override
    public Fragment getItem(int position) {
        return listData.get(position);
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = context.getString(R.string.an_gi);
                break;
            case 1:
                title = context.getString(R.string.o_dau);
                break;
            case 2:
                title = context.getString(R.string.gan_toi);
                break;
        }

        return title;
    }
}
