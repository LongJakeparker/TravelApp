package com.greenacademy.travelapp.Activity.Utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.greenacademy.travelapp.R;

/**
 * Created by PhamNhuVu on 2/11/2017.
 */

public class FragmentUtils {

    public static void addFragment(FragmentActivity activity, Fragment fragmentTransaction, int container, String tag){
        activity.getSupportFragmentManager().beginTransaction()
                .add(container, fragmentTransaction, tag)
                .addToBackStack(null)
                .commit();
    }

    public static void replaceFragment(FragmentActivity activity, Fragment fragmentTransaction, int container, String tag){
        activity.getSupportFragmentManager().beginTransaction()
                .replace(container, fragmentTransaction, tag)
                .addToBackStack(null)
                .commit();
    }

    public static void replaceFragmentWithouBackStack(FragmentActivity activity, Fragment fragmentTransaction, int container, String tag){
        activity.getSupportFragmentManager().beginTransaction()
                .replace(container, fragmentTransaction, tag)
                .commit();
    }
}
