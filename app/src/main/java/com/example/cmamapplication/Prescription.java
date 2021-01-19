package com.example.cmamapplication;

public class Prescription {

    String patient_id, vitamin, albendazole, measles, plump_sup, date_created;
    Long vitamin_dosage, albendazole_dosage, measles_dosage, plump_sup_daily_dosage, ratio;

    String iron, folic, teatenus_toxoid, micronutrient;
    Long iron_daily_dosage, iron_dosage, folic_daily_dosage, folic_dosage, teatenus_toxoid_dosage, micronutrient_dosage;

    public Prescription() {
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getVitamin() {
        return vitamin;
    }

    public void setVitamin(String vitamin) {
        this.vitamin = vitamin;
    }

    public String getAlbendazole() {
        return albendazole;
    }

    public void setAlbendazole(String albendazole) {
        this.albendazole = albendazole;
    }

    public String getMeasles() {
        return measles;
    }

    public void setMeasles(String measles) {
        this.measles = measles;
    }

    public String getPlump_sup() {
        return plump_sup;
    }

    public void setPlump_sup(String plump_sup) {
        this.plump_sup = plump_sup;
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

    public String getIron() {
        return iron;
    }

    public void setIron(String iron) {
        this.iron = iron;
    }

    public String getFolic() {
        return folic;
    }

    public void setFolic(String folic) {
        this.folic = folic;
    }

    public String getTeatenus_toxoid() {
        return teatenus_toxoid;
    }

    public void setTeatenus_toxoid(String teatenus_toxoid) {
        this.teatenus_toxoid = teatenus_toxoid;
    }

    public String getMicronutrient() {
        return micronutrient;
    }

    public void setMicronutrient(String micronutrient) {
        this.micronutrient = micronutrient;
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
        this.vitamin = vitamin_id;
        this.albendazole = albendazole_id;
        this.measles = measles_id;
        this.plump_sup = plump_sup_id;
        this.date_created = date_created;
        this.vitamin_dosage = vitamin_dosage;
        this.albendazole_dosage = albendazole_dosage;
        this.measles_dosage = measles_dosage;
        this.plump_sup_daily_dosage = plump_sup_daily_dosage;
        this.ratio = ratio;
        this.iron = iron_id;
        this.folic = folic_id;
        this.teatenus_toxoid = teatenus_toxoid_id;
        this.micronutrient = micronutrient_id;
        this.iron_daily_dosage = iron_daily_dosage;
        this.iron_dosage = iron_dosage;
        this.folic_daily_dosage = folic_daily_dosage;
        this.folic_dosage = folic_dosage;
        this.teatenus_toxoid_dosage = teatenus_toxoid_dosage;
        this.micronutrient_dosage = micronutrient_dosage;
    }
}
