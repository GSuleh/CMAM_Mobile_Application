package com.example.cmamapplication;

public class CommunityHealthUnit {

    String Name, Facility;
    Long Code;

    public CommunityHealthUnit() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFacility() {
        return Facility;
    }

    public void setFacility(String facility) {
        Facility = facility;
    }

    public Long getCode() {
        return Code;
    }

    public void setCode(Long code) {
        Code = code;
    }

    public CommunityHealthUnit(String name, String facility, Long code) {
        Name = name;
        Facility = facility;
        Code = code;
    }
}
