package com.greenacademy.travelapp.Activity.Model;

import android.graphics.Bitmap;

import java.util.BitSet;

/**
 * Created by Jake on 2/11/2017.
 */

public class ChildModel {
    private int Id;
    private int IdDiaDiem;
    private int IdNgayChuyenDi;
    private int LoaiDiaDiem;
    private String Time;
    private String Name;
    private String Describe;
    private int Likes;
    private int Pics;
    private String IconDes;

    public ChildModel(){}

    public ChildModel(String time, String name, String describe, int likes, int pics, String iconDes) {
        Time = time;
        Name = name;
        Describe = describe;
        Likes = likes;
        Pics = pics;
        IconDes = iconDes;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdDiaDiem() {
        return IdDiaDiem;
    }

    public void setIdDiaDiem(int idDiaDiem) {
        IdDiaDiem = idDiaDiem;
    }

    public int getIdNgayChuyenDi() {
        return IdNgayChuyenDi;
    }

    public void setIdNgayChuyenDi(int idNgayChuyenDi) {
        IdNgayChuyenDi = idNgayChuyenDi;
    }

    public int getLoaiDiaDiem() {
        return LoaiDiaDiem;
    }

    public void setLoaiDiaDiem(int loaiDiaDiem) {
        LoaiDiaDiem = loaiDiaDiem;
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

    public int getLikes() {
        return Likes;
    }

    public void setLikes(int likes) {
        Likes = likes;
    }

    public int getPics() {
        return Pics;
    }

    public void setPics(int pics) {
        Pics = pics;
    }

    public String getIconDes() {
        return IconDes;
    }

    public void setIconDes(String iconDes) {
        IconDes = iconDes;
    }
}
