package com.example.cmamapplication;

public class Patient {

    public String fullname, guardian_name,  guardian_phone, treatment_group,  chv_id , pregnant, status;
    public Long guardian_nat_id, chu_code;

    public Patient(){}

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGuardian_name() {
        return guardian_name;
    }

    public void setGuardian_name(String guardian_name) {
        this.guardian_name = guardian_name;
    }

    public String getGuardian_phone() {
        return guardian_phone;
    }

    public void setGuardian_phone(String guardian_phone) {
        this.guardian_phone = guardian_phone;
    }

    public String getTreatment_group() {
        return treatment_group;
    }

    public void setTreatment_group(String treatment_group) {
        this.treatment_group = treatment_group;
    }

    public Long getGuardian_nat_id() {
        return guardian_nat_id;
    }

    public void setGuardian_nat_id(Long guardian_nat_id) {
        this.guardian_nat_id = guardian_nat_id;
    }

    public Long getChu_code() {
        return chu_code;
    }

    public void setChu_code(Long chu_code) {
        this.chu_code = chu_code;
    }

    public String getChv_id() {
        return chv_id;
    }

    public void setChv_id(String chv_id) {
        this.chv_id = chv_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPregnant() {
        return pregnant;
    }

    public void setPregnant(String pregnant) {
        this.pregnant = pregnant;
    }

    public Patient(String fullname, String guardian_name, String guardian_phone, String treatment_group, Long guardian_nat_id, Long chu_code, String chv_id, String status, String pregnant) {
        this.fullname = fullname;
        this.guardian_name = guardian_name;
        this.guardian_phone = guardian_phone;
        this.treatment_group = treatment_group;
        this.guardian_nat_id = guardian_nat_id;
        this.chu_code = chu_code;
        this.chv_id = chv_id;
        this.status = status;
        this.pregnant = pregnant;
    }
}
