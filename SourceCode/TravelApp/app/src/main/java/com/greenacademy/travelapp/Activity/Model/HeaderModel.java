package com.greenacademy.travelapp.Activity.Model;

import java.util.List;

/**
 * Created by Jake on 2/11/2017.
 */

public class HeaderModel {
    private int Id;
    private String Title;
    private int SumPic;
    private int SumLike;
    private String CountChild;
    private List<ChildModel> Child;

    public HeaderModel(){}

    public HeaderModel(String title, List<ChildModel> child) {
        Title = title;
        Child = child;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getSumPic() {
        return SumPic;
    }

    public void setSumPic(int sumPic) {
        SumPic = sumPic;
    }

    public int getSumLike() {
        return SumLike;
    }

    public void setSumLike(int sumLike) {
        SumLike = sumLike;
    }

    public List<ChildModel> getChild() {
        return Child;
    }

    public void setChild(List<ChildModel> child) {
        Child = child;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getCountChild() {
        return CountChild;
    }

    public void setCountChild(String countChild) {
        CountChild = countChild;
    }

}
