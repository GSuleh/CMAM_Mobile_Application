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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MapUserSubCountyActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner spinner;
    private DatabaseReference reference,ref1;
    List<String> userData;

    String id;
    String name, code, item;
    EditText subcounty, subcountycode;

    private Button assign;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_user_sub_county);

        subcounty = findViewById(R.id.assignedsubcountyname);
        subcountycode = findViewById(R.id.assignedsubcountycode);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        spinner = (Spinner) findViewById(R.id.usersspinner);
        userData = new ArrayList<>();


        getIncomingIntent();

        reference = FirebaseDatabase.getInstance().getReference();
        ref1 = FirebaseDatabase.getInstance().getReference("Users");


        subcounty.setText(name);
        subcountycode.setText(code);



        reference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    String namesarray = ds.child("email").getValue(String.class);
                    userData.add(namesarray);

                    ArrayAdapter<String> array = new ArrayAdapter<>(MapUserSubCountyActivity.this,android.R.layout.simple_spinner_dropdown_item,userData);
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



        assign = (Button) findViewById(R.id.assignedsubcounty);
        assign.setOnClickListener(this);

    }

    private void getIncomingIntent() {

        if (getIntent().hasExtra("NAME") && getIntent().hasExtra("CODE")) {

            name = getIntent().getStringExtra("NAME");
            code = getIntent().getStringExtra("CODE");

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.assignedsubcounty:
                assignAdmin();
                break;
        }
    }


    private void assignAdmin() {

        final String type = spinner.getSelectedItem().toString();

        final Long ct = Long.valueOf(code);
        reference.child("Users").orderByChild("email").equalTo(type).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren())
                {
                   id = ds.getKey();

                    ref1.child(id).child("committee_id").setValue(ct);
                    Toast.makeText(MapUserSubCountyActivity.this, "worked!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}