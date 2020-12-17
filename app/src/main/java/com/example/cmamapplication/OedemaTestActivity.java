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

public class OedemaTestActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Spinner spinner;
    private DatabaseReference reference,reference1;
    String[] severity = {"Select severity", "Mild", "Moderate","Severe"};
    Test test;

    String uid;
    String patientid, item;

    private Button oedematest;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oedema_test);

        reference = FirebaseDatabase.getInstance().getReference("Test");
        reference1 = FirebaseDatabase.getInstance().getReference("Biodata");

        spinner = (Spinner) findViewById(R.id.severity);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter array = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,severity);
        array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(array);


        oedematest = (Button) findViewById(R.id.oedematest);
        oedematest.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        getIncomingIntent();
    }

    private void getIncomingIntent() {

        if (getIntent().hasExtra("PATIENT_ID")) {

            patientid = getIntent().getStringExtra("PATIENT_ID");

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item =  item = spinner.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.oedematest:
                addTest();
                break;
        }
    }

    private void addTest() {

        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        final String currentDateandTime = sdf.format(new Date());

        reference1.orderByChild("patient_id").equalTo(patientid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()) {
                    uid = ds.getKey();

                    test = new Test(uid,"Bilateral Oedema","Severity",spinner.getSelectedItem().toString(),spinner.getSelectedItem().toString(),currentDateandTime);
                    reference.push().setValue(test);
                    progressBar.setVisibility(View.VISIBLE);

                    Toast.makeText(OedemaTestActivity.this, uid, Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(OedemaTestActivity.this, MuacTestActivity.class);
                    intent.putExtra("PATIENT_ID", patientid);

                    reference1.child(uid).child("date_updated").setValue(currentDateandTime);
                    reference1.child(uid).child("oedema").setValue(spinner.getSelectedItem().toString());

                    OedemaTestActivity.this.startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}