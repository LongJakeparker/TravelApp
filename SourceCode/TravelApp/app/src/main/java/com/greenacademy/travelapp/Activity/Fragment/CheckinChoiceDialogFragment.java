package com.greenacademy.travelapp.Activity.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.SearchView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;

import com.greenacademy.travelapp.Activity.Adapter.ListMapAdapter;
import com.greenacademy.travelapp.Activity.Item.ListMapItem;
import com.greenacademy.travelapp.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jake on 2/18/2017.
 */

public class CheckinChoiceDialogFragment extends DialogFragment {
    View v;
    private ListView lvMap;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_check_in_choice, null);
        getDialog().setCanceledOnTouchOutside(true);

        SearchView();

        lvMap = (ListView) v.findViewById(R.id.lvMapNear);

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


        ListMapAdapter adapter = new ListMapAdapter(getContext(), R.layout.item_listmap_near, listMapItems );
        lvMap.setAdapter(adapter);
        return v;
    }

    private void SearchView() {
        SearchView searchView = (SearchView) v.findViewById(R.id.SearchView);
        int searchSrcTextId = getResources().getIdentifier("android:id/search_src_text", null, null);
        EditText searchEditText = (EditText) searchView.findViewById(searchSrcTextId);
        searchEditText.setTextSize(15);

        //Removing underline
        int searchPlateId = searchView.getContext().getResources().getIdentifier("android:id/search_plate", null, null);
        View searchPlate = searchView.findViewById(searchPlateId);
        if (searchPlate!=null) {
            searchPlate.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.custom_background_checkinchoice_dialog);
    }

    @Override
    public void onResume() {
        super.onResume();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getDialog().getWindow().setLayout((int) (width*.9), (int) (height*.8));
        getDialog().getWindow().setGravity(Gravity.CENTER);
    }
}
