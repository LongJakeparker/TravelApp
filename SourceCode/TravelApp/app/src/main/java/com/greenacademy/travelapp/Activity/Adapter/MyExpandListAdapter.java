package com.greenacademy.travelapp.Activity.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenacademy.travelapp.Activity.AsyncTask.DownloadImageTask;
import com.greenacademy.travelapp.Activity.Interface.CallBackContextMenu;
import com.greenacademy.travelapp.Activity.Model.HeaderModel;
import com.greenacademy.travelapp.R;

import java.util.List;

/**
 * Created by Jake on 2/11/2017.
 */

public class MyExpandListAdapter extends BaseExpandableListAdapter{

    private Context context;
    private List<HeaderModel> headerModelList;
    private CallBackContextMenu callBackData;

    public MyExpandListAdapter(Context context, List<HeaderModel> headerModelList, CallBackContextMenu callBackData) {
        this.context = context;
        this.headerModelList = headerModelList;
        this.callBackData = callBackData;
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
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
         groupViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_header_timeline, null);

            convertView.setClickable(true);

            viewHolder = new groupViewHolder();

            viewHolder.Title = (TextView) convertView.findViewById(R.id.tvTitleHeaderSchedule);
            viewHolder.CountChild = (TextView) convertView.findViewById(R.id.tvCountChildSchedule);
            viewHolder.SumPics = (TextView) convertView.findViewById(R.id.tvSumPicSchedule);
            viewHolder.SumLikes = (TextView) convertView.findViewById(R.id.tvSumLikeSchedule);

            convertView.setTag(viewHolder);
        }
            viewHolder = (groupViewHolder) convertView.getTag();



        ExpandableListView expandableListView = (ExpandableListView) parent;
        expandableListView.expandGroup(groupPosition);

        viewHolder.Title.setText(headerModelList.get(groupPosition).getTitle());
        viewHolder.CountChild.setText(String.valueOf(headerModelList.get(groupPosition).getChild().size()));
        viewHolder.SumPics.setText(String.valueOf(headerModelList.get(groupPosition).getSumPic()));
        viewHolder.SumLikes.setText(String.valueOf(headerModelList.get(groupPosition).getSumLike()));

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        childViewHolder viewHolder;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_child_timeline, null);
            viewHolder = new childViewHolder();

            View view = new View(context);

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    callBackData.CallBackData(headerModelList.get(groupPosition).getChild().get(childPosition).getId());
                    return true;
                }
            });

            viewHolder.Time = (TextView) convertView.findViewById(R.id.tvTimeSchedule);
            viewHolder.Name = (TextView) convertView.findViewById(R.id.tvNameChildSchedule);
            viewHolder.Des = (TextView) convertView.findViewById(R.id.tvDescribeChildSchedule);
            viewHolder.Likes = (TextView) convertView.findViewById(R.id.tvNumLikesSchedule);
            viewHolder.Pics = (TextView) convertView.findViewById(R.id.tvNumPicSchedule);
            viewHolder.IconDes = (ImageView) convertView.findViewById(R.id.ivDescribeSchedule);
            convertView.setTag(viewHolder);
        }
        viewHolder = (childViewHolder) convertView.getTag();

        viewHolder.Time.setText(headerModelList.get(groupPosition).getChild().get(childPosition).getTime());
        viewHolder.Name.setText(headerModelList.get(groupPosition).getChild().get(childPosition).getName());
        viewHolder.Des.setText(headerModelList.get(groupPosition).getChild().get(childPosition).getDescribe());
        viewHolder.Likes.setText(String.valueOf(headerModelList.get(groupPosition).getChild().get(childPosition).getLikes()));
        viewHolder.Pics.setText(String.valueOf(headerModelList.get(groupPosition).getChild().get(childPosition).getPics()));
        viewHolder.IconDes.setImageResource(R.drawable.com_facebook_auth_dialog_background);
        new DownloadImageTask(viewHolder.IconDes ,headerModelList.get(groupPosition).getChild().get(childPosition).getIconDes()).execute();



        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }




    public static class groupViewHolder{
        TextView Title;
        TextView CountChild;
        TextView SumPics;
        TextView SumLikes;
    }

    public static class childViewHolder{
        TextView Time;
        TextView Name;
        TextView Des;
        TextView Likes;
        TextView Pics;
        ImageView IconDes;
    }
}
