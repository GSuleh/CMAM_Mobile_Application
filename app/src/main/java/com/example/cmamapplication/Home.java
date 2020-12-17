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

public class Home extends AppCompatActivity implements View.OnClickListener {

    private CardView screening, pat, guidelines, postdischarge, out;
    private RelativeLayout profile;

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

        final TextView name = (TextView) findViewById(R.id.username);
        final TextView phone = (TextView) findViewById(R.id.tel);
        final TextView userrole = (TextView) findViewById(R.id.role);

        screening = (CardView) findViewById(R.id.screeningcard);
        screening.setOnClickListener( this);

        pat = (CardView) findViewById(R.id.patientcard);
        pat.setOnClickListener( this);

        guidelines = (CardView) findViewById(R.id.guidelinescard);
        guidelines.setOnClickListener( this);

        postdischarge = (CardView) findViewById(R.id.postdischargecard);
        postdischarge.setOnClickListener( this);

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
                Toast.makeText(Home.this, "Something went wrong!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.screeningcard:
                startActivity(new Intent(this, AssessmentGroupActivity.class));
                break;
            case R.id.patientcard:
                startActivity(new Intent(this, Patients.class));
                break;
            case R.id.guidelinescard:
                startActivity(new Intent(this, Guidelines.class));
                break;
            case R.id.postdischargecard:
                startActivity(new Intent(this, PostDischarge.class));
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