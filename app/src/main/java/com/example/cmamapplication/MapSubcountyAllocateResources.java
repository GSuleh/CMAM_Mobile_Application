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

public class MapSubcountyAllocateResources extends AppCompatActivity implements View.OnClickListener {

    private Spinner spinner;
    ResourceClass resource;
    private FirebaseUser user;
    private DatabaseReference reference,reference1,ref,ref1,ref2,ref3;
    List<String> hospitalsdata;

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
        setContentView(R.layout.activity_map_subcounty_allocate_resources);

        serialno = findViewById(R.id.assignedserial);
        amount = findViewById(R.id.addinventory);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        spinner = (Spinner) findViewById(R.id.healthfacilitiesspinner);
        hospitalsdata = new ArrayList<>();

        getIncomingIntent();

        serialno.setText(code);

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference("HealthFacilities");
        reference1 = FirebaseDatabase.getInstance().getReference("Resource");
        ref = FirebaseDatabase.getInstance().getReference("Users");
        ref3 = FirebaseDatabase.getInstance().getReference("counties");
        ref1 = ref3.child("46");
        ref2 = ref1.child("sub_counties");

        ref.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User admin = snapshot.getValue(User.class);
                if (admin != null) {

                    committee = admin.committee_id;

                    ref2.orderByChild("code").equalTo(committee).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot ds:snapshot.getChildren())
                            {
                                SubCountyClass data = ds.getValue(SubCountyClass.class);
                                if (data != null) {

                                   subcounty = data.sub_county;

                                    reference.orderByChild("Sub_county").equalTo(subcounty).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            for(DataSnapshot ds:snapshot.getChildren())
                                            {
                                                String namesarray = ds.child("Officialname").getValue(String.class);
                                                hospitalsdata.add(namesarray);

                                                ArrayAdapter<String> array = new ArrayAdapter<>(MapSubcountyAllocateResources.this,android.R.layout.simple_spinner_dropdown_item,hospitalsdata);
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
        reference.orderByChild("Officialname").equalTo(spinner.getSelectedItem().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()) {
                    Hospital hospitalclass = ds.getValue(Hospital.class);
                    if (hospitalclass != null) {

                        committee_id = hospitalclass.getCode();
                        Toast.makeText(MapSubcountyAllocateResources.this, committee_id.toString(), Toast.LENGTH_LONG).show();

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
        rcvd = Long.valueOf(recieved) - amt;
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

                    resource = new ResourceClass(type,name,code, committee_id,amt,amt,amt, allocate,max, min);
                    reference1.push().setValue(resource);
                    Toast.makeText(MapSubcountyAllocateResources.this, "worked!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MapSubcountyAllocateResources.this, subcountyresources.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}