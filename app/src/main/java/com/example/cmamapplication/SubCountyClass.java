package com.example.cmamapplication;

public class SubCountyClass {

    String sub_county;
    Long code;

    public SubCountyClass() {
    }

    public String getSub_county() {
        return sub_county;
    }

    public void setSub_county(String sub_county) {
        this.sub_county = sub_county;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public SubCountyClass(String sub_county, Long code) {
        this.sub_county = sub_county;
        this.code = code;
    }
}
