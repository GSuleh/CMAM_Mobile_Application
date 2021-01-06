package com.example.cmamapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PrescriptionActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseUser user;
    private DatabaseReference reference,reference1;
    Biodata biodata;
    Prescription prescription;
    String uid, patient_id, treatment, treatment_group, age,dob, malnutrition_rate, oedema;

    private EditText vitamin, albendazole, measles, plumpy;
    private TextView vitamintxt, albendazoletxt, measlestxt, plumpytxt;
    private Button prescribe;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Biodata");
        reference1 = FirebaseDatabase.getInstance().getReference("Patient");

        vitamin = findViewById(R.id.vitamina);
        albendazole = findViewById(R.id.albendazole);
        measles = findViewById(R.id.measles);
        plumpy = findViewById(R.id.plumpy);

        vitamintxt = findViewById(R.id.vitaminatextview);
        albendazoletxt = findViewById(R.id.albendazoleview);
        measlestxt = findViewById(R.id.measlestextview);
        plumpytxt = findViewById(R.id.plumpytextview);

        getIncomingIntent();

        reference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                biodata = snapshot.getValue(Biodata.class);

                if (biodata != null) {

                    age = biodata.age;
                    dob = biodata.dob;
                    malnutrition_rate = biodata.muac;
                    oedema = biodata.oedema;

                    if(Long.valueOf(age) <= 1  && treatment.equals("TSFP")){

                        vitamin.setText("1");
                        vitamintxt.setText("Vitamin A 100 000 IU");
                        albendazole.setText("");
                        albendazoletxt.setText("Albendazole (Not Prescribed)");
                        measles.setText("1");
                        plumpy.setText("");
                    }
                    else if(Long.valueOf(age) > 1 && Long.valueOf(age) < 2  && treatment.equals("TSFP")){
                        vitamin.setText("1");
                        vitamintxt.setText("Vitamin A 200 000 IU");
                        albendazole.setText("1");
                        albendazoletxt.setText("Albendazole 200mg");
                        measles.setText("1");
                    }
                    else if(Long.valueOf(age) >= 2  && treatment.equals("TSFP")) {
                        vitamin.setText("1");
                        vitamintxt.setText("Vitamin A 200 000 IU");
                        albendazole.setText("1");
                        albendazoletxt.setText("Albendazole 400mg");
                        measles.setText("1");
                    }
                    else if(malnutrition_rate.equals("Normal")  && oedema.equals("Normal"))
                    {
                        vitamin.setText("");
                        vitamintxt.setText("Vitamin A (Not Prescribed)");
                        albendazole.setText("");
                        albendazoletxt.setText("Albendazole (Not Prescribed)");
                        measles.setText("1");
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        prescribe = (Button) findViewById(R.id.prescription);
        prescribe.setOnClickListener(this);
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("BIODATA_ID")) {

            uid = getIntent().getStringExtra("BIODATA_ID");
            patient_id = getIntent().getStringExtra("PATIENT_ID");
            treatment = getIntent().getStringExtra("TREATMENT");

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addbiodata:
                Prescribe();
                break;
        }
    }

    private void Prescribe() {
    }
}