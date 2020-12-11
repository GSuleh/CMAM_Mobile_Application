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

public class SubCountMedicalInstitutions extends AppCompatActivity {

    private FirebaseUser user;
    private String uid, subcounty, hospitalname;
    RecyclerView recyclerview;
    private DatabaseReference reference, reference1, reference2, ref, ref1;
    List<Hospital> hospitalsData;
    MedicalInstitutionAdapter adapter;
    SubCountyMedicalInstitutionAdapter scmiadapter;
    Long committee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_count_medical_institutions);


        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference("HealthFacilities");
        reference1 = FirebaseDatabase.getInstance().getReference("Users");
        reference.keepSynced(true);
        reference2 = FirebaseDatabase.getInstance().getReference("counties");
        ref = reference2.child("46");
        ref1 = ref.child("sub_counties");

        recyclerview = (RecyclerView) findViewById(R.id.healthfacilities_recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        hospitalsData = new ArrayList<>();


        reference1.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User admin = snapshot.getValue(User.class);
                if (admin != null) {

                    committee = admin.committee_id;
                    ref1.orderByChild("code").equalTo(committee).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                SubCountyClass subcountyclass = ds.getValue(SubCountyClass.class);
                                if (subcountyclass != null) {

                                    subcounty = subcountyclass.sub_county;

                                }
                            }
                                    reference.orderByChild("Sub_county").equalTo(subcounty).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            for (DataSnapshot ds : snapshot.getChildren()) {
                                                Hospital data = ds.getValue(Hospital.class);

                                                if (data != null) {
                                                    hospitalname = data.Sub_county;
                                                }

                                                hospitalsData.add(data);
                                            }
                                            scmiadapter = new SubCountyMedicalInstitutionAdapter(SubCountMedicalInstitutions.this, hospitalsData);
                                            recyclerview.setAdapter(scmiadapter);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}