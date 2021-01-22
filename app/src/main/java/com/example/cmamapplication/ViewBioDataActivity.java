package com.example.cmamapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ViewBioDataActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference reference;
    ProgressBar progressBar;
    String patientid;
    EditText dob, height, weight, gender;
    Button done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bio_data);

        dob = findViewById(R.id.age);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        gender = findViewById(R.id.gender);
        done = findViewById(R.id.done);
        done.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        getIncomingIntent();

        reference = FirebaseDatabase.getInstance().getReference("Biodata");

        reference.orderByChild("patient_id").equalTo(patientid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Biodata data = ds.getValue(Biodata.class);

                    patientid = ds.getKey();

                    dob.setText(data.dob);
                    height.setText(data.height);
                    weight.setText(data.weight);
                    gender.setText(data.gender);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getIncomingIntent() {

        if (getIntent().hasExtra("PATIENT_ID")) {

            patientid = getIntent().getStringExtra("PATIENT_ID");

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.done:
                Done();
                break;
        }
    }

    private void Done() {
        Intent intent = new Intent(ViewBioDataActivity.this, Home.class);
        ViewBioDataActivity.this.startActivity(intent);
    }
}