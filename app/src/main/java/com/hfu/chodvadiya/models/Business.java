package com.hfu.chodvadiya.models;

public class Business
{
    private int id;
    private String name,xender,education,profession,profession_loc,mobile_number,home_address,village,taluka,district;

    public Business(int id, String name, String xender, String education, String profession, String profession_loc, String mobile_number, String home_address, String village, String taluka, String district) {
        this.id = id;
        this.name = name;
        this.xender = xender;
        this.education = education;
        this.profession = profession;
        this.profession_loc = profession_loc;
        this.mobile_number = mobile_number;
        this.home_address = home_address;
        this.village = village;
        this.taluka = taluka;
        this.district = district;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getXender() {
        return xender;
    }

    public String getEducation() {
        return education;
    }

    public String getProfession() {
        return profession;
    }

    public String getProfession_loc() {
        return profession_loc;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public String getHome_address() {
        return home_address;
    }

    public String getVillage() {
        return village;
    }

    public String getTaluka() {
        return taluka;
    }

    public String getDistrict() {
        return district;
    }
}