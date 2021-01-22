package com.example.cmamapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class PostDischarge extends AppCompatActivity implements View.OnClickListener {

    private CardView followup, discharge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_discharge);

        followup = (CardView) findViewById(R.id.followupcard);
        followup.setOnClickListener( this);

        discharge = (CardView) findViewById(R.id.dischargedecard);
        discharge.setOnClickListener( this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.followupcard:
                startActivity(new Intent(this, PendingFollowupActivity.class));
                break;
            case R.id.dischargedecard:
                startActivity(new Intent(this, DischargedPatientsActivity.class));
                break;
        }
    }
}