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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BiodataActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private FirebaseUser user;
    private DatabaseReference reference,reference1;
    String[] gender = {"Select Gender", "Male", "Female"};
    Biodata biodata;

    String uid;
    String name, item;
    EditText age, height, weight;

    private Button addbiodata;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodata);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Biodata");
        reference1 = FirebaseDatabase.getInstance().getReference("Patient");

        age = findViewById(R.id.age);

        height = findViewById(R.id.height);

        weight = findViewById(R.id.weight);


        spinner = (Spinner) findViewById(R.id.gender);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter array = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,gender);
        array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(array);


        addbiodata = (Button) findViewById(R.id.addbiodata);
        addbiodata.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        getIncomingIntent();


    }

    private void getIncomingIntent() {

        if (getIntent().hasExtra("FULLNAME")) {

            name = getIntent().getStringExtra("FULLNAME");

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addbiodata:
                addBiodata();
                break;
        }
    }

    private void addBiodata() {


        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        final String currentDateandTime = sdf.format(new Date());

        final Long gg = Long.valueOf(age.getText().toString());


        reference1.orderByChild("fullname").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    uid = ds.getKey();

                    Toast.makeText(BiodataActivity.this, uid, Toast.LENGTH_LONG).show();

        biodata = new Biodata(age.getText().toString(),height.getText().toString(),weight.getText().toString(), spinner.getSelectedItem().toString(),uid,currentDateandTime);
        reference.push().setValue(biodata);
        progressBar.setVisibility(View.VISIBLE);

        if(gg <= 5){
        Intent intent = new Intent(BiodataActivity.this, OedemaTestActivity.class);
        intent.putExtra("PATIENT_ID", uid);

        reference1.child(uid).child("treatment_group").setValue("6-59 Months");

        BiodataActivity.this.startActivity(intent);
        }else{
            Intent intent = new Intent(BiodataActivity.this, MuacTestActivity.class);
            intent.putExtra("PATIENT_ID", uid);

            reference1.child(uid).child("treatment_group").setValue("Pregnant and lactating women");

            BiodataActivity.this.startActivity(intent);
        }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item =  item = spinner.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}