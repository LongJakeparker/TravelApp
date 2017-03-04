package com.greenacademy.travelapp.Activity.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.greenacademy.travelapp.Activity.Adapter.AdapterQuanAn.FullListQuanAnAdapter;
import com.greenacademy.travelapp.Activity.Connection.Interface.ItemRecyclerClickListener;
import com.greenacademy.travelapp.Activity.Constant.Constant;
import com.greenacademy.travelapp.Activity.Model.QuanAnChiTiet;
import com.greenacademy.travelapp.R;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by DAVIDSON on 2/19/2017.
 */

public class FullListQuananFragment extends Fragment implements ItemRecyclerClickListener {
    RecyclerView recyclerFullListQuanan;
    ArrayList<QuanAnChiTiet> listQuanAn;
    FullListQuanAnAdapter fullListAdapter;
    Toolbar toolbar;

    QuanAnFragment quanAnFragment;
    ChiTietQuanAnFragment chiTietQuanAnFragment;

    SearchView searchView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.full_list_quanan_fragment, container, false);
//        listSearch = new ArrayList<QuanAnChiTiet>();
        listQuanAn = getArguments().getParcelableArrayList(Constant.FULLLIST_QUANAN);
//        listSearch.addAll(listQuanAn);
        recyclerFullListQuanan = (RecyclerView) view.findViewById(R.id.recyclerViewfulllistQuanan);
        fullListAdapter = new FullListQuanAnAdapter(listQuanAn, getContext());
        toolbar = (Toolbar) view.findViewById(R.id.toolbarFullListQuanan);
        quanAnFragment = new QuanAnFragment();
        chiTietQuanAnFragment = new ChiTietQuanAnFragment();
        searchView = (SearchView) view.findViewById(R.id.searchViewFullListQuanan);
        EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.colorWhite));

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(fragmentManager.findFragmentByTag("FulllistFrag"));
                fragmentTransaction.commit();
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerFullListQuanan.setLayoutManager(layoutManager);
        recyclerFullListQuanan.setAdapter(fullListAdapter);
        fullListAdapter.onItemRecyclerClickListener(this);

        //search
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fullListAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return view;
    }

    public void setFragment(Fragment fragment, boolean option, String fragName){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (option){
            fragmentTransaction.add(R.id.framelayout_container, fragment, fragName);
        }else {
            fragmentTransaction.replace(R.id.framelayout_container, fragment, fragName);
        }

        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View view, int position, int adapterID) {
        if (adapterID == Constant.FULLLIST_ADAPTER){
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constant.CHITIET_QUANAN, listQuanAn.get(position));
            chiTietQuanAnFragment.setArguments(bundle);
            setFragment(chiTietQuanAnFragment, true, "quananChitietFragment");
        }
    }
}
