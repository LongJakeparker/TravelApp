package com.greenacademy.travelapp.Activity.CustomDialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.greenacademy.travelapp.R;


/**
 * Created by GIT on 2/9/2017.
 */

public class DialogWaitingLogin {
    Context context;
    int resource;
    Dialog dialog;
    String title;

    public DialogWaitingLogin(Context context, int resource, String title) {
        this.context = context;
        this.resource = resource;
        this.title = title;
    }

    public void createDialog(String title){
        dialog = new Dialog(context);
        dialog.setContentView(resource);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tvWaittingBarStyle);
        tvTitle.setText(title);
//        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setTitle(title);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    public void showDialog(){
        dialog.show();
    }

    public void closeDialog(){
        dialog.cancel();
    }
}
