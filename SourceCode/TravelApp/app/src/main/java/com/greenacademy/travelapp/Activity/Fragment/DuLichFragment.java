package com.greenacademy.travelapp.Activity.Fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenacademy.travelapp.Activity.Utils.FragmentUtils;
import com.greenacademy.travelapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DuLichFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    TabLayout tabLayoutDuLich;
    View view;
    ArrayList<Fragment> arrFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_du_lich, container, false);
        initView();
        return view;
    }

    private void initView(){
        arrFragment = new ArrayList<>();
        arrFragment.add(0, new ChiTietDiaDiemFragment());
        arrFragment.add(1, new QuanAnFragment());
        arrFragment.add(2, new ChiTietDiaDiemFragment());

        tabLayoutDuLich = (TabLayout) view.findViewById(R.id.tablayout_MainFrame);
        tabLayoutDuLich.addTab(tabLayoutDuLich.newTab().setIcon(R.drawable.ic_diadiem_manhinhchinh).setText("Địa điểm"));
        tabLayoutDuLich.addTab(tabLayoutDuLich.newTab().setIcon(R.drawable.ic_quanan_diadiem).setText("Quán ăn"));
        tabLayoutDuLich.addTab(tabLayoutDuLich.newTab().setIcon(R.drawable.ic_diadiem_manhinhchinh).setText("Khách sạn"));
        tabLayoutDuLich.setOnTabSelectedListener(this);

        FragmentUtils.replaceFragmentWithouBackStack(getActivity(), arrFragment.get(0), R.id.container_dulich_fragment, "dulich");
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()){
            case 0:
                FragmentUtils.replaceFragmentWithouBackStack(getActivity(), arrFragment.get(0), R.id.container_dulich_fragment, "dulich");
                break;
            case 1:
                FragmentUtils.replaceFragmentWithouBackStack(getActivity(), arrFragment.get(1), R.id.container_dulich_fragment, "quanan");
                break;
            case 2:
                FragmentUtils.replaceFragmentWithouBackStack(getActivity(), arrFragment.get(2), R.id.container_dulich_fragment, "khachsan");
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
