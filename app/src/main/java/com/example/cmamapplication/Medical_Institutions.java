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

public class Medical_Institutions extends AppCompatActivity {

    private FirebaseUser user;
    private String uid;
    RecyclerView recyclerview;
    private DatabaseReference reference;
    List<Hospital> hospitalsData;
    MedicalInstitutionAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical__institutions);

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference("HealthFacilities");

        recyclerview =  (RecyclerView)findViewById(R.id.healthfacilities_recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        hospitalsData = new ArrayList<>();

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    Hospital data = ds.getValue(Hospital.class);
                    hospitalsData.add(data);
                }
                adapter = new MedicalInstitutionAdapter(Medical_Institutions.this, hospitalsData);
                recyclerview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}