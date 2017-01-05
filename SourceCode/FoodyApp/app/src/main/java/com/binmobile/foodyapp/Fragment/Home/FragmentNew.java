package com.binmobile.foodyapp.Fragment.Home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binmobile.foodyapp.Adapter.HomeAdapter;
import com.binmobile.foodyapp.Model.DiaDiem;
import com.binmobile.foodyapp.Network.ApiType;
import com.binmobile.foodyapp.Network.CallApi;
import com.binmobile.foodyapp.Network.CallBackData;
import com.binmobile.foodyapp.Network.HttpParam;
import com.binmobile.foodyapp.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by nthanhphong on 9/1/2016.
 */

public class FragmentNew extends Fragment implements CallBackData {
    RecyclerView recyclerView;
    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_new, null);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
//        recyclerView.setAdapter(new HomeAdapter(getActivity()));
        CallApi.getInstance().SetCallback(this);
        CallApi.getInstance().CallApiServer(ApiType.GET_DIA_DIEM, null, null);
        return rootView;
    }

    @Override
    public void Callback(ApiType apiType, String json) {
        switch (apiType) {
            case GET_DIA_DIEM:
                List<DiaDiem> result=parserDiaDiem(json);
                recyclerView.setAdapter(new HomeAdapter(getActivity(),result));
                break;
        }
    }

    public List<DiaDiem> parserDiaDiem(String json){
        List<DiaDiem> result=new LinkedList<>();
        try {
            JSONArray listDiadiem=new JSONArray(json);
            for(int i=0;i<listDiadiem.length();i++){
                JSONObject node=listDiadiem.getJSONObject(i);
                DiaDiem diadiem=new DiaDiem();
                diadiem.Id=node.getInt("Id");
                diadiem.anh=node.getString("Anh");
                diadiem.ten=node.getString("Ten");
                result.add(diadiem);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
