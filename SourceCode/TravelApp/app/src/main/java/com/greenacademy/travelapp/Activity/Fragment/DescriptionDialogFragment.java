package com.greenacademy.travelapp.Activity.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.greenacademy.travelapp.R;

/**
 * Created by Jake on 2/25/2017.
 */

public class DescriptionDialogFragment extends DialogFragment implements View.OnClickListener {
    View v;
    EditText etDescrip;
    Button btnCancel;
    Button btnAdd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_description_dialog, null);

        etDescrip = (EditText) v.findViewById(R.id.etDescripTrip);
        btnAdd = (Button) v.findViewById(R.id.btnAddTrip);
        btnCancel = (Button) v.findViewById(R.id.btnCancelTrip);

        btnCancel.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAddTrip:
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
}
