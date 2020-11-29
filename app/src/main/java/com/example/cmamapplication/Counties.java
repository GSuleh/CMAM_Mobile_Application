package com.example.cmamapplication;

import java.util.List;

public class Counties {

    String  county;
    Long code,subcounties;

    public Counties() {
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Long getSubcounties() {
        return subcounties;
    }

    public void setSubcounties(Long subcounties) {
        this.subcounties = subcounties;
    }

    public Counties(String county, Long code, Long subcounties) {
        this.county = county;
        this.code = code;
        this.subcounties = subcounties;
    }
}
