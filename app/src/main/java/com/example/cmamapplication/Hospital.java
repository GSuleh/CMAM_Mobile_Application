package com.example.cmamapplication;

public class Hospital {


    String  Officialname, Ward;

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

    public Hospital(String officialname, String ward) {
        Officialname = officialname;
        Ward = ward;
    }
}
