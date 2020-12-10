package com.example.cmamapplication;

public class CommunityHealthUnit {

    String Name, Facility, Facility_subcounty, Status;
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

    public String getFacility_subcounty() {
        return Facility_subcounty;
    }

    public void setFacility_subcounty(String facility_subcounty) {
        Facility_subcounty = facility_subcounty;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public CommunityHealthUnit(String name, String facility, String facility_subcounty, String status, Long code) {
        Name = name;
        Facility = facility;
        Facility_subcounty = facility_subcounty;
        Status = status;
        Code = code;
    }
}
