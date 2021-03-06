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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PLW_Patient extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Spinner spinner, spinner1;
    private FirebaseUser user;
    private DatabaseReference reference, reference1;
    private Patient patient;

    private EditText fullname, national_id, guardianphone;
    private String uid, item,item1;
    private Long committee;
    private Button addPatient;
    private ProgressBar progressBar;

    String[] plw = {"Select Status", "Pregnant", "Lactating"};
    String[] pregnant = {"Select", "Third Trimester", "Less than 6 weeks post partum", "More than 6 weeks post partum","FALSE"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_l_w__patient);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference1 = FirebaseDatabase.getInstance().getReference("Patient");
        uid = user.getUid();

        fullname = findViewById(R.id.fullname);

        guardianphone = findViewById(R.id.guardian_phone);

        national_id = findViewById(R.id.nationalid);

        spinner = (Spinner) findViewById(R.id.plw);
        spinner.setOnItemSelectedListener(this);

        spinner1 = (Spinner) findViewById(R.id.pregnant);
        spinner1.setOnItemSelectedListener(this);

        ArrayAdapter array = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,plw);
        array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter array1 = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,pregnant);
        array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(array);
        spinner1.setAdapter(array1);

        addPatient = (Button) findViewById(R.id.newpatient);
        addPatient.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newpatient:
                registerPatient();
                break;
        }
    }

    private void registerPatient() {

        reference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User profile = snapshot.getValue(User.class);
                committee = profile.committee_id;

                final String tel = guardianphone.getText().toString().trim();
                final Long id = Long.valueOf(String.valueOf(national_id.getText()));
                patient = new Patient(fullname.getText().toString(), null, tel, "Pregnant and lactating women", id, committee, uid,spinner.getSelectedItem().toString(),spinner1.getSelectedItem().toString());
                reference1.push().setValue(patient);
                progressBar.setVisibility(View.VISIBLE);

                Intent intent = new Intent(PLW_Patient.this, BiodataActivity.class);
                intent.putExtra("FULLNAME", fullname.getText().toString());

                PLW_Patient.this.startActivity(intent);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = spinner.getSelectedItem().toString();
        item1 = spinner1.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
