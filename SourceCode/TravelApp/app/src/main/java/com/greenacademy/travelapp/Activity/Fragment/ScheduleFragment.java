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
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.greenacademy.travelapp.Activity.Adapter.MyExpandListAdapter;
import com.greenacademy.travelapp.Activity.AsyncTask.ScheduleTask;
import com.greenacademy.travelapp.Activity.Interface.ScheduleInterface;
import com.greenacademy.travelapp.Activity.Model.ChildModel;
import com.greenacademy.travelapp.Activity.Model.HeaderModel;
import com.greenacademy.travelapp.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jake on 2/11/2017.
 */

public class ScheduleFragment extends Fragment implements ScheduleInterface{
    View v;
    ExpandableListView scheduleList;
    Button btnAdd;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_schedule, null);
        scheduleList = (ExpandableListView) v.findViewById(R.id.schedule_list);
        new ScheduleTask(this).execute();
        return v;
    }

    @Override
    public void CallBackData(List<HeaderModel> headerModel) {
        MyExpandListAdapter myExpandListAdapter = new MyExpandListAdapter(getContext(), headerModel);
        scheduleList.setAdapter(myExpandListAdapter);
    }
}
