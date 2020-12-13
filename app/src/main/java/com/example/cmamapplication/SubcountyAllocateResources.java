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

public class SubcountyAllocateResources extends AppCompatActivity {

    RecyclerView recyclerview;
    private FirebaseUser user;
    private DatabaseReference reference, reference1, reference2, ref, ref1, ref2;
    private String uid, subcounty;
    private Long committee;
    List<ResourceClass> resourceData;
    ResourceAdapterForSubcountyAllocation adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcounty_allocate_resources);

        recyclerview = (RecyclerView) findViewById(R.id.subcounty_allocate_recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        resourceData = new ArrayList<>();

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        reference = FirebaseDatabase.getInstance().getReference();
        reference1 = FirebaseDatabase.getInstance().getReference("Resource");
        ref = FirebaseDatabase.getInstance().getReference("Users");

        ref.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User admin = snapshot.getValue(User.class);
                if (admin != null) {

                    committee = admin.committee_id;

                    reference1.orderByChild("committee_id").equalTo(committee).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                ResourceClass data = ds.getValue(ResourceClass.class);
                                if (data != null) {

                                    resourceData.add(data);

                                }
                            }

                            adapter = new ResourceAdapterForSubcountyAllocation(SubcountyAllocateResources.this, resourceData);
                            recyclerview.setAdapter(adapter);
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
