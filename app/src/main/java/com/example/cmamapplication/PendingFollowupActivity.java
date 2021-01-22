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

public class PendingFollowupActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    private DatabaseReference reference;
    private FirebaseUser user;
    String uid;
    List<FollowUp> followupData;
    FollowUpAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_followup);
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        recyclerview = (RecyclerView) findViewById(R.id.pending_recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        followupData = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("FollowUp");

        reference.orderByChild("chv_id").equalTo(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    FollowUp data = ds.getValue(FollowUp.class);

                    if(data.status.equals("FALSE")) {
                        followupData.add(data);
                    }
                }

                adapter = new FollowUpAdapter(PendingFollowupActivity.this, followupData);
                recyclerview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
