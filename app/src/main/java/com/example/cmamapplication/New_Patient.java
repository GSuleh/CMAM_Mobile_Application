package com.example.cmamapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class New_Patient extends AppCompatActivity implements View.OnClickListener {

    private FirebaseUser user;
    private DatabaseReference reference,reference1;
    private Patient patient;

    private EditText fullname, guardianname, national_id, guardianphone;
    private String uid;
    private Long committee;
    private Button addPatient;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__patient);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference1 = FirebaseDatabase.getInstance().getReference("Patient");
        uid = user.getUid();

        fullname = findViewById(R.id.fullname);

        guardianname = findViewById(R.id.guardian_name);

        guardianphone = findViewById(R.id.guardian_phone);

        national_id = findViewById(R.id.nationalid);

        addPatient = (Button) findViewById(R.id.newpatient);
        addPatient.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newpatient:
                registerPatient();
                break;
        }
    }

    private void registerPatient() {

        reference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User profile = snapshot.getValue(User.class);
                committee = profile.committee_id;

                    final String tel = guardianphone.getText().toString().trim();
                    final Long id = Long.valueOf(String.valueOf(national_id.getText()));
                    patient = new Patient(fullname.getText().toString(),guardianname.getText().toString(),tel,null,id,committee,uid);
                    reference1.push().setValue(patient);
                    progressBar.setVisibility(View.VISIBLE);

                    Intent intent = new Intent(New_Patient.this, BiodataActivity.class);
                    intent.putExtra("FULLNAME", fullname.getText().toString());

                    New_Patient.this.startActivity(intent);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}