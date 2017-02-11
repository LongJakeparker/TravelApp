package com.greenacademy.travelapp.Activity.Model;

import java.util.ArrayList;

/**
 * Created by DAVIDSON on 2/11/2017.
 */

public class ListQuanAnChiTiet {
    public ArrayList<QuanAnChiTiet> QuanAns;
    public int Status;
    public String Description;

    public ArrayList<QuanAnChiTiet> getQuanAns() {
        return QuanAns;
    }

    public void setQuanAns(ArrayList<QuanAnChiTiet> quanAns) {
        QuanAns = quanAns;
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
