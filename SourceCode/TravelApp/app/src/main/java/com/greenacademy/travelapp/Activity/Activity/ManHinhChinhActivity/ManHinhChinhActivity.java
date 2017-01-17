package com.greenacademy.travelapp.Activity.Activity.ManHinhChinhActivity;

import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.greenacademy.travelapp.R;

public class ManHinhChinhActivity extends AppCompatActivity  {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle menuLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh);
        initMenu();

    }

    private void initMenu(){
        toolbar = (Toolbar) findViewById(R.id.Toolbar_ManHinhChinh);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        drawerLayout = (DrawerLayout) findViewById(R.id.activity_man_hinh_chinh);
        menuLayout = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_menu, R.string.close_menu){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        menuLayout.setDrawerIndicatorEnabled(false);
        menuLayout.setHomeAsUpIndicator(R.drawable.ic_menu_manhinhchinh);
        menuLayout.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


    }

}
