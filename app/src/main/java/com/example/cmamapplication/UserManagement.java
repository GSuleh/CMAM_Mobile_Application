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

public class UserManagement extends AppCompatActivity implements View.OnClickListener {

    private CardView assignrole, createuser;
    private RelativeLayout profile;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String uid, fname, lname, tel, role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        uid = user.getUid();

        assignrole = (CardView) findViewById(R.id.userrolecard);
        assignrole.setOnClickListener(this);

        createuser = (CardView) findViewById(R.id.createusercard);
        createuser.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.userrolecard:
                startActivity(new Intent(this, RoleManagement.class));
                break;
            case R.id.createusercard:
                startActivity(new Intent(this, UserCreation.class));
                break;
        }
    }

    private void userLogout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, MainActivity.class));
    }
}