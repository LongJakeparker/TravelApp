package com.greenacademy.travelapp.Activity.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.greenacademy.travelapp.Activity.Adapter.MyExpandListAdapter;
import com.greenacademy.travelapp.Activity.Model.ChildModel;
import com.greenacademy.travelapp.Activity.Model.HeaderModel;
import com.greenacademy.travelapp.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jake on 2/11/2017.
 */

public class ScheduleFragment extends Fragment {
    View v;
    Toolbar toolbar;
    ExpandableListView scheduleList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_schedule, null);
        scheduleList = (ExpandableListView) v.findViewById(R.id.schedule_list);
        toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        //((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<ChildModel> childModels = new ArrayList<>();
        childModels.add(new ChildModel("9:45","Chợ Hoa Đà Lạt","asdacascacac \nsadasdacasc","55", "10", R.drawable.common_google_signin_btn_icon_light));
        childModels.add(new ChildModel("9:45","Chợ Hoa Đà Lạt","asdacascacac \nsadasdacasc","55", "10", R.drawable.common_google_signin_btn_icon_light));
        childModels.add(new ChildModel("9:45","Chợ Hoa Đà Lạt","asdacascacac \nsadasdacasc","55", "10", R.drawable.common_google_signin_btn_icon_light));
        childModels.add(new ChildModel("9:45","Chợ Hoa Đà Lạt","asdacascacac \nsadasdacasc","55", "10", R.drawable.common_google_signin_btn_icon_light));
        childModels.add(new ChildModel("9:45","Chợ Hoa Đà Lạt","asdacascacac \nsadasdacasc","55", "10", R.drawable.common_google_signin_btn_icon_light));
        List<HeaderModel> headerModels = new ArrayList<>();
        headerModels.add(new HeaderModel("14/2/2017", childModels));
        headerModels.add(new HeaderModel("6/3/2017", childModels));
        headerModels.add(new HeaderModel("6/12/2017", childModels));
        headerModels.add(new HeaderModel("10/2/2018", childModels));

        MyExpandListAdapter myExpandListAdapter = new MyExpandListAdapter(getContext(),headerModels);
        scheduleList.setAdapter(myExpandListAdapter);

        return v;
    }
}
