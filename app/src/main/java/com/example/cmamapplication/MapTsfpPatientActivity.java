package com.example.cmamapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MapTsfpPatientActivity extends AppCompatActivity {

    private DatabaseReference reference,ref,ref1;
    List<String> patientData;

    String patientid,chuname;

    String name, treatment_group, status, phase, chu;
    EditText nameedittext, tgroup, chuedit;


    private Button next;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_tsfp_patient);

        nameedittext = findViewById(R.id.assignedname);
        tgroup = findViewById(R.id.assignedtreatmentgroup);
        chuedit = findViewById(R.id.assignedchu);
        next = findViewById(R.id.next);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        getIncomingIntent();

        reference = FirebaseDatabase.getInstance().getReference("Patient");
        ref = FirebaseDatabase.getInstance().getReference("chus");

        reference.orderByChild("fullname").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Patient data = ds.getValue(Patient.class);

                    patientid = ds.getKey();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ref.orderByChild("Code").equalTo(Long.valueOf(chu)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    CommunityHealthUnit data = ds.getValue(CommunityHealthUnit.class);

                    if (data != null) {
                        chuname = data.Name;
                        chuedit.setText(chuname);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        nameedittext.setText(name);
        tgroup.setText(treatment_group);


    }

    private void getIncomingIntent() {

        if (getIntent().hasExtra("NAME") && getIntent().hasExtra("TREATMENT_GROUP")) {

            name = getIntent().getStringExtra("NAME");
            treatment_group = getIntent().getStringExtra("TREATMENT_GROUP");
            status = getIntent().getStringExtra("STATUS");
            phase = getIntent().getStringExtra("PHASE");
            chu = getIntent().getStringExtra("CHU");

        }
    }
}