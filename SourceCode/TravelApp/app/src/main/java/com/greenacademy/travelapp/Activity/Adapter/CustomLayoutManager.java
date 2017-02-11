package com.greenacademy.travelapp.Activity.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * layoutmanager này dùng cho RecyclerView không kéo ngan dọc, lên xuống
 */

public class CustomLayoutManager extends LinearLayoutManager {
    public CustomLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }

    @Override
    public boolean canScrollHorizontally() {
        return false;
    }
}
