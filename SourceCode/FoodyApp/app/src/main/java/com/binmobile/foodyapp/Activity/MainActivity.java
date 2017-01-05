package com.binmobile.foodyapp.Activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.binmobile.foodyapp.Fragment.CheckIn.FragmentCheckIn;
import com.binmobile.foodyapp.Fragment.CheckIn.FragmentFiles;
import com.binmobile.foodyapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState==null){
            FragmentCheckIn  fragment=new FragmentCheckIn();
            fragment.setMainActivity(this);
            setFragment(fragment);
        }
    }

    public void setFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.myContainer,fragment ,String.valueOf(fragment.getId())).addToBackStack(null).commit();
    }
}
