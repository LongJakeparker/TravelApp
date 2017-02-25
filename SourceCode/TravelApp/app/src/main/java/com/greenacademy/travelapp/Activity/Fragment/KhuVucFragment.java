package com.greenacademy.travelapp.Activity.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenacademy.travelapp.Activity.Adapter.KhuVucAdapter;
import com.greenacademy.travelapp.Activity.Model.KhuVucDuLich;
import com.greenacademy.travelapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class KhuVucFragment extends Fragment {

    ArrayList<KhuVucDuLich> arrKhuVuc;
    View view;
    RecyclerView recyclerKhuVuc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_khu_vuc, container, false);
        initView();
        return view;
    }

    private void initView(){
        recyclerKhuVuc = (RecyclerView) view.findViewById(R.id.recyclerKhuVuc_DuLich);
        recyclerKhuVuc.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        arrKhuVuc = new ArrayList<>();
        arrKhuVuc.add(new KhuVucDuLich("Vũng Tàu", R.drawable.image_khuvuc1_default, "Đẹp quá trời", 109, 8173, 4.5));
        arrKhuVuc.add(new KhuVucDuLich("Vịnh Hạ Long", R.drawable.image_khuvuc2_default,"Đẹp không có chỗ chê", 109, 80, 4.7));
        arrKhuVuc.add(new KhuVucDuLich("Nha Trang", R.drawable.image_khuvuc1_default,"Đẹp không có từ ngữ nào có thể tả", 431, 5321, 4.1));
        arrKhuVuc.add(new KhuVucDuLich("Cà Mau", R.drawable.image_khuvuc2_default,"Đẹp không nói nên lời", 1345, 641, 3.5));
        arrKhuVuc.add(new KhuVucDuLich("Mũi Né", R.drawable.image_khuvuc1_default,"Đẹp không gì sánh bằng", 541, 653, 3.1));
        arrKhuVuc.add(new KhuVucDuLich("Đà Nẵng", R.drawable.image_khuvuc2_default,"Đẹp tuyệt vời", 1341, 1516, 4.8));
        recyclerKhuVuc.setAdapter(new KhuVucAdapter(arrKhuVuc));
    }

}
