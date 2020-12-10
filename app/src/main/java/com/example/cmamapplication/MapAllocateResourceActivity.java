package com.example.cmamapplication;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MapAllocateResourceActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner spinner;
    ResourceClass resource;
    private DatabaseReference reference,reference1,ref,ref1;
    List<String> subcountyData;

    String id;
    String name;
    String code;
    Object item;
    String recieved;
    String available;
    String allocated;
    String type;
    Long amt ,avlbl, rcvd, all, committee_id;
    EditText serialno, amount;

    private Button assign;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_allocate_resource);

        serialno = findViewById(R.id.assignedserial);
        amount = findViewById(R.id.addinventory);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        spinner = (Spinner) findViewById(R.id.subcountiesspinner);
        subcountyData = new ArrayList<>();

        getIncomingIntent();

        reference = FirebaseDatabase.getInstance().getReference("counties");
        reference1 = FirebaseDatabase.getInstance().getReference("counties");

        reference.keepSynced(true);
        ref = reference.child("46");
        ref1 = ref.child("sub_counties");

        serialno.setText(code);

        ref1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    String namesarray = ds.child("sub_county").getValue(String.class);
                    subcountyData.add(namesarray);

                    ArrayAdapter<String> array = new ArrayAdapter<>(MapAllocateResourceActivity.this,android.R.layout.simple_spinner_dropdown_item,subcountyData);
                    array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    array.notifyDataSetChanged();
                    spinner.setAdapter(array);

                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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

        assign = (Button) findViewById(R.id.assignedresource);
        assign.setOnClickListener(this);

    }

    private void getIncomingIntent() {

        if (getIntent().hasExtra("NAME") && getIntent().hasExtra("CODE")) {

            name = getIntent().getStringExtra("NAME");
            code = getIntent().getStringExtra("CODE");
            recieved = getIntent().getStringExtra("INVENTORY_RECEIVED");
            available = getIntent().getStringExtra("INVENTORY_AVAILABLE");
            allocated = getIntent().getStringExtra("INVENTORY_ALLOCATED");

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.assignedresource:
                allocateResource();
                break;
        }
    }

    private void allocateResource() {

        final Long ct = Long.valueOf(code);

        amt = Long.valueOf(amount.getText().toString().trim());
        avlbl = Long.valueOf(available) - amt;
        rcvd = Long.valueOf(recieved) - amt;
        all = Long.valueOf(allocated) - amt;
        reference1.child("Resource").orderByChild("serial_number").equalTo(code).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    id = ds.getKey();

                    reference1.child(id).child("inventory_available").setValue(avlbl);
                    reference1.child(id).child("inventory_received").setValue(rcvd);
                    reference1.child(id).child("inventory_received").setValue(all);

                    reference1.child(id).child("inventory_available").setValue(avlbl);
                    reference1.child(id).child("inventory_received").setValue(rcvd);
                    Long allocate = Long.valueOf(0);
                    Long max = Long.valueOf(10000);
                    Long min = Long.valueOf(1000);

                    resource = new ResourceClass(type,name,code, committee_id,amt,amt,amt, allocate,max, min);
                    reference1.push().setValue(resource);
                    Toast.makeText(MapAllocateResourceActivity.this, "worked!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MapAllocateResourceActivity.this, resources.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}