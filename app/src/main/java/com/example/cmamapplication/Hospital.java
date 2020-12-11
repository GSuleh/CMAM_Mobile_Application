package com.example.cmamapplication;

public class Hospital {


    String  Officialname, Ward, Sub_county, Facility_type_category;
    Long Code;

    public Hospital() {
    }

    public String getOfficialname() {
        return Officialname;
    }

    public void setOfficialname(String officialname) {
        Officialname = officialname;
    }

    public String getWard() {
        return Ward;
    }

    public void setWard(String ward) {
        Ward = ward;
    }

    public Long getCode() {
        return Code;
    }

    public void setCode(Long code) {
        Code = code;
    }

    public String getSub_county() {
        return Sub_county;
    }

    public void setSub_county(String sub_county) {
        Sub_county = sub_county;
    }

    public String getFacility_type_category() {
        return Facility_type_category;
    }

    public void setFacility_type_category(String facility_type_category) {
        Facility_type_category = facility_type_category;
    }

    public Hospital(String officialname, String ward, Long code) {
        Officialname = officialname;
        Ward = ward;
        Code = code;
    }
}
