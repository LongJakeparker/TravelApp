package com.greenacademy.travelapp.Activity.CustomDialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.greenacademy.travelapp.R;

/**
 * Created by GIT on 2/9/2017.
 */

public class DialogWaitingLogin {
    Context context;
    int resource;
    Dialog dialog;

    public DialogWaitingLogin(Context context, int resource) {
        this.context = context;
        this.resource = resource;

    }

    public void createDialog(){
        dialog = new Dialog(context);
        dialog.setContentView(resource);
        dialog.setTitle("Waiting...");
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(false);
    }

    public void showDialog(){
        dialog.show();
    }

    public void closeDialog(){
        dialog.cancel();
    }
}
