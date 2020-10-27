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

public class dashboard extends AppCompatActivity implements View.OnClickListener {

    private TextView signout;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String uid, fname, lname, tel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        uid = user.getUid();

        final TextView name = (TextView) findViewById(R.id.username);
        final TextView phone = (TextView) findViewById(R.id.tel);

        signout = (TextView) findViewById(R.id.signout);
        signout.setOnClickListener(this);

        reference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User profile = snapshot.getValue(User.class);

                if (profile != null) {
                    String fname = profile.firstname;
                    String lname = profile.lastname;
                    String tel = profile.phone;

                    name.setText(fname + " " + lname);
                    phone.setText(tel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(dashboard.this, "Something went wrong!", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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