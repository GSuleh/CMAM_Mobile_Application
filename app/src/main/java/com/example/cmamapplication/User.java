package com.example.cmamapplication;

import android.widget.EditText;

public class User {

    public String firstname, lastname, email, phone, role;

    public User(){}

    public User(String firstname, String lastname, String email, String phone, String role){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.role = role;

    }
}
