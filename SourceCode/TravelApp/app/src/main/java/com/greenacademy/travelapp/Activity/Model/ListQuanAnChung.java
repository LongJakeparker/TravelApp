package com.greenacademy.travelapp.Activity.Model;

import java.util.ArrayList;

/**
 * Created by GIT on 2/9/2017.
 */


public class ListQuanAnChung {

    public ArrayList<QuanAnChung> quanAnChung;
    public int Status;
    public String Description ;

    public ArrayList<QuanAnChung> getLoaiQuanAns() {
        return quanAnChung;
    }

    public void setLoaiQuanAns(ArrayList<QuanAnChung> quanAnChung) {
        this.quanAnChung = quanAnChung;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
