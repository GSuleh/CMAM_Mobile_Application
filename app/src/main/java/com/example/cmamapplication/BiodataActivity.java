package com.example.cmamapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BiodataActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private FirebaseUser user;
    private DatabaseReference reference,reference1;
    private int mDate, mMonth, mYear;
    String[] gender = {"Select Gender", "Male", "Female"};
    Biodata biodata;

    String uid;
    String name;
    String item;
    int miaka;
    EditText age, height, weight, hidden;
    ImageView datepicker;

    private Button addbiodata;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodata);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Biodata");
        reference1 = FirebaseDatabase.getInstance().getReference("Patient");

        hidden = findViewById(R.id.hidden);

        age = findViewById(R.id.age);

        height = findViewById(R.id.height);

        weight = findViewById(R.id.weight);

        datepicker = findViewById(R.id.datepicker);
        datepicker.setOnClickListener(this);

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

            case R.id.datepicker:
                final Calendar cal = Calendar.getInstance();
                mDate = cal.get(Calendar.DATE);
                mMonth = cal.get(Calendar.MONTH);
                mYear = cal.get(Calendar.YEAR);

                DatePickerDialog dpd = new DatePickerDialog(BiodataActivity.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        int m = month +1;
                        age.setText(dayOfMonth + "-" + m + "-" + year);

                        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
                        final String currentDateandTime = sdf.format(new Date());

                        Date mydate = null;
                        try {
                            mydate = sdf.parse(currentDateandTime);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        final Calendar today = Calendar.getInstance();
                        today.setTime(mydate);

                        miaka = today.get(Calendar.YEAR) - year;
                        hidden.setText(miaka+"");
                    }

                }, mYear,mMonth,mDate);
                dpd.show();


                break;
        }
    }

    private void addBiodata() {


        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        final String currentDateandTime = sdf.format(new Date());

        final Long gg = Long.valueOf(hidden.getText().toString());


        reference1.orderByChild("fullname").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    uid = ds.getKey();

                    Toast.makeText(BiodataActivity.this, uid, Toast.LENGTH_LONG).show();

        biodata = new Biodata(age.getText().toString(),String.valueOf(gg),height.getText().toString(),weight.getText().toString(), spinner.getSelectedItem().toString(),uid,currentDateandTime);
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