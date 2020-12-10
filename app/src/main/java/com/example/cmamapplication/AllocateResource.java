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

public class AllocateResource extends AppCompatActivity {

    RecyclerView recyclerview;
    private DatabaseReference reference;
    List<ResourceClass> resourceData;
    ResourceAdapterForAllocation adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocate_resource);

        recyclerview =  (RecyclerView)findViewById(R.id.allocate_recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        resourceData = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Resource");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    ResourceClass data = ds.getValue(ResourceClass.class);
                    resourceData.add(data);
                }

                adapter = new ResourceAdapterForAllocation(AllocateResource.this,resourceData);
                recyclerview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}