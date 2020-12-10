package com.example.cmamapplication;

import android.widget.EditText;

public class User {

    public String firstname, lastname, email, phone, role;

    public Long committee_id;

    public User(){}

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getCommittee_id() {
        return committee_id;
    }

    public void setCommittee_id(Long committee_id) {
        this.committee_id = committee_id;
    }

    public User(String firstname, String lastname, String email, String phone, String role, Long committee_id) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.committee_id = committee_id;
    }
}
