package com.example.cmamapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MuacTestResultActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference reference,reference1;
    Biodata biodata;

    String uid, armcirc,whzscore, malrates;
    String patientid, age;

    private EditText muac, whz, malrate;
    private Button next;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muac_test_result);

        reference = FirebaseDatabase.getInstance().getReference("Test");
        reference1 = FirebaseDatabase.getInstance().getReference("Biodata");

        muac = findViewById(R.id.armcirc);
        whz = findViewById(R.id.whs);
        malrate = findViewById(R.id.malrate);

        next = (Button) findViewById(R.id.treatment);
        next.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        getIncomingIntent();

        reference1.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                biodata = snapshot.getValue(Biodata.class);

                if (biodata != null) {

                    armcirc = biodata.arm_circumference;
                    malrates = biodata.muac;
                    whzscore = biodata.weight;

                    muac.setText(armcirc);
                    whz.setText(whzscore);
                    malrate.setText(malrates);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getIncomingIntent() {
            if (getIntent().hasExtra("BIODATA_ID")) {

                uid = getIntent().getStringExtra("BIODATA_ID");


        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.treatment:
                startTreatment();
                break;
        }
    }

    private void startTreatment() {

        Intent intent = new Intent(MuacTestResultActivity.this, TreatmentActivity.class);
        intent.putExtra("BIODATA_ID", uid);
        MuacTestResultActivity.this.startActivity(intent);

    }
}