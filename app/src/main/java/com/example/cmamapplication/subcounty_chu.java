package com.example.cmamapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class subcounty_chu extends AppCompatActivity {

    RecyclerView recyclerview;
    private FirebaseUser user;
    private DatabaseReference reference, reference1, reference2,ref,ref1;
    List<CommunityHealthUnit> chuData;
    SubcountyChuAdapter adapter;
    private String id, uid, subcounty;
    Long committee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcounty_chu);

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference("chus");
        reference1 = FirebaseDatabase.getInstance().getReference("Users");
        reference2 = FirebaseDatabase.getInstance().getReference("counties");
        ref = reference2.child("46");
        ref1 = ref.child("sub_counties");

        recyclerview =  (RecyclerView)findViewById(R.id.chus_recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        chuData = new ArrayList<>();

        reference1.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User admin = snapshot.getValue(User.class);
                    if (admin != null) {

                        committee = admin.committee_id;

                        ref1.orderByChild("code").equalTo(committee).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot ds:snapshot.getChildren())
                                {
                                    SubCountyClass subcountyclass = ds.getValue(SubCountyClass.class);
                                    if (subcountyclass != null) {

                                        subcounty = subcountyclass.sub_county;

                                        reference.orderByChild("Facility_subcounty").equalTo(subcounty).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                for(DataSnapshot ds:snapshot.getChildren())
                                                {
                                                    CommunityHealthUnit data = ds.getValue(CommunityHealthUnit.class);

                                                    if (data != null) {
                                                        chuData.add(data);

                                                    }
                                                }

                                                adapter = new SubcountyChuAdapter(subcounty_chu.this,chuData);
                                                recyclerview.setAdapter(adapter);
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });


                                    }
                                }
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
