package com.example.cmamapplication;

public class Prescription {

    String patient_id, vitamin_id, albendazole_id, measles_id, plump_sup_id, date_created;
    Long vitamin_dosage, albendazole_dosage, measles_dosage, plump_sup_daily_dosage, ratio;

    String iron_id, folic_id, teatenus_toxoid_id, micronutrient_id;
    Long iron_daily_dosage, iron_dosage, folic_daily_dosage, folic_dosage, teatenus_toxoid_dosage, micronutrient_dosage;

    public Prescription() {
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getVitamin_id() {
        return vitamin_id;
    }

    public void setVitamin_id(String vitamin_id) {
        this.vitamin_id = vitamin_id;
    }

    public String getAlbendazole_id() {
        return albendazole_id;
    }

    public void setAlbendazole_id(String albendazole_id) {
        this.albendazole_id = albendazole_id;
    }

    public String getMeasles_id() {
        return measles_id;
    }

    public void setMeasles_id(String measles_id) {
        this.measles_id = measles_id;
    }

    public String getPlump_sup_id() {
        return plump_sup_id;
    }

    public void setPlump_sup_id(String plump_sup_id) {
        this.plump_sup_id = plump_sup_id;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public Long getVitamin_dosage() {
        return vitamin_dosage;
    }

    public void setVitamin_dosage(Long vitamin_dosage) {
        this.vitamin_dosage = vitamin_dosage;
    }

    public Long getAlbendazole_dosage() {
        return albendazole_dosage;
    }

    public void setAlbendazole_dosage(Long albendazole_dosage) {
        this.albendazole_dosage = albendazole_dosage;
    }

    public Long getMeasles_dosage() {
        return measles_dosage;
    }

    public void setMeasles_dosage(Long measles_dosage) {
        this.measles_dosage = measles_dosage;
    }

    public Long getPlump_sup_daily_dosage() {
        return plump_sup_daily_dosage;
    }

    public void setPlump_sup_daily_dosage(Long plump_sup_daily_dosage) {
        this.plump_sup_daily_dosage = plump_sup_daily_dosage;
    }

    public Long getRatio() {
        return ratio;
    }

    public void setRatio(Long ratio) {
        this.ratio = ratio;
    }

    public String getIron_id() {
        return iron_id;
    }

    public void setIron_id(String iron_id) {
        this.iron_id = iron_id;
    }

    public String getFolic_id() {
        return folic_id;
    }

    public void setFolic_id(String folic_id) {
        this.folic_id = folic_id;
    }

    public String getTeatenus_toxoid_id() {
        return teatenus_toxoid_id;
    }

    public void setTeatenus_toxoid_id(String teatenus_toxoid_id) {
        this.teatenus_toxoid_id = teatenus_toxoid_id;
    }

    public String getMicronutrient_id() {
        return micronutrient_id;
    }

    public void setMicronutrient_id(String micronutrient_id) {
        this.micronutrient_id = micronutrient_id;
    }

    public Long getIron_daily_dosage() {
        return iron_daily_dosage;
    }

    public void setIron_daily_dosage(Long iron_daily_dosage) {
        this.iron_daily_dosage = iron_daily_dosage;
    }

    public Long getIron_dosage() {
        return iron_dosage;
    }

    public void setIron_dosage(Long iron_dosage) {
        this.iron_dosage = iron_dosage;
    }

    public Long getFolic_daily_dosage() {
        return folic_daily_dosage;
    }

    public void setFolic_daily_dosage(Long folic_daily_dosage) {
        this.folic_daily_dosage = folic_daily_dosage;
    }

    public Long getFolic_dosage() {
        return folic_dosage;
    }

    public void setFolic_dosage(Long folic_dosage) {
        this.folic_dosage = folic_dosage;
    }

    public Long getTeatenus_toxoid_dosage() {
        return teatenus_toxoid_dosage;
    }

    public void setTeatenus_toxoid_dosage(Long teatenus_toxoid_dosage) {
        this.teatenus_toxoid_dosage = teatenus_toxoid_dosage;
    }

    public Long getMicronutrient_dosage() {
        return micronutrient_dosage;
    }

    public void setMicronutrient_dosage(Long micronutrient_dosage) {
        this.micronutrient_dosage = micronutrient_dosage;
    }

    public Prescription(String patient_id, String vitamin_id, String albendazole_id, String measles_id, String plump_sup_id, String date_created, Long vitamin_dosage, Long albendazole_dosage, Long measles_dosage, Long plump_sup_daily_dosage, Long ratio, String iron_id, String folic_id, String teatenus_toxoid_id, String micronutrient_id, Long iron_daily_dosage, Long iron_dosage, Long folic_daily_dosage, Long folic_dosage, Long teatenus_toxoid_dosage, Long micronutrient_dosage) {
        this.patient_id = patient_id;
        this.vitamin_id = vitamin_id;
        this.albendazole_id = albendazole_id;
        this.measles_id = measles_id;
        this.plump_sup_id = plump_sup_id;
        this.date_created = date_created;
        this.vitamin_dosage = vitamin_dosage;
        this.albendazole_dosage = albendazole_dosage;
        this.measles_dosage = measles_dosage;
        this.plump_sup_daily_dosage = plump_sup_daily_dosage;
        this.ratio = ratio;
        this.iron_id = iron_id;
        this.folic_id = folic_id;
        this.teatenus_toxoid_id = teatenus_toxoid_id;
        this.micronutrient_id = micronutrient_id;
        this.iron_daily_dosage = iron_daily_dosage;
        this.iron_dosage = iron_dosage;
        this.folic_daily_dosage = folic_daily_dosage;
        this.folic_dosage = folic_dosage;
        this.teatenus_toxoid_dosage = teatenus_toxoid_dosage;
        this.micronutrient_dosage = micronutrient_dosage;
    }
}
