package com.example.cmamapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class MapFollowUpActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference reference,ref;
    String patientid,chuname;
    EditText nameedittext, tgroup, chuedit;

    private Button next;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_follow_up);

        nameedittext = findViewById(R.id.assignedname);
        tgroup = findViewById(R.id.assignedtreatmentgroup);
        chuedit = findViewById(R.id.assignedchu);
        next = findViewById(R.id.next);
        next.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        getIncomingIntent();

        reference = FirebaseDatabase.getInstance().getReference("Patient");
        ref = FirebaseDatabase.getInstance().getReference("chus");

        reference.child(patientid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Patient data = ds.getValue(Patient.class);

                    if (data != null) {
                        nameedittext.setText(data.fullname);
                        tgroup.setText(data.treatment_group);

                        ref.orderByChild("Code").equalTo(data.chu_code).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot ds : snapshot.getChildren()) {
                                    CommunityHealthUnit data = ds.getValue(CommunityHealthUnit.class);

                                    if (data != null) {
                                        chuname = data.Name;
                                        chuedit.setText(chuname);
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
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

    }
}