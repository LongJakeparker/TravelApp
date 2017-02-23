package com.greenacademy.travelapp.Activity.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenacademy.travelapp.Activity.AsyncTask.DownloadImageTask;
import com.greenacademy.travelapp.Activity.Interface.DownloadImageInterface;
import com.greenacademy.travelapp.Activity.Model.HeaderModel;
import com.greenacademy.travelapp.R;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Jake on 2/11/2017.
 */

public class MyExpandListAdapter extends BaseExpandableListAdapter implements DownloadImageInterface{
    Bitmap bitmap;
    private Context context;
    private List<HeaderModel> headerModelList;

    public MyExpandListAdapter(Context context, List<HeaderModel> headerModelList) {
        this.context = context;
        this.headerModelList = headerModelList;
    }

    @Override
    public int getGroupCount() {
        return headerModelList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return headerModelList.get(groupPosition).getChild().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_header_timeline, null);
        }
        TextView Title = (TextView) convertView.findViewById(R.id.tvTitleHeaderSchedule);
        TextView CountChild = (TextView) convertView.findViewById(R.id.tvCountChildSchedule);

        ExpandableListView expandableListView = (ExpandableListView) parent;
        expandableListView.expandGroup(groupPosition);

        Title.setText(headerModelList.get(groupPosition).getTitle());
        CountChild.setText(String.valueOf(headerModelList.get(groupPosition).getChild().size()));

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_child_timeline, null);
        }
        TextView Time = (TextView) convertView.findViewById(R.id.tvTimeSchedule);
        TextView Name = (TextView) convertView.findViewById(R.id.tvNameChildSchedule);
        TextView Des = (TextView) convertView.findViewById(R.id.tvDescribeChildSchedule);
        TextView Likes = (TextView) convertView.findViewById(R.id.tvNumLikesSchedule);
        TextView Pics = (TextView) convertView.findViewById(R.id.tvNumPicSchedule);
        ImageView IconDes = (ImageView) convertView.findViewById(R.id.ivDescribeSchedule);

        Time.setText(headerModelList.get(groupPosition).getChild().get(childPosition).getTime());
        Name.setText(headerModelList.get(groupPosition).getChild().get(childPosition).getName());
        Des.setText(headerModelList.get(groupPosition).getChild().get(childPosition).getDescribe());
        Likes.setText(String.valueOf(headerModelList.get(groupPosition).getChild().get(childPosition).getLikes()));
        Pics.setText(String.valueOf(headerModelList.get(groupPosition).getChild().get(childPosition).getPics()));
        new DownloadImageTask(headerModelList.get(groupPosition).getChild().get(childPosition).getIconDes(), this).execute();
        IconDes.setImageBitmap(this.bitmap);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public void CallBackData(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
