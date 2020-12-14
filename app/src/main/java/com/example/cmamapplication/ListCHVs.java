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

public class ListCHVs extends AppCompatActivity {

    String uid;
    Long committee;
    RecyclerView recyclerview;
    private FirebaseUser user;
    private DatabaseReference reference;
    List<User> userData;
    CHVsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_c_h_vs);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        uid = user.getUid();
        reference.keepSynced(true);

        recyclerview =  (RecyclerView)findViewById(R.id.user_recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        userData = new ArrayList<>();

        reference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User admin = snapshot.getValue(User.class);
                if (admin != null) {

                    committee = admin.committee_id;

                    reference.orderByChild("committee_id").equalTo(committee).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot ds:snapshot.getChildren())
                            {
                                User data = ds.getValue(User.class);
                                userData.add(data);
                            }
                            adapter = new CHVsAdapter(ListCHVs.this,userData);
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