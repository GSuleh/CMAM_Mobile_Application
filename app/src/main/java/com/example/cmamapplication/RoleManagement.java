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

public class RoleManagement extends AppCompatActivity {

    RecyclerView recyclerview;
    private DatabaseReference reference;
    List<User> userData;
    RoleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_management);

        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.keepSynced(true);

        recyclerview =  (RecyclerView)findViewById(R.id.user_recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        userData = new ArrayList<>();

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    User data = ds.getValue(User.class);
                    userData.add(data);
                }
                adapter = new RoleAdapter(RoleManagement.this,userData);
                recyclerview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}