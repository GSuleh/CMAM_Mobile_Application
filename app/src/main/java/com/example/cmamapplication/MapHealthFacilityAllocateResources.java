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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MapHealthFacilityAllocateResources extends AppCompatActivity implements View.OnClickListener {

    private Spinner spinner;
    ResourceClass resource;
    private FirebaseUser user;
    private DatabaseReference reference,reference1,ref,ref1,ref2,ref3;
    List<String> chusData;

    String id,uid;
    String name, subcounty;
    String code, recieved, available,allocated, type, item;
    Long amt ,avlbl, rcvd, all, committee, committee_id;
    EditText serialno, amount;

    private Button assign;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_health_facility_allocate_resources);

        serialno = findViewById(R.id.assignedserial);
        amount = findViewById(R.id.addinventory);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        spinner = (Spinner) findViewById(R.id.chusspinner);
        chusData = new ArrayList<>();

        getIncomingIntent();

        serialno.setText(code);

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference("HealthFacilities");
        reference1 = FirebaseDatabase.getInstance().getReference("Resource");
        ref = FirebaseDatabase.getInstance().getReference("Users");
        ref1 = FirebaseDatabase.getInstance().getReference("chus");

        ref.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User admin = snapshot.getValue(User.class);
                if (admin != null) {

                    committee = admin.committee_id;

                    reference.orderByChild("Code").equalTo(committee).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot ds:snapshot.getChildren())
                            {
                                    Hospital data = ds.getValue(Hospital.class);
                                if (data != null) {

                                    subcounty = data.Officialname;

                                    ref1.orderByChild("Facility").equalTo(subcounty).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            for(DataSnapshot ds:snapshot.getChildren())
                                            {
                                                String namesarray = ds.child("Name").getValue(String.class);
                                                chusData.add(namesarray);

                                                ArrayAdapter<String> array = new ArrayAdapter<>(MapHealthFacilityAllocateResources.this,android.R.layout.simple_spinner_dropdown_item,chusData);
                                                array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                array.notifyDataSetChanged();
                                                spinner.setAdapter(array);

                                                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        getCommitteeID();
                                                        item = spinner.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

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

        assign = (Button) findViewById(R.id.assignresource);
        assign.setOnClickListener(this);
    }

    private void getCommitteeID() {
        ref1.orderByChild("Name").equalTo(spinner.getSelectedItem().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()) {
                    CommunityHealthUnit chuclass = ds.getValue(CommunityHealthUnit.class);
                    if (chuclass != null) {

                        committee_id = chuclass.getCode();
                        Toast.makeText(MapHealthFacilityAllocateResources.this, committee_id.toString(), Toast.LENGTH_LONG).show();

                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getIncomingIntent() {

        if (getIntent().hasExtra("NAME") && getIntent().hasExtra("CODE")) {

            name = getIntent().getStringExtra("NAME");
            code = getIntent().getStringExtra("CODE");
            type = getIntent().getStringExtra("TYPE");
            recieved = getIntent().getStringExtra("INVENTORY_RECEIVED");
            available = getIntent().getStringExtra("INVENTORY_AVAILABLE");
            allocated = getIntent().getStringExtra("INVENTORY_ALLOCATED");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.assignresource:
                allocateResource();
                break;
        }
    }

    private void allocateResource() {

        final Long ct = Long.valueOf(code);

        amt = Long.valueOf(amount.getText().toString().trim());
        avlbl = Long.valueOf(available) - amt;
        rcvd = Long.valueOf(recieved);
        all = Long.valueOf(allocated) + amt;
        final Long allocate = Long.valueOf(0);
        final Long max = Long.valueOf(10000);
        final Long min = Long.valueOf(1000);




        reference1.orderByChild("serial_number").equalTo(code).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    id = ds.getKey();

                    reference1.child(id).child("inventory_available").setValue(avlbl);
                    reference1.child(id).child("inventory_received").setValue(rcvd);
                    reference1.child(id).child("inventory_allocated").setValue(all);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        resource = new ResourceClass(type,name,code, committee_id,amt,amt,amt, allocate,max, min);
        reference1.push().setValue(resource);
        Toast.makeText(MapHealthFacilityAllocateResources.this, "worked!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(MapHealthFacilityAllocateResources.this, linkfacilityresources.class));
    }
}