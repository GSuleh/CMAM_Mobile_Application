package com.example.cmamapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TreatmentActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference reference,reference1,reference2;
    Biodata biodata;

    String patient_id, malnutrition_rate, uid, oedema, trtmnt, drtn, age;

    private Treatment treat;
    private EditText treatment, duration, hiddenage;
    private Button prescription;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment);

        reference = FirebaseDatabase.getInstance().getReference("Treatment");
        reference1 = FirebaseDatabase.getInstance().getReference("Biodata");
        reference2 = FirebaseDatabase.getInstance().getReference("Patient");

        treatment = findViewById(R.id.treatment);
        duration = findViewById(R.id.duration);
        hiddenage = findViewById(R.id.agehidden);

        prescription = (Button) findViewById(R.id.assigntreatment);
        prescription.setOnClickListener(this);

        getIncomingIntent();


        reference1.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                biodata = snapshot.getValue(Biodata.class);

                if (biodata != null) {

                    patient_id = biodata.patient_id;
                    malnutrition_rate = biodata.muac;
                    oedema = biodata.oedema;
                    age = biodata.age;

                    hiddenage.setText(age);

                    if(malnutrition_rate.equals("MAM") && oedema.equals("Normal")){
                        treatment.setText("TSFP");
                        duration.setText("3 months");
                    }
                    else if(malnutrition_rate.equals("MAM") && oedema.equals("Mild")) {
                        treatment.setText("TSFP");
                        duration.setText("3 months");
                    }
                    else if(malnutrition_rate.equals("SAM") && oedema.equals("Mild")) {
                        treatment.setText("OTP");
                        duration.setText("4 months");
                    }
                    else if(malnutrition_rate.equals("SAM") && oedema.equals("Moderate")) {
                        treatment.setText("OTP");
                        duration.setText("4 months");
                    }
                    else if(malnutrition_rate.equals("SAM") && oedema.equals("Severe")) {
                        treatment.setText("SC");
                        duration.setText("4 months");
                    }
                    else if(malnutrition_rate.equals("SAM") && oedema.equals("Null")) {
                        treatment.setText("TSFP");
                        duration.setText("3 months");
                    }
                    else if(malnutrition_rate.equals("Normal") && oedema.equals("Normal"))
                    {
                        Toast.makeText(TreatmentActivity.this, "Patient is perfectly healthy.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(TreatmentActivity.this, TreatmentActivity.class);
                        intent.putExtra("BIODATA_ID", uid);
                        TreatmentActivity.this.startActivity(intent);

                    }
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
            case R.id.assigntreatment:
                addTreatment();
                break;
        }
    }

    private void addTreatment() {

        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        final String currentDateandTime = sdf.format(new Date());

        treat = new Treatment(treatment.getText().toString(), patient_id, duration.getText().toString(), currentDateandTime, currentDateandTime, "Active",Long.valueOf(0));
        reference.push().setValue(treat);


                    reference2.child(patient_id).child("treatment").setValue(treatment.getText().toString());




        if(Long.valueOf(hiddenage.getText().toString()) <=2) {
            Intent intent = new Intent(TreatmentActivity.this, PrescriptionActivity.class);
            intent.putExtra("BIODATA_ID", uid);
            intent.putExtra("PATIENT_ID", patient_id);
            intent.putExtra("TREATMENT", treatment.getText().toString());
            TreatmentActivity.this.startActivity(intent);
        }
        else{
            Intent intent = new Intent(TreatmentActivity.this, PrescriptionActivity.class);
            intent.putExtra("BIODATA_ID", uid);
            intent.putExtra("PATIENT_ID", patient_id);
            intent.putExtra("TREATMENT", treatment.getText().toString());
            TreatmentActivity.this.startActivity(intent);
        }
    }
}