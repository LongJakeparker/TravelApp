package com.greenacademy.travelapp.Activity.Model;

/**
 * Created by Jake on 2/11/2017.
 */

public class ChildModel {
    private String Time;
    private String Name;
    private String Describe;
    private String Likes;
    private String Pics;
    private int IconDes;

    public ChildModel(String time, String name, String describe, String likes, String pics, int iconDes) {
        Time = time;
        Name = name;
        Describe = describe;
        Likes = likes;
        Pics = pics;
        IconDes = iconDes;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescribe() {
        return Describe;
    }

    public void setDescribe(String describe) {
        Describe = describe;
    }

    public String getLikes() {
        return Likes;
    }

    public void setLikes(String likes) {
        Likes = likes;
    }

    public String getPics() {
        return Pics;
    }

    public void setPics(String pics) {
        Pics = pics;
    }

    public int getIconDes() {
        return IconDes;
    }

    public void setIconDes(int iconDes) {
        IconDes = iconDes;
    }
}
