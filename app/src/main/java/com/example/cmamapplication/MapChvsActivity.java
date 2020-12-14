package com.example.cmamapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

public class MapChvsActivity extends AppCompatActivity {

    private DatabaseReference reference,ref1;
    String[] roledata = {"Select User Role", "CHMTAdmin", "SCHMTAdmin", "LinkFacilityAdmin", "CHO", "CHV"};

    String id,mailextra, roleextra, committee;
    EditText email,role, committeecode;
    String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_chvs);

        getIncomingIntent();

        email = (EditText) findViewById(R.id.assignedemail);
        email.setText(mailextra);

        committeecode = (EditText) findViewById(R.id.assignedcommittee);
        committeecode.setText(committee);

        role = (EditText) findViewById(R.id.assignedrole);
        role.setText(roleextra);
    }

    private void getIncomingIntent() {

        if (getIntent().hasExtra("EMAIL")) {

            mailextra = getIntent().getStringExtra("EMAIL");
            roleextra = getIntent().getStringExtra("ROLE");
            committee = getIntent().getStringExtra("COMMITTEE");

        }

    }
}