package com.greenacademy.travelapp.Activity.Fragment;


import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterViewFlipper;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.greenacademy.travelapp.Activity.Activity.ManHinhChinhActivity;
import com.greenacademy.travelapp.Activity.Adapter.DiaDiemAdapter;
import com.greenacademy.travelapp.Activity.Adapter.TopDiaDiemAdapter;
import com.greenacademy.travelapp.Activity.AsyncTask.GetKhuVucDiaDiemThread;
import com.greenacademy.travelapp.Activity.Constant.Constant;
import com.greenacademy.travelapp.Activity.Model.DiaDiemDuLich;
import com.greenacademy.travelapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiaDiemFragment extends Fragment implements DiaDiemAdapter.DiaDiemVH.InterfaceDiaDiemDuLich {

    View view;
    RecyclerView recyclerDiaDiem;
    AdapterViewFlipper flipperDiaDiem;
    ArrayList<DiaDiemDuLich> arrDiaDiem;
    DiaDiemAdapter diaDiemAdapter;
    ArrayList<DiaDiemDuLich> arrTopDiaDiem;
    int id;
    private TopDiaDiemAdapter topDiaDiemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_dia_diem, container, false);
        id = getArguments().getInt("id");
        new GetKhuVucDiaDiemThread(this, "http://103.237.147.137:9045/DiaDiem/DiaDiemById?idKhuVuc=".concat(String.valueOf(id)), Constant.TYPE_DATA_DIA_DIEM).start();
        initView();
        return view;
    }

    private void initView(){
        ((ManHinhChinhActivity) getActivity()).linearControlDuLich.setVisibility(View.VISIBLE);

        arrTopDiaDiem       = new ArrayList<>();
        flipperDiaDiem      = (AdapterViewFlipper) view.findViewById(R.id.flipperTopDiaDiem);
        topDiaDiemAdapter   = new TopDiaDiemAdapter(getActivity(), R.layout.item_recycler_diadiem, arrTopDiaDiem,
                getActivity().getWindowManager().getDefaultDisplay().getWidth());
        flipperDiaDiem.setAdapter(topDiaDiemAdapter);

        arrDiaDiem      = new ArrayList<>();
        recyclerDiaDiem = (RecyclerView) view.findViewById(R.id.recyclerDiaDiem_DuLich);
        recyclerDiaDiem.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false));
        diaDiemAdapter  = new DiaDiemAdapter(arrDiaDiem, this);
        recyclerDiaDiem.setAdapter(diaDiemAdapter);
        recyclerDiaDiem.setNestedScrollingEnabled(false);
    }

    @Override
    public void callbackDiaDiemFragment(int position) {
        DiaDiemDuLich diadiem = arrDiaDiem.get(position);
        Bundle bundle = setBundleDiaDiem(diadiem);
        ChiTietDiaDiemFragment chiTietDiaDiemFragment = (ChiTietDiaDiemFragment) getFragmentManager().findFragmentById(R.id.chitiet_diadiem_fragment);
        if (chiTietDiaDiemFragment == null){
            chiTietDiaDiemFragment = new ChiTietDiaDiemFragment();
            Toast.makeText(getActivity(), "new", Toast.LENGTH_SHORT).show();
        }
        chiTietDiaDiemFragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.framelayout_container, chiTietDiaDiemFragment, "chitiet_diadiem")
                .addToBackStack("dulich")
                .commit();
    }

    public void updateData(DiaDiemDuLich diadiem){
        arrDiaDiem.add(diadiem);
        arrTopDiaDiem.add(diadiem);
        topDiaDiemAdapter.notifyDataSetChanged();
        diaDiemAdapter.notifyDataSetChanged();
    }

    public void updateDataTop(){
        topDiaDiemAdapter.notifyDataSetChanged();
    }

    private Bundle setBundleDiaDiem(DiaDiemDuLich diadiem){
        Bundle bundle = new Bundle();
        bundle.putString("ten_dia_diem" , diadiem.getStrTenDiaDiem());
        bundle.putString("mo_ta"        , diadiem.getStrMoTa());
        bundle.putDouble("danh_gia"     , diadiem.getDbDanhGia());
        bundle.putInt   ("yeu_thich"    , diadiem.getIntYeuThich());
        bundle.putInt   ("so_luot_xem"  , diadiem.getIntSoLuotXem());
        bundle.putInt   ("check_in"     , diadiem.getIntCheckIn());
        bundle.putString("link_anh"     , diadiem.getStrLinkAnh());
        bundle.putString("dia_chi"      , diadiem.getStrDiaChi());
        return bundle;
    }

}
