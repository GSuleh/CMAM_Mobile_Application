package com.example.cmamapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FollowupActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseUser user;
    private DatabaseReference reference,reference1;
    private int mDate, mMonth, mYear;

    FollowUp appoitnment;
    EditText date;
    ImageView datepicker;
    String uid, patient_id;
    private Button followup;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followup);

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        date = findViewById(R.id.date);

        datepicker = findViewById(R.id.datepicker);
        datepicker.setOnClickListener(this);

        followup = (Button) findViewById(R.id.addbiodata);
        followup.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        getIncomingIntent();
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("PATIENT_ID")) {

            patient_id = getIntent().getStringExtra("PATIENT_ID");

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.followup:
                setFollowUp();
                break;

            case R.id.datepicker:
                final Calendar cal = Calendar.getInstance();
                mDate = cal.get(Calendar.DATE);
                mMonth = cal.get(Calendar.MONTH);
                mYear = cal.get(Calendar.YEAR);

                DatePickerDialog dpd = new DatePickerDialog(FollowupActivity.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        int m = month +1;
                        date.setText(dayOfMonth + "-" + m + "-" + year);

                    }

                }, mYear,mMonth,mDate);
                dpd.show();
                    break;
        }
    }

    private void setFollowUp() {
        appoitnment = new FollowUp(patient_id,uid,date.getText().toString(),"FALSE");
        reference.push().setValue(appoitnment);

        Intent intent = new Intent(FollowupActivity.this, Home.class);
        FollowupActivity.this.startActivity(intent);
    }
}