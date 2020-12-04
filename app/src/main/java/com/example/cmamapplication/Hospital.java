package com.example.cmamapplication;

public class Hospital {


    String  Officialname, Ward;
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

    public Hospital(String officialname, String ward, Long code) {
        Officialname = officialname;
        Ward = ward;
        Code = code;
    }
}
