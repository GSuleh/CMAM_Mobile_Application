package com.example.cmamapplication;

public class FollowUp {

    String patient_id, chv_id,date, status;

    public FollowUp() {
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getChv_id() {
        return chv_id;
    }

    public void setChv_id(String chv_id) {
        this.chv_id = chv_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FollowUp(String patient_id, String chv_id, String date, String status) {
        this.patient_id = patient_id;
        this.chv_id = chv_id;
        this.date = date;
        this.status = status;
    }
}
