package com.greenacademy.travelapp.Activity.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.greenacademy.travelapp.Activity.AsyncTask.AddScheduleTask;
import com.greenacademy.travelapp.Activity.Constant.Constant;
import com.greenacademy.travelapp.Activity.CustomDialog.DialogWaitingLogin;
import com.greenacademy.travelapp.Activity.Enum.StatusAddSchedule;
import com.greenacademy.travelapp.Activity.Interface.AddScheduleInterface;
import com.greenacademy.travelapp.R;

/**
 * Created by Jake on 2/25/2017.
 */

public class DescriptionDialogFragment extends DialogFragment implements View.OnClickListener, AddScheduleInterface {
    View v;
    EditText etDescrip;
    Button btnCancel;
    Button btnAdd;
    Bundle bundle;
    TextView tvName;
    DialogWaitingLogin dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_description_dialog, null);

        etDescrip = (EditText) v.findViewById(R.id.etDescripTrip);
        btnAdd = (Button) v.findViewById(R.id.btnAddTrip);
        btnCancel = (Button) v.findViewById(R.id.btnCancelTrip);
        tvName = (TextView) v.findViewById(R.id.tvNameDesTrip);

        bundle = getArguments();

        tvName.setText(bundle.getString(Constant.KEY_NAME_LOCATION));

        dialog = new DialogWaitingLogin(getContext(), R.layout.custom_dialog_progressbar, Constant.TITLE_DIALOG_WAITTING_PROCESS);
        dialog.createDialog("Waiting...");

        btnCancel.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAddTrip:
                new AddScheduleTask(bundle.getInt(Constant.KEY_ID_DIADIEM),
                        bundle.getInt(Constant.KEY_ID_LOAIDIADIEM),
                        etDescrip.getText().toString(),this).execute();
                dialog.showDialog();
                break;
            case R.id.btnCancelTrip:
                dismiss();
                break;
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public void CallBackData(StatusAddSchedule statusAddSchedule) {
        if (statusAddSchedule == statusAddSchedule.THAT_BAI){
            Toast.makeText(getContext(),"Đã có lỗi xảy ra!!", Toast.LENGTH_LONG).show();
            dialog.closeDialog();
        }else {
            dialog.closeDialog();
            dismiss();
            Toast.makeText(getContext(),"Đã thêm thành công!!", Toast.LENGTH_LONG).show();
        }
    }
}
