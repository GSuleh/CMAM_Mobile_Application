package com.example.cmamapplication;

public class Prescription {

    String patient_id, resource_id, dosage, instructions, date_created;
    Long amount_prescribed;

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getResource_id() {
        return resource_id;
    }

    public void setResource_id(String resource_id) {
        this.resource_id = resource_id;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public Long getAmount_prescribed() {
        return amount_prescribed;
    }

    public void setAmount_prescribed(Long amount_prescribed) {
        this.amount_prescribed = amount_prescribed;
    }


    public Prescription(String patient_id, String resource_id, String dosage, String instructions, String date_created, Long amount_prescribed) {
        this.patient_id = patient_id;
        this.resource_id = resource_id;
        this.dosage = dosage;
        this.instructions = instructions;
        this.date_created = date_created;
        this.amount_prescribed = amount_prescribed;
    }
}
