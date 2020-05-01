package com.hfu.chodvadiya.models;

public class Family
{
    private int id;
    private String name,relation,xender,blood_group,status,mobile_number,dob,education,profession,profession_field,village_mosal,surname_mosal;

    public Family(int id, String name, String relation, String xender, String blood_group, String status, String mobile_number, String dob, String education, String profession, String profession_field, String village_mosal, String surname_mosal) {
        this.id = id;
        this.name = name;
        this.relation = relation;
        this.xender = xender;
        this.blood_group = blood_group;
        this.status = status;
        this.mobile_number = mobile_number;
        this.dob = dob;
        this.education = education;
        this.profession = profession;
        this.profession_field = profession_field;
        this.village_mosal = village_mosal;
        this.surname_mosal = surname_mosal;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRelation() {
        return relation;
    }

    public String getXender() {
        return xender;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public String getStatus() {
        return status;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public String getDob() {
        return dob;
    }

    public String getEducation() {
        return education;
    }

    public String getProfession() {
        return profession;
    }

    public String getProfession_field() {
        return profession_field;
    }

    public String getVillage_mosal() {
        return village_mosal;
    }

    public String getSurname_mosal() {
        return surname_mosal;
    }
}
