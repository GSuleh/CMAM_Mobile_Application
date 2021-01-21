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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MuacTestActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference reference,reference1;
    Test test;

    String uid;
    String patientid, age;

    private EditText armcirc;
    private Button muactest;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muac_test);

        reference = FirebaseDatabase.getInstance().getReference("Test");
        reference1 = FirebaseDatabase.getInstance().getReference("Biodata");

        armcirc = findViewById(R.id.armcirc);

        muactest = (Button) findViewById(R.id.muactest);
        muactest.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        getIncomingIntent();


    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("PATIENT_ID")) {

            patientid = getIntent().getStringExtra("PATIENT_ID");

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.muactest:
                addTest();
                break;
        }
    }

    private void addTest() {

        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        final String currentDateandTime = sdf.format(new Date());
        final Long arm = Long.valueOf((long) Double.parseDouble(armcirc.getText().toString()));
        reference1.orderByChild("patient_id").equalTo(patientid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()) {
                    uid = ds.getKey();
                    age = ds.child("age").getValue(String.class);
                    Long patientage = Long.valueOf(age);

                    reference1.child(uid).child("date_updated").setValue(currentDateandTime);
                    reference1.child(uid).child("arm_circumference").setValue(armcirc.getText().toString());

                    if(arm<11.5) {

                        test = new Test(uid, "MUAC", "Middle Upper Arm Circumference", armcirc.getText().toString(), "SAM", currentDateandTime);
                        reference.push().setValue(test);
                        progressBar.setVisibility(View.VISIBLE);

                        Toast.makeText(MuacTestActivity.this, uid, Toast.LENGTH_LONG).show();
                        reference1.child(uid).child("muac").setValue("SAM");

                    }else if(arm<=12.5 && arm>=11.5){

                        test = new Test(uid,"muac","Middle Upper Arm Circumference",armcirc.getText().toString(),"MAM",currentDateandTime);
                        reference.push().setValue(test);
                        progressBar.setVisibility(View.VISIBLE);

                        Toast.makeText(MuacTestActivity.this, uid, Toast.LENGTH_LONG).show();
                        reference1.child(uid).child("muac").setValue("MAM");

                    } else if(arm<=21.5 && patientage >5){

                        test = new Test(uid,"muac","Middle Upper Arm Circumference",armcirc.getText().toString(),"SAM",currentDateandTime);
                        reference.push().setValue(test);
                        reference1.child(uid).child("oedema").setValue("Null");
                        progressBar.setVisibility(View.VISIBLE);

                        Toast.makeText(MuacTestActivity.this, uid, Toast.LENGTH_LONG).show();
                        reference1.child(uid).child("muac").setValue("SAM");

                    } else{

                        test = new Test(uid,"muac","Middle Upper Arm Circumference",armcirc.getText().toString(),"Normal",currentDateandTime);
                        reference.push().setValue(test);
                        progressBar.setVisibility(View.VISIBLE);

                        Toast.makeText(MuacTestActivity.this, uid, Toast.LENGTH_LONG).show();
                        reference1.child(uid).child("MUAC").setValue("Normal");

                    }

                    Intent intent = new Intent(MuacTestActivity.this, MuacTestResultActivity.class);
                    intent.putExtra("BIODATA_ID", uid);
                    MuacTestActivity.this.startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}