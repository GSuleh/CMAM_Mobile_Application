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

public class CountiesActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    private DatabaseReference reference;
    List<Counties> countyData;
    CountyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counties);

        reference = FirebaseDatabase.getInstance().getReference("counties");
        reference.keepSynced(true);

        recyclerview =  (RecyclerView)findViewById(R.id.counties_recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        countyData = new ArrayList<>();

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    Counties data = ds.getValue(Counties.class);
                    countyData.add(data);
                }

                adapter = new CountyAdapter(countyData);
                recyclerview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}