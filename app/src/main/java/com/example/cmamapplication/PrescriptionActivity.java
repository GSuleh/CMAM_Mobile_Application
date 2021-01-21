package com.example.cmamapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Element;
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

import java.text.SimpleDateFormat;
import java.util.Date;

public class PrescriptionActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseUser user;
    private DatabaseReference reference,reference1,reference2,reference3;
    Biodata biodata;
    User users;
    ResourceClass resource;
    Prescription prescription;
    String bioid, uid, id, patient_id, treatment, treatment_group, age,dob, malnutrition_rate, oedema;
    Long committeeid;

    private EditText vitamin, albendazole, measles, plumpy, comid;
    private TextView vitamintxt, albendazoletxt, measlestxt, plumpytxt;
    private Button prescribe;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Biodata");
        reference1 = FirebaseDatabase.getInstance().getReference("Prescription");
        reference2 = FirebaseDatabase.getInstance().getReference("Resource");
        reference3 = FirebaseDatabase.getInstance().getReference("Users");

        vitamin = findViewById(R.id.vitamina);
        albendazole = findViewById(R.id.albendazole);
        measles = findViewById(R.id.measles);
        plumpy = findViewById(R.id.plumpy);

        vitamintxt = findViewById(R.id.vitaminatextview);
        albendazoletxt = findViewById(R.id.albendazoleview);
        measlestxt = findViewById(R.id.measlestextview);
        plumpytxt = findViewById(R.id.plumpytextview);
        comid = findViewById(R.id.committeeid);

        getIncomingIntent();

        reference.child(bioid).addListenerForSingleValueEvent(new ValueEventListener() {
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
                        albendazole.setText("0");
                        albendazoletxt.setText("Albendazole (Not Prescribed)");
                        measles.setText("1");
                        plumpy.setText("30");
                        plumpytxt.setText("Plumpy (Monthly Ration");
                    }
                    else if(Long.valueOf(age) > 1 && Long.valueOf(age) < 2  && treatment.equals("TSFP")){
                        vitamin.setText("1");
                        vitamintxt.setText("Vitamin A 200 000 IU");
                        albendazole.setText("1");
                        albendazoletxt.setText("Albendazole 200mg");
                        measles.setText("1");
                        plumpy.setText("30");
                        plumpytxt.setText("Plumpy (Monthly Ration");
                    }
                    else if(Long.valueOf(age) >= 2  && treatment.equals("TSFP")) {
                        vitamin.setText("1");
                        vitamintxt.setText("Vitamin A 200 000 IU");
                        albendazole.setText("1");
                        albendazoletxt.setText("Albendazole 400mg");
                        measles.setText("1");
                        plumpy.setText("30");
                        plumpytxt.setText("Plumpy (Monthly Ration");
                    }
                    else if(malnutrition_rate.equals("Normal")  && oedema.equals("Normal"))
                    {
                        vitamin.setText("0");
                        vitamintxt.setText("Vitamin A (Not Prescribed)");
                        albendazole.setText("0");
                        albendazoletxt.setText("Albendazole (Not Prescribed)");
                        measles.setText("1");
                        plumpy.setText("30");
                        plumpytxt.setText("Plumpy (Monthly Ration");
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

                    if (resourcename == "Measles vaccination"){
                        inventory_available -=   Long.valueOf(String.valueOf(measles.getText()));
                        reference2.child(id).child("inventory_available").setValue(inventory_available);
                    }
                    else if(resourcename == "Vitamin A"){
                        inventory_available -=   Long.valueOf(String.valueOf(vitamin.getText()));
                        reference2.child(id).child("inventory_available").setValue(inventory_available);
                    }
                    else if(resourcename == "Plumpy Sup"){
                        inventory_available -=   Long.valueOf(String.valueOf(plumpy.getText()));
                        reference2.child(id).child("inventory_available").setValue(inventory_available);
                    }
                    else if(resourcename == "Albendazole"){
                        inventory_available -=   Long.valueOf(String.valueOf(albendazole.getText()));
                        reference2.child(id).child("inventory_available").setValue(inventory_available);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        prescription = new Prescription(patient_id,vitamintxt.getText().toString(),albendazoletxt.getText().toString(),measlestxt.getText().toString(),plumpytxt.getText().toString(),currentDateandTime,Long.valueOf(vitamin.getText().toString()),Long.valueOf(albendazole.getText().toString()),Long.valueOf(measles.getText().toString()),Long.valueOf("1"),Long.valueOf(plumpy.getText().toString()),null,null,null,null,null,null,null,null,null,null,null,null);
        reference1.push().setValue(prescription);

        Intent intent = new Intent(PrescriptionActivity.this, FollowupActivity.class);
        intent.putExtra("PATIENT_ID", patient_id);
        PrescriptionActivity.this.startActivity(intent);
    }
}