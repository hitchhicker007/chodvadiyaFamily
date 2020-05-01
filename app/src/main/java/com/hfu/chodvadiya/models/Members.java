package com.hfu.chodvadiya.models;

public class Members
{
    private int id;
    private String name, village, mobile, image;

    public Members(int id, String name, String village, String mobile, String image) {
        this.id = id;
        this.name = name;
        this.village = village;
        this.mobile = mobile;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getVillage() {
        return village;
    }

    public String getMobile() {
        return mobile;
    }

    public String getImage() {
        return image;
    }
}
