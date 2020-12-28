package com.example.cmamapplication;

public class Biodata {

    public String age, height, weight, gender, oedema, arm_circumference, MUAC, temperature, respiratory_rate, tidal_volume, minute_volume, vital_capacity, date_created, date_updated;
    public String patient_id;

    public Biodata() {
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOedema() {
        return oedema;
    }

    public void setOedema(String oedema) {
        this.oedema = oedema;
    }

    public String getArm_circumference() {
        return arm_circumference;
    }

    public void setArm_circumference(String arm_circumference) {
        this.arm_circumference = arm_circumference;
    }

    public String getMUAC() {
        return MUAC;
    }

    public void setMUAC(String MUAC) {
        this.MUAC = MUAC;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getRespiratory_rate() {
        return respiratory_rate;
    }

    public void setRespiratory_rate(String respiratory_rate) {
        this.respiratory_rate = respiratory_rate;
    }

    public String getTidal_volume() {
        return tidal_volume;
    }

    public void setTidal_volume(String tidal_volume) {
        this.tidal_volume = tidal_volume;
    }

    public String getMinute_volume() {
        return minute_volume;
    }

    public void setMinute_volume(String minute_volume) {
        this.minute_volume = minute_volume;
    }

    public String getVital_capacity() {
        return vital_capacity;
    }

    public void setVital_capacity(String vital_capacity) {
        this.vital_capacity = vital_capacity;
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

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public Biodata(String age, String height, String weight, String gender,  String patient_id, String date_created) {
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.patient_id = patient_id;
        this.date_created = date_created;
    }
}
