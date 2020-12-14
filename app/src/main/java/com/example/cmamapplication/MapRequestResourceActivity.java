package com.example.cmamapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
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

public class MapRequestResourceActivity extends AppCompatActivity implements View.OnClickListener {

    Request requestresource;
    private DatabaseReference reference,reference1;
    List<String> subcountyData;

    String name;
    String uid,id;
    String code;
    String recieved;
    String available;
    String allocated;
    String type;
    String subcounty;
    Long committee;
    EditText subcountycode, serialno, amount;

    private FirebaseUser user;
    private Button request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_request_resource);

        subcountycode = findViewById(R.id.committeeid);
        serialno = findViewById(R.id.assignedserial);
        amount = findViewById(R.id.addinventory);
        subcountyData = new ArrayList<>();

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference1 = FirebaseDatabase.getInstance().getReference("Request");
        getIncomingIntent();

        serialno.setText(code);

        reference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User profile = snapshot.getValue(User.class);

                if (profile != null) {

                    committee = profile.committee_id;

                    subcountycode.setText(committee.toString());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        request = (Button) findViewById(R.id.requestresource);
        request.setOnClickListener(this);

    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("NAME") && getIntent().hasExtra("CODE")) {

            name = getIntent().getStringExtra("NAME");
            code = getIntent().getStringExtra("CODE");
            subcounty = getIntent().getStringExtra("SUBCOUNTY_CODE");
            type = getIntent().getStringExtra("TYPE");
            recieved = getIntent().getStringExtra("INVENTORY_RECEIVED");
            available = getIntent().getStringExtra("INVENTORY_AVAILABLE");
            allocated = getIntent().getStringExtra("INVENTORY_ALLOCATED");

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.requestresource:
                requestResource ();
                break;
        }
    }

    private void requestResource() {


        Long rmg = Long.valueOf(String.valueOf(subcountycode.getText()));
        Long amt = Long.valueOf(String.valueOf(amount.getText()));

        requestresource = new Request(rmg,rmg,code,amt,null);
        reference1.push().setValue(requestresource);

        Toast.makeText(MapRequestResourceActivity.this, "worked!", Toast.LENGTH_LONG).show();
    }
}