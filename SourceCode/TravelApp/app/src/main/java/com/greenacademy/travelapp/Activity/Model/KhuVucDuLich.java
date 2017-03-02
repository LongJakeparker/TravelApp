package com.greenacademy.travelapp.Activity.Model;

/**
 * Created by PhamNhuVu on 2/11/2017.
 */

public class KhuVucDuLich {
    int id;
    String title;
    String link;
    int image;
    String description;
    int view;
    int like;
    double star;

    public KhuVucDuLich() {
    }

    public KhuVucDuLich(int id, String title, int image, String description, int view, int like, double star) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.description = description;
        this.view = view;
        this.like = like;
        this.star = star;
    }

    public KhuVucDuLich(String title, int image, String description, int view, int like, double star) {
        this.title = title;
        this.image = image;
        this.description = description;
        this.view = view;
        this.like = like;
        this.star = star;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStar(double star) {
        this.star = star;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public double getStar() {
        return star;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
