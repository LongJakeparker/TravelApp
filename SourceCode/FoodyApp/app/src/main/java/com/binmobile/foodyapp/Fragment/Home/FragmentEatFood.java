package com.binmobile.foodyapp.Fragment.Home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binmobile.foodyapp.R;

/**
 * Created by nthanhphong on 9/6/2016.
 */

public class FragmentEatFood extends Fragment {
    private TabLayout allTabLayout;
    private FragmentNew fragmentNew;
    private FragmentCatalog fragmentCatalog;
    private FragmentZone fragmentZone;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_eat_food, null);
        InitView();
        ListenerChangeTab();
        SetupTabLayout();
        return rootView;
    }

    private void SetupTabLayout() {
        fragmentNew = new FragmentNew();
        fragmentCatalog = new FragmentCatalog();
        fragmentZone = new FragmentZone();
        allTabLayout.addTab(allTabLayout.newTab().setText(getString(R.string.home_new_title)), true);
        allTabLayout.addTab(allTabLayout.newTab().setText(getString(R.string.home_catalog_title)));
        allTabLayout.addTab(allTabLayout.newTab().setText(getString(R.string.home_zone_title)));
    }

    private void InitView() {
        allTabLayout = (TabLayout) rootView.findViewById(R.id.sliding_tabs);
    }


    private void ListenerChangeTab() {
        allTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentTabFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setCurrentTabFragment(int tabPosition) {
        switch (tabPosition) {
            case 0:
                replaceFragment(fragmentNew);
                break;
            case 1:
                replaceFragment(fragmentCatalog);
                break;
            case 2:
                replaceFragment(fragmentZone);
                break;
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_container, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }
}
