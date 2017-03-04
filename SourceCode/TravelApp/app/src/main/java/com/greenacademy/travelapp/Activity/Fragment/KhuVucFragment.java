package com.greenacademy.travelapp.Activity.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.greenacademy.travelapp.Activity.Activity.ManHinhChinhActivity;
import com.greenacademy.travelapp.Activity.Adapter.KhuVucAdapter;
import com.greenacademy.travelapp.Activity.AsyncTask.GetKhuVucDiaDiemThread;
import com.greenacademy.travelapp.Activity.Constant.Constant;
import com.greenacademy.travelapp.Activity.Model.KhuVucDuLich;
import com.greenacademy.travelapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class KhuVucFragment extends Fragment implements KhuVucAdapter.KhuVucVH.InterfaceKhuVuc {

    ArrayList<KhuVucDuLich> arrKhuVuc;
    View view;
    RecyclerView recyclerKhuVuc;
    ManHinhChinhActivity manHinhChinhActivity;
    KhuVucAdapter khuVucAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_khu_vuc, container, false);
        initView();
        return view;
    }

    private void initView(){
        ((ManHinhChinhActivity) getActivity()).linearControlDuLich.setVisibility(View.GONE);
        manHinhChinhActivity = (ManHinhChinhActivity) getActivity();
        manHinhChinhActivity.tvTitle.setText("Khu Vực");

        arrKhuVuc = new ArrayList<>();
        recyclerKhuVuc = (RecyclerView) view.findViewById(R.id.recyclerKhuVuc_DuLich);
        recyclerKhuVuc.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        khuVucAdapter = new KhuVucAdapter(arrKhuVuc, this);
        recyclerKhuVuc.setAdapter(khuVucAdapter);

        new GetKhuVucDiaDiemThread(this, "http://103.237.147.137:9045/KhuVuc/AllKhuVuc", Constant.TYPE_DATA_KHU_VUC).start();
    }

    @Override
    public void callbackKhuVucFragment(int id) {
        ManHinhChinhActivity manHinhChinhActivity = (ManHinhChinhActivity) getActivity();
        manHinhChinhActivity.tvTitle.setText("Địa Điểm");
        DiaDiemFragment diaDiemFragment = (DiaDiemFragment) getFragmentManager().findFragmentById(R.id.diadiem_fragment);
        if (diaDiemFragment == null){
            diaDiemFragment = new DiaDiemFragment();
            Toast.makeText(getActivity(), "new", Toast.LENGTH_LONG).show();
        }
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        diaDiemFragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.framelayout_container, diaDiemFragment)
                .addToBackStack("dulich")
                .commit();
    }


    public void updateData(KhuVucDuLich khuVucDuLich){
        arrKhuVuc.add(khuVucDuLich);
        khuVucAdapter.notifyDataSetChanged();
    }
}
