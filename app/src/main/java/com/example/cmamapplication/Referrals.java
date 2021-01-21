package com.example.cmamapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Referrals extends AppCompatActivity {

    RecyclerView recyclerview;
    private DatabaseReference reference,reference1,reference2,reference3;
    private FirebaseUser user;
    String uid, subcounty,chvid;
    List<Patient> patientData;
    PatientAdapter adapter;
    Long committee,chucode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referrals);

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        recyclerview = (RecyclerView) findViewById(R.id.tsfp_recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        patientData = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Patient");
        reference1 = FirebaseDatabase.getInstance().getReference("Users");
        reference2 = FirebaseDatabase.getInstance().getReference("HealthFacility");
        reference3 = FirebaseDatabase.getInstance().getReference("chus");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Patient data = ds.getValue(Patient.class);

                    if(data.treatment.equals("OTP") ) {
                        patientData.add(data);
                    }else if(data.treatment.equals("SC") ) {
                        patientData.add(data);
                    }
                }

                adapter = new PatientAdapter(Referrals.this, patientData);
                recyclerview.setAdapter(adapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}

