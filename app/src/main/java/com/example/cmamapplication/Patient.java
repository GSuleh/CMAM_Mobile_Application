package com.example.cmamapplication;

public class Patient {

    public String firstname, lastname, guardian_name, guardian_email, guardian_phone, guardian_id, county, sub_county, ward, doctor_id, role;

    public Patient(){}

    public Patient(String firstname, String lastname, String guardian_name, String guardian_email, String guardian_phone,String guardian_id, String county, String sub_county, String ward, String doctor_id, String role){
        this.firstname = firstname;
        this.lastname = lastname;
        this.guardian_name = guardian_name;
        this.guardian_email= guardian_email;
        this.guardian_phone = guardian_phone;
        this.guardian_id = guardian_id;
        this.county = county;
        this.sub_county = sub_county;
        this.ward = ward;
        this.doctor_id = doctor_id;
        this.role = role;

    }
}
