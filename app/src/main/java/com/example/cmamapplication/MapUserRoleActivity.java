package com.example.cmamapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class MapUserRoleActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Spinner spinner;
    private DatabaseReference reference,ref1;
    String[] roledata = {"Select User Role", "CHMTAdmin", "SCHMTAdmin", "LinkFacilityAdmin", "CHO", "CHV"};

    String id,mailextra;
    EditText email;
    String item;
    EditText subcounty, subcountycode;

    private Button assign;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_user_role);

        assign = (Button) findViewById(R.id.assignedrole);
        assign.setOnClickListener(this);

        getIncomingIntent();

        email = (EditText) findViewById(R.id.assignedemail);
        email.setText(mailextra);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        spinner = (Spinner) findViewById(R.id.rolespinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter array = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,roledata);
        array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(array);

        reference = FirebaseDatabase.getInstance().getReference();
        ref1 = FirebaseDatabase.getInstance().getReference("Users");

    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("EMAIL")) {

            mailextra = getIntent().getStringExtra("EMAIL");

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = spinner.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.assignedrole:
                assignRole();
                break;
        }
    }

    private void assignRole() {

        final String type = spinner.getSelectedItem().toString();
        reference.child("Users").orderByChild("email").equalTo(mailextra).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    id = ds.getKey();

                    ref1.child(id).child("role").setValue(type);
                    Toast.makeText(MapUserRoleActivity.this, "worked!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}