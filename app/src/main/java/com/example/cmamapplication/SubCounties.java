package com.example.cmamapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SubCounties extends AppCompatActivity {

    RecyclerView recyclerview;
    private DatabaseReference reference, ref, ref1;
    List<SubCountyClass> subcountyData;
    SubCountyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_counties);

        reference = FirebaseDatabase.getInstance().getReference("counties");
        reference.keepSynced(true);
        ref = reference.child("46");
        ref1 = ref.child("sub_counties");


        recyclerview =  (RecyclerView)findViewById(R.id.subcounties_recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        subcountyData = new ArrayList<>();

        ref1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    SubCountyClass data = ds.getValue(SubCountyClass.class);
                    subcountyData.add(data);
                }

                adapter = new SubCountyAdapter(subcountyData);
                recyclerview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}