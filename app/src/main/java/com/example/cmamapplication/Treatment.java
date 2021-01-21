package com.example.cmamapplication;

public class Treatment {

    String treatment, patient_id, duration_in_weeks, date_created, date_updated, status;
    Long followups;

    public Treatment() {
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getDuration_in_weeks() {
        return duration_in_weeks;
    }

    public void setDuration_in_weeks(String duration_in_weeks) {
        this.duration_in_weeks = duration_in_weeks;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(String date_updated) {
        this.date_updated = date_updated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getFollowups() {
        return followups;
    }

    public void setFollowups(Long followups) {
        this.followups = followups;
    }

    public Treatment(String treatment, String patient_id, String duration_in_weeks, String date_created, String date_updated, String status, Long followups) {
        this.treatment = treatment;
        this.patient_id = patient_id;
        this.duration_in_weeks = duration_in_weeks;
        this.date_created = date_created;
        this.date_updated = date_updated;
        this.status = status;
        this.followups = followups;
    }
}
