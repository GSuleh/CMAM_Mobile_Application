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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PLWPrescriptionActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseUser user;
    private DatabaseReference reference,reference1,reference2,reference3,reference4;
    Biodata biodata;
    User users;
    ResourceClass resource;
    Prescription prescription;
    Patient patients;
    String bioid, uid, id, patient_id, treatment, status, pregnant, age,dob, malnutrition_rate, oedema;
    Long committeeid;

    private EditText vitamin, iron, folic, tetanus, comid, plwstatus, pregnancy, micro, vaccine;
    private TextView vitamintxt, irontxt, folictxt, tetanustxt, microtxt, vaccinetxt;
    private Button prescribe;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_l_w_prescription);

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Biodata");
        reference1 = FirebaseDatabase.getInstance().getReference("Prescription");
        reference2 = FirebaseDatabase.getInstance().getReference("Resource");
        reference3 = FirebaseDatabase.getInstance().getReference("Users");
        reference4 = FirebaseDatabase.getInstance().getReference("Patient");

        vitamin = findViewById(R.id.vitamina);
        iron = findViewById(R.id.iron);
        folic = findViewById(R.id.folic);
        tetanus = findViewById(R.id.tetanus);
        micro = findViewById(R.id.micro);
        vaccine = findViewById(R.id.vaccine);

        vitamintxt = findViewById(R.id.vitaminatextview);
        irontxt = findViewById(R.id.ironview);
        folictxt = findViewById(R.id.folicview);
        tetanustxt = findViewById(R.id.tetanusview);
        microtxt = findViewById(R.id.microtxt);
        vaccinetxt = findViewById(R.id.vaccinetxt);

        comid = findViewById(R.id.committeeid);
        plwstatus = findViewById(R.id.treatgroup);
        pregnancy = findViewById(R.id.pregnant);


        getIncomingIntent();

        reference4.child(patient_id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                patients = snapshot.getValue(Patient.class);

                if (patients != null) {

                    status = patients.pregnant;
                    pregnant = patients.status;

                    plwstatus.setText(status);
                    pregnancy.setText(pregnant);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference.child(bioid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                biodata = snapshot.getValue(Biodata.class);

                if (biodata != null) {

                    age = biodata.age;
                    dob = biodata.dob;
                    malnutrition_rate = biodata.muac;
                    oedema = biodata.oedema;

                    if(plwstatus.getText().toString().equals("Pregnant") && pregnancy.getText().toString().equals("Third Trimester")  && treatment.equals("TSFP")){

                        vitamin.setText("0");
                        vitamintxt.setText("Vitamin A (Not Prescribed)");
                        iron.setText("30");
                        irontxt.setText("60mg Iron plus");
                        folic.setText("30");
                        folictxt.setText("400ug Folic Acid");
                        tetanus.setText("1");
                        tetanustxt.setText("Tetanus Toxoid (Standard)");
                        micro.setText("0");
                        microtxt.setText("Micronutrient (Not Prescribed)");
                        vaccine.setText("1");
                        vaccinetxt.setText("Vaccine");
                    }
                    else if(plwstatus.getText().toString().equals("Lactating") && pregnancy.getText().toString().equals("More than 6 weeks post partum")  && treatment.equals("TSFP")){
                        vitamin.setText("1");
                        vitamintxt.setText("Vitamin A 200 000 IU");
                        iron.setText("30");
                        irontxt.setText("60mg Iron plus");
                        folic.setText("30");
                        folictxt.setText("400ug Folic Acid");
                        tetanus.setText("");
                        tetanustxt.setText("Tetanus Toxoid (Not Prescribed)");
                        micro.setText("1");
                        microtxt.setText("Micronutrient");
                        vaccine.setText("0");
                        vaccinetxt.setText("Vaccine (Not Prescribed)");
                    }
                    else if(plwstatus.getText().toString().equals("Lactating") && pregnancy.getText().toString().equals("Less than 6 weeks post partum")  && treatment.equals("TSFP")){
                        vitamin.setText("0");
                        vitamintxt.setText("Vitamin A (Not Prescribed)");
                        iron.setText("30");
                        irontxt.setText("60mg Iron plus");
                        folic.setText("30");
                        folictxt.setText("400ug Folic Acid");
                        tetanus.setText("0");
                        tetanustxt.setText("Tetanus Toxoid (Not Prescribed)");
                        micro.setText("1");
                        microtxt.setText("Micronutrient");
                        vaccine.setText("0");
                        vaccinetxt.setText("Vaccine (Not Prescribed)");
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference3.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users = snapshot.getValue(User.class);

                if (users != null) {

                    committeeid = users.committee_id;
                    comid.setText(committeeid.toString());
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

            bioid = getIntent().getStringExtra("BIODATA_ID");
            patient_id = getIntent().getStringExtra("PATIENT_ID");
            treatment = getIntent().getStringExtra("TREATMENT");

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.prescription:
                Prescribe();
                break;
        }
    }

    private void Prescribe() {

        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        final String currentDateandTime = sdf.format(new Date());

        reference2.orderByChild("committee_id").equalTo(comid.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()) {
                    id = ds.getKey();
                    String resourcename = ds.child("product_name").getValue(String.class);
                    Long inventory_available = Long.valueOf(ds.child("inventory_available").getValue(String.class));
                    Long inventory_allocated = Long.valueOf(ds.child("inventory_available").getValue(String.class));

                    if (resourcename == "Iron"){
                        inventory_available -=   Long.valueOf(String.valueOf(iron.getText()));
                        reference2.child(id).child("inventory_available").setValue(inventory_available);

                        inventory_allocated +=   Long.valueOf(String.valueOf(iron.getText()));
                        reference2.child(id).child("inventory_allocated").setValue(inventory_allocated);
                    }
                    else if(resourcename == "Vitamin A"){
                        inventory_available -=   Long.valueOf(String.valueOf(vitamin.getText()));
                        reference2.child(id).child("inventory_available").setValue(inventory_available);

                        inventory_allocated +=   Long.valueOf(String.valueOf(vitamin.getText()));
                        reference2.child(id).child("inventory_allocated").setValue(inventory_allocated);
                    }
                    else if(resourcename == "Plumpy Sup"){
                        inventory_available -=   30;
                        reference2.child(id).child("inventory_available").setValue(inventory_available);

                        inventory_allocated +=   Long.valueOf(30);
                        reference2.child(id).child("inventory_allocated").setValue(inventory_allocated);
                    }
                    else if(resourcename == "Folic acid"){
                        inventory_available -=   Long.valueOf(String.valueOf(folic.getText()));
                        reference2.child(id).child("inventory_available").setValue(inventory_available);

                        inventory_allocated +=   Long.valueOf(String.valueOf(folic.getText()));
                        reference2.child(id).child("inventory_allocated").setValue(inventory_allocated);
                    }
                    else if(resourcename == "Vaccine"){
                        inventory_available -=   Long.valueOf(String.valueOf(vaccine.getText()));
                        reference2.child(id).child("inventory_available").setValue(inventory_available);

                        inventory_allocated +=   Long.valueOf(String.valueOf(vaccine.getText()));
                        reference2.child(id).child("inventory_allocated").setValue(inventory_allocated);
                    }

                    else if(resourcename == "Micronutrient"){
                        inventory_available -=   Long.valueOf(String.valueOf(micro.getText()));
                        reference2.child(id).child("inventory_available").setValue(inventory_available);

                        inventory_allocated +=   Long.valueOf(String.valueOf(micro.getText()));
                        reference2.child(id).child("inventory_allocated").setValue(inventory_allocated);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        prescription = new Prescription(patient_id,vitamintxt.getText().toString(),null,null,"Plumpy Sup",currentDateandTime,Long.valueOf(vitamin.getText().toString()),null,null,Long.valueOf("1"),Long.valueOf("30"),irontxt.getText().toString(),folictxt.getText().toString(),tetanustxt.getText().toString(),microtxt.getText().toString(),vaccinetxt.getText().toString(),Long.valueOf("1"),Long.valueOf(iron.getText().toString()),Long.valueOf("1"),Long.valueOf(folic.getText().toString()),Long.valueOf(tetanus.getText().toString()),Long.valueOf(micro.getText().toString()),Long.valueOf(vaccine.getText().toString()));
        reference1.push().setValue(prescription);

        Intent intent = new Intent(PLWPrescriptionActivity.this, FollowupActivity.class);
        intent.putExtra("PATIENT_ID", patient_id);
        PLWPrescriptionActivity.this.startActivity(intent);
    }
}