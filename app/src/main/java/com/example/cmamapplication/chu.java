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

public class chu extends AppCompatActivity {

    RecyclerView recyclerview;
    private FirebaseUser user;
    private DatabaseReference reference;
    List<CommunityHealthUnit> chuData;
    ChuAdapter adapter;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu);

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference("chus");

        recyclerview =  (RecyclerView)findViewById(R.id.chus_recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        chuData = new ArrayList<>();

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    CommunityHealthUnit data = ds.getValue(CommunityHealthUnit.class);
                    chuData.add(data);
                }

                adapter = new ChuAdapter(chu.this,chuData);
                recyclerview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}