package com.example.cmamapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MapUpdateResourceActivity extends AppCompatActivity implements View.OnClickListener {


    private DatabaseReference reference,ref,ref1;
    List<String> subcountyData;

    String id;
    String name, code,recieved,available;
    Long amt ,avlbl, rcvd;
    EditText serialno, amount;

    private Button assign;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_update_resource);

        serialno = findViewById(R.id.assignedserial);
        amount = findViewById(R.id.addinventory);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        subcountyData = new ArrayList<>();

        getIncomingIntent();

        reference = FirebaseDatabase.getInstance().getReference();
        reference.keepSynced(true);
        ref1 = FirebaseDatabase.getInstance().getReference("Resource");

        serialno.setText(code);

        assign = (Button) findViewById(R.id.assignedresource);
        assign.setOnClickListener(this);


    }

    private void getIncomingIntent() {

        if (getIntent().hasExtra("NAME") && getIntent().hasExtra("CODE")) {

            name = getIntent().getStringExtra("NAME");
            code = getIntent().getStringExtra("CODE");
            recieved = getIntent().getStringExtra("INVENTORY_RECEIVED");
            available = getIntent().getStringExtra("INVENTORY_AVAILABLE");

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.assignedresource:
                updateResource();

                break;
        }
    }

    private void updateResource() {
        final Long ct = Long.valueOf(code);

        amt = Long.valueOf(amount.getText().toString().trim());
        avlbl = Long.valueOf(available) + amt;
        rcvd = Long.valueOf(recieved) + amt;
        reference.child("Resource").orderByChild("serial_number").equalTo(code).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    id = ds.getKey();

                    ref1.child(id).child("inventory_available").setValue(avlbl);
                    ref1.child(id).child("inventory_received").setValue(rcvd);

                    Toast.makeText(MapUpdateResourceActivity.this, "worked!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MapUpdateResourceActivity.this, resources.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}