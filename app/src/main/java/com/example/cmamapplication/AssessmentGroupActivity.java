package com.example.cmamapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class AssessmentGroupActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView child, plw;
    private RelativeLayout profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_group);

        child = (CardView) findViewById(R.id.childcard);
        child.setOnClickListener(this);

        plw = (CardView) findViewById(R.id.plwcard);
        plw.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.childcard:
                startActivity(new Intent(this, New_Patient.class));
                break;
            case R.id.plwcard:
                startActivity(new Intent(this, PLW_Patient.class));
                break;
        }
    }
}