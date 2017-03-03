package com.greenacademy.travelapp.Activity.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.greenacademy.travelapp.Activity.Adapter.MyExpandListAdapter;
import com.greenacademy.travelapp.Activity.AsyncTask.DelScheduleTask;
import com.greenacademy.travelapp.Activity.AsyncTask.ScheduleTask;
import com.greenacademy.travelapp.Activity.Enum.StatusDelSchedule;
import com.greenacademy.travelapp.Activity.Interface.CallBackContextMenu;
import com.greenacademy.travelapp.Activity.Interface.DelScheduleInterface;
import com.greenacademy.travelapp.Activity.Interface.ScheduleInterface;
import com.greenacademy.travelapp.Activity.Model.HeaderModel;
import com.greenacademy.travelapp.R;

import java.util.List;


/**
 * Created by Jake on 2/11/2017.
 */

public class ScheduleFragment extends Fragment implements ScheduleInterface, DelScheduleInterface, CallBackContextMenu{
    View v;
    ExpandableListView scheduleList;
    ImageButton btnAdd;
    int childId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_schedule, null);
        scheduleList = (ExpandableListView) v.findViewById(R.id.schedule_list);

        scheduleList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                
                return true;
            }
        });

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
        MyExpandListAdapter myExpandListAdapter = new MyExpandListAdapter(getContext(), headerModel, this);
        scheduleList.setAdapter(myExpandListAdapter);
    }

    //Khởi tạo menu option cho sự kiện long click on ExpandList
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);


        ExpandableListView.ExpandableListContextMenuInfo info = (ExpandableListView.ExpandableListContextMenuInfo) menuInfo;

        int type = ExpandableListView.getPackedPositionType(info.packedPosition);

        if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
            getActivity().getMenuInflater().inflate(R.menu.menu_schedule, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.DelScheduleMenu:
                new DelScheduleTask(childId, this).execute();
                break;
        }
        return true;
    }

    @Override
    public void CallBackData(StatusDelSchedule statusDelSchedule) {
        if (statusDelSchedule == StatusDelSchedule.THANH_CONG){
            Toast.makeText(getContext(),"Đã Xóa", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void CallBackData(int childPosition) {
        registerForContextMenu(scheduleList);
        this.childId = childPosition;
    }
}
