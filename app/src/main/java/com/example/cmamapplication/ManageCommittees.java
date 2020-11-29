package com.example.cmamapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ManageCommittees extends AppCompatActivity implements View.OnClickListener {

    private CardView counties, subcounties, linkfacililies, chus;
    private String uid;

    private FirebaseUser user;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_committees);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        uid = user.getUid();

        counties = (CardView) findViewById(R.id.listcountiescard);
        counties.setOnClickListener(this);

        subcounties = (CardView) findViewById(R.id.subcountiescard);
        subcounties.setOnClickListener(this);

        linkfacililies = (CardView) findViewById(R.id.healthfacilitiescard);
        linkfacililies.setOnClickListener(this);

        chus = (CardView) findViewById(R.id.chuscard);
        chus.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.listcountiescard:
                startActivity(new Intent(this, CountiesActivity.class));
                break;
            case R.id.subcountiescard:
                startActivity(new Intent(this, SubCounties.class));
                break;
            case R.id.healthfacilitiescard:
                startActivity(new Intent(this, Medical_Institutions.class));
                break;
            case R.id.chuscard:
                startActivity(new Intent(this, chu.class));
                break;
        }
    }
}