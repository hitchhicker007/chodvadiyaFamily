package com.hfu.chodvadiya.models;

public class Gallary
{
    private int id;
    private String title,image,date;

    public Gallary(int id, String title, String image, String date) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getDate() {
        return date;
    }
}
