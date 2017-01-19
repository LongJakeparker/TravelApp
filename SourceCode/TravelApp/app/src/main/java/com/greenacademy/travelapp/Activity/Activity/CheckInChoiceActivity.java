package com.greenacademy.travelapp.Activity.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.greenacademy.travelapp.Activity.Adapter.ListMapAdapter;
import com.greenacademy.travelapp.Activity.Item.ListMapItem;
import com.greenacademy.travelapp.R;

import java.util.LinkedList;
import java.util.List;

public class CheckInChoiceActivity extends AppCompatActivity {
    ListView lvMap;
    ImageView ivSearchMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_choice);

        lvMap = (ListView) findViewById(R.id.lvMapNear);
        initivSearchMap();


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

    private void initivSearchMap() {
        ivSearchMap = (ImageView) findViewById(R.id.ivMapSearchCheckIn);
        ivSearchMap.setImageResource(R.drawable.sample_map);
    }
}
