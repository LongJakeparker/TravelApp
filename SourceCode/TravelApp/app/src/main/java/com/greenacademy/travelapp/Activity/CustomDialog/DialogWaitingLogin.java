package com.greenacademy.travelapp.Activity.CustomDialog;

import android.app.Dialog;
import android.content.Context;

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

    public void createDialog(String title){
        dialog = new Dialog(context);
        dialog.setContentView(resource);
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
