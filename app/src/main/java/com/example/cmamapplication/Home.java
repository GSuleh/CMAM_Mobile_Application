package com.example.cmamapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity implements View.OnClickListener {

    private ImageView new_p, pat, dash, hosp, inv, out;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String uid, fname, lname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        uid = user.getUid();

        final TextView name = (TextView) findViewById(R.id.name);


        new_p = (ImageView) findViewById(R.id.newpatient);
        new_p.setOnClickListener( this);

        pat = (ImageView) findViewById(R.id.activepatients);
        pat.setOnClickListener( this);

        dash = (ImageView) findViewById(R.id.dashboard);
        dash.setOnClickListener( this);

        hosp = (ImageView) findViewById(R.id.hospitals);
        hosp.setOnClickListener(this);

        inv = (ImageView) findViewById(R.id.inventory);
        inv.setOnClickListener(this);

        out = (ImageView) findViewById(R.id.signout);
        out.setOnClickListener(this);


        reference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User profile = snapshot.getValue(User.class);

                if (profile != null){
                    String fname = profile.firstname;
                    String lname = profile.lastname;
                    String tel = profile.phone;

                    name.setText(fname+" "+lname);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Home.this, "Something went wrong!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.newpatient:
                startActivity(new Intent(this, New_Patient.class));
                break;
            case R.id.activepatients:
                startActivity(new Intent(this, Patients.class));
                break;
            case R.id.dashboard:
                startActivity(new Intent(this, dashboard.class));
                break;
            case R.id.hospitals:
                startActivity(new Intent(this, Medical_Institutions.class));
                break;
            case R.id.inventory:
                startActivity(new Intent(this, Inventory.class));
                break;
            case R.id.signout:
                userLogout();
                break;
        }
    }

    private void userLogout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, MainActivity.class));
    }
}