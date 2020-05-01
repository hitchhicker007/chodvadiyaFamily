package com.hfu.chodvadiya.models;

public class Education
{
    private int id;
    private String name,mobile_number,profession,profession_field,village,taluka,xender,district,home_address;

    public Education(int id, String name, String mobile_number, String profession, String profession_field, String village, String taluka, String xender, String district, String home_address) {
        this.id = id;
        this.name = name;
        this.mobile_number = mobile_number;
        this.profession = profession;
        this.profession_field = profession_field;
        this.village = village;
        this.taluka = taluka;
        this.xender = xender;
        this.district = district;
        this.home_address = home_address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public String getProfession() {
        return profession;
    }

    public String getProfession_field() {
        return profession_field;
    }

    public String getVillage() {
        return village;
    }

    public String getTaluka() {
        return taluka;
    }

    public String getXender() {
        return xender;
    }

    public String getDistrict() {
        return district;
    }

    public String getHome_address() {
        return home_address;
    }
}

