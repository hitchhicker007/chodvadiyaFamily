package com.hfu.chodvadiya.models;

public class Contacts
{
    private int id;
    private String name,profile_pic,mobile_number,address,profession,email;

    public Contacts(int id, String name, String profile_pic, String mobile_number, String address, String profession) {
        this.id = id;
        this.name = name;
        this.profile_pic = profile_pic;
        this.mobile_number = mobile_number;
        this.address = address;
        this.profession = profession;
//        this.email = email;
    }

    public int getId() {
        return id;
    }

//    public String getEmail() {
//        return email;
//    }

    public String getName() {
        return name;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public String getAddress() {
        return address;
    }

    public String getProfession() {
        return profession;
    }
}
