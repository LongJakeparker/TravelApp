package com.greenacademy.travelapp.Activity.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.greenacademy.travelapp.Activity.Adapter.ListMapAdapter;
import com.greenacademy.travelapp.Activity.Item.ListMapItem;
import com.greenacademy.travelapp.R;

import java.util.LinkedList;
import java.util.List;

public class CheckInChoiceActivity extends AppCompatActivity {
    private ListView lvMap;
    private ImageView ivSearchMap;
    private EditText etSearchCheckin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_choice);

        lvMap = (ListView) findViewById(R.id.lvMapNear);
        initivSearchMap();
//        initetSerchCheckin();

        ListMapItem listMapItem = new ListMapItem(R.drawable.icon_hotel, "Riverside Hotel", "dsadasdasdasdasdasd sdadasdasdas \n adsdadadad \n dsadsds \n adasdsdas","4.5km");

        List<ListMapItem> listMapItems = new LinkedList<>();
        listMapItems.add(listMapItem);
        listMapItems.add(listMapItem);
        listMapItems.add(listMapItem);
        listMapItems.add(listMapItem);
        listMapItems.add(listMapItem);
        listMapItems.add(listMapItem);
        listMapItems.add(listMapItem);
        listMapItems.add(listMapItem);
        listMapItems.add(listMapItem);
        listMapItems.add(listMapItem);
        listMapItems.add(listMapItem);
        listMapItems.add(listMapItem);
        listMapItems.add(listMapItem);


        ListMapAdapter adapter = new ListMapAdapter(getBaseContext(), R.layout.item_listmap_near, listMapItems );
        lvMap.setAdapter(adapter);

    }

//    private void initetSerchCheckin(){
//        etSearchCheckin = (EditText) findViewById(R.id.etSearchChecInChoice);
//        etSearchCheckin.setFocusable(false);
//        etSearchCheckin.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                v.setFocusableInTouchMode(true);
//                v.setFocusable(true);
//                ivSearchMap.setVisibility(View.GONE);
//                return false;
//            }
//        });
//    }

    private void initivSearchMap() {
        ivSearchMap = (ImageView) findViewById(R.id.ivMapSearchCheckIn);
        ivSearchMap.setImageResource(R.drawable.sample_map);
    }

    @Override
    public void onBackPressed() {
        if (ivSearchMap.getVisibility() == View.GONE){
            ivSearchMap.setVisibility(View.VISIBLE);
            etSearchCheckin.setFocusable(false);
        }
        else {
            super.onBackPressed();
        }
    }
}
