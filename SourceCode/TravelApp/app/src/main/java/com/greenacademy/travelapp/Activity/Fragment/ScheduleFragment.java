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

import com.greenacademy.travelapp.Activity.Activity.ManHinhChinhActivity;
import com.greenacademy.travelapp.Activity.Adapter.MyExpandListAdapter;
import com.greenacademy.travelapp.Activity.AsyncTask.DelScheduleTask;
import com.greenacademy.travelapp.Activity.AsyncTask.ScheduleTask;
import com.greenacademy.travelapp.Activity.Constant.Constant;
import com.greenacademy.travelapp.Activity.CustomDialog.DialogWaitingLogin;
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

public class ScheduleFragment extends Fragment implements ScheduleInterface, DelScheduleInterface{
    View v;
    ExpandableListView scheduleList;
    ImageButton btnAdd;
    List<HeaderModel> headerModel;
    DialogWaitingLogin dialog;
    private MyExpandListAdapter myExpandListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_schedule, null);
        ((ManHinhChinhActivity) getActivity()).linearControlDuLich.setVisibility(View.GONE);
        scheduleList = (ExpandableListView) v.findViewById(R.id.schedule_list);
        registerForContextMenu(scheduleList);

        dialog = new DialogWaitingLogin(getContext(), R.layout.custom_dialog_progressbar, Constant.TITLE_DIALOG_WAITTING_PROCESS);
        dialog.createDialog("Waiting...");

        scheduleList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Toast.makeText(getContext(), "Click", Toast.LENGTH_LONG).show();
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
        this.headerModel = headerModel;
        myExpandListAdapter = new MyExpandListAdapter(getContext(), headerModel);
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
        ExpandableListView.ExpandableListContextMenuInfo info =
                (ExpandableListView.ExpandableListContextMenuInfo) item.getMenuInfo();
        int groupPos = 0, childPos = 0;
        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
            groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition);
            childPos = ExpandableListView.getPackedPositionChild(info.packedPosition);
        }
        switch (item.getItemId()){
            case R.id.DelScheduleMenu:
                new DelScheduleTask(headerModel.get(groupPos).getChild().get(childPos).getId(), this).execute();
                headerModel.get(groupPos).getChild().remove(childPos);
                dialog.showDialog();
                break;
        }
        return true;
    }

    @Override
    public void CallBackData(StatusDelSchedule statusDelSchedule) {
        if (statusDelSchedule == StatusDelSchedule.THANH_CONG){
            dialog.closeDialog();
            Toast.makeText(getContext(),"Đã Xóa", Toast.LENGTH_LONG).show();
        } else {
            dialog.closeDialog();
        }
    }

//    @Override
//    public void CallBackData(int childPosition) {
//        registerForContextMenu(scheduleList);
//        this.childId = childPosition;
//    }
}
