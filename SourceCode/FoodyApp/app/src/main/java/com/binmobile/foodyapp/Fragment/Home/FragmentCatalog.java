package com.binmobile.foodyapp.Fragment.Home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.binmobile.foodyapp.Adapter.CatalogAdapter;
import com.binmobile.foodyapp.R;

/**
 * Created by nthanhphong on 9/1/2016.
 */

public class FragmentCatalog extends Fragment {
    private View rootView;
    private ListView listviewCatalog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_catalog, null);
        InitView();
        SetData();
        return rootView;
    }

    private void SetData(){
        listviewCatalog.setAdapter(new CatalogAdapter(getContext(),null));
    }

    private void InitView() {
        listviewCatalog = (ListView) rootView.findViewById(R.id.listview_catalog);
    }
}
