package com.example.cmamapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class CHO extends AppCompatActivity implements View.OnClickListener {

    private CardView chvs, pat, resources, analytics, out;
    private RelativeLayout profile;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String uid, fname, lname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cho);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        uid = user.getUid();

        final TextView name = (TextView) findViewById(R.id.username);
        final TextView phone = (TextView) findViewById(R.id.tel);
        final TextView userrole = (TextView) findViewById(R.id.role);

        chvs = (CardView) findViewById(R.id.chvscard);
        chvs.setOnClickListener( this);

        pat = (CardView) findViewById(R.id.patientcard);
        pat.setOnClickListener( this);

        resources = (CardView) findViewById(R.id.resourcecard);
        resources.setOnClickListener( this);

        analytics = (CardView) findViewById(R.id.analyticscard);
        analytics.setOnClickListener(this);

        out = (CardView) findViewById(R.id.signoutcard);
        out.setOnClickListener(this);

        profile = (RelativeLayout) findViewById(R.id.personaldetails);
        profile.setOnClickListener(this);

        reference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User profile = snapshot.getValue(User.class);

                if (profile != null){
                    String fname = profile.firstname;
                    String lname = profile.lastname;
                    String tel = profile.phone;
                    String role = profile.role;

                    name.setText(fname+" "+lname);
                    phone.setText(tel);
                    userrole.setText(role);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CHO.this, "Something went wrong!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chvscard:
                startActivity(new Intent(this, ListCHVs.class));
                break;
            case R.id.patientcard:
                startActivity(new Intent(this, Patients.class));
                break;
            case R.id.resourcecard:
                startActivity(new Intent(this, choresources.class));
                break;
            case R.id.analyticscard:
                startActivity(new Intent(this, Analytics.class));
                break;
            case R.id.personaldetails:
                startActivity(new Intent(this, profile.class));
                break;
            case R.id.signoutcard:
                userLogout();
                break;
        }
    }

    private void userLogout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, MainActivity.class));
    }
}