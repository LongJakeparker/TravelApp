package com.greenacademy.travelapp.Activity.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ExpandableListView;
import android.widget.ImageButton;

import com.greenacademy.travelapp.Activity.Adapter.MyExpandListAdapter;
import com.greenacademy.travelapp.Activity.AsyncTask.ScheduleTask;
import com.greenacademy.travelapp.Activity.Interface.ScheduleInterface;
import com.greenacademy.travelapp.Activity.Model.HeaderModel;
import com.greenacademy.travelapp.R;

import java.util.List;


/**
 * Created by Jake on 2/11/2017.
 */

public class ScheduleFragment extends Fragment implements ScheduleInterface{
    View v;
    ExpandableListView scheduleList;
    ImageButton btnAdd;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_schedule, null);
        scheduleList = (ExpandableListView) v.findViewById(R.id.schedule_list);
        new ScheduleTask(this).execute();

        setButtonAdd();

        return v;
    }

    private void setButtonAdd() {
        btnAdd = (ImageButton) v.findViewById(R.id.btnAddSchedule);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation click = AnimationUtils.loadAnimation(getContext(), R.anim.click_image);
                btnAdd.startAnimation(click);

                CheckinChoiceDialogFragment myDialog = new CheckinChoiceDialogFragment();
                myDialog.show(getFragmentManager(), "Địa Điểm");
            }
        });
    }

    @Override
    public void CallBackData(List<HeaderModel> headerModel) {
        MyExpandListAdapter myExpandListAdapter = new MyExpandListAdapter(getContext(), headerModel);
        scheduleList.setAdapter(myExpandListAdapter);
        scheduleList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return false;
            }
        });
    }
}
