package com.example.cmamapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class UserCreation extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private FirebaseAuth mAuth;
    private TextView banner, registeruser;
    private EditText firstname, lastname, email, phone, password;
    private ProgressBar progressBar;
    private Spinner spinner;

    String item;
    String[] usertype = {"Select User Role", "CHMTAdmin", "SCHMTAdmin", "LinkFacilityAdmin", "CHO", "CHV"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_creation);
        mAuth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.bannerdescription);
        banner.setOnClickListener(this);

        registeruser = (Button) findViewById(R.id.registeruser);
        registeruser.setOnClickListener(this);

        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        password = (EditText) findViewById(R.id.password);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        spinner = (Spinner) findViewById(R.id.rolespinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter array = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,usertype);
        array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(array);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.banner:
                startActivity(new Intent(this, MainActivity.class));
                break;

            case R.id.registeruser:
                registerUser();
                break;
        }

    }

    private void registerUser() {
        final String emailaddress = email.getText().toString().trim();
        final String fname = firstname.getText().toString().trim();
        final String lname = lastname.getText().toString().trim();
        final String tel = phone.getText().toString().trim();
        String pass = password.getText().toString().trim();
        final String role = spinner.getSelectedItem().toString();
        if (fname.isEmpty()){
            firstname.setError("Firstname is required.");
            firstname.requestFocus();
            return;
        }

        if(item == "Select User Role" ){
            Toast.makeText(UserCreation.this, "Select user type!", Toast.LENGTH_LONG).show();
            return;
        }

        if (lname.isEmpty()){
            lastname.setError("Lastname is required.");
            lastname.requestFocus();
            return;
        }

        if (emailaddress.isEmpty()){
            email.setError("Email is required.");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailaddress).matches()){
            email.setError("Please Enter valid email.");
            email.requestFocus();
            return;
        }

        if (tel.isEmpty()){
            phone.setError("Phone number is required.");
            phone.requestFocus();
            return;
        }

        if (pass.isEmpty()){
            password.setError("Password is required.");
            password.requestFocus();
            return;
        }

        if (pass.length() < 6){
            password.setError("Password should be greater than 6 characters.");
            password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(emailaddress,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(fname,lname,emailaddress,tel,role,null);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()) {
                                        Toast.makeText(UserCreation.this, "User has been registered succesfully", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }else {
                                        Toast.makeText(UserCreation.this, "Fialed to register! Please try again.", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }
                    }
                });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = spinner.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}