package com.example.cmamapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class MapTsfpPatientActivity extends AppCompatActivity {

    private DatabaseReference reference,ref,ref1;
    List<String> patientData;

    String id;

    String name, treatment_group, status, phase;
    EditText nameedittext, tgroup;


    private Button followup;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_tsfp_patient);

        nameedittext = findViewById(R.id.assignedname);
        tgroup = findViewById(R.id.assignedtreatmentgroup);
        followup = findViewById(R.id.beginfollowup);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        patientData = new ArrayList<>();

        getIncomingIntent();

        nameedittext.setText(name);
        tgroup.setText(treatment_group);

    }

    private void getIncomingIntent() {

        if (getIntent().hasExtra("NAME") && getIntent().hasExtra("TREATMENT_GROUP")) {

            name = getIntent().getStringExtra("NAME");
            treatment_group = getIntent().getStringExtra("TREATMENT_GROUP");
            status = getIntent().getStringExtra("STATUS");
            phase = getIntent().getStringExtra("PHASE");

        }
    }
}