package com.hfu.chodvadiya.models;

public class Bloods
{
    private int id;
    private String name,village,taluko,district,address,xender,mobile;

    public Bloods(int id, String name, String village, String taluko, String district, String address, String xender, String mobile) {
        this.id = id;
        this.name = name;
        this.village = village;
        this.taluko = taluko;
        this.district = district;
        this.address = address;
        this.xender = xender;
        this.mobile = mobile;
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

    public String getTaluko() {
        return taluko;
    }

    public String getDistrict() {
        return district;
    }

    public String getAddress() {
        return address;
    }

    public String getXender() {
        return xender;
    }

    public String getMobile() {
        return mobile;
    }
}
