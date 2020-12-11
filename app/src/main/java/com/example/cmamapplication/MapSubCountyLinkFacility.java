package com.example.cmamapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class MapSubCountyLinkFacility extends AppCompatActivity {

    private DatabaseReference reference, ref1;
    List<String> facilityData;

    String id;
    String name, code,type,subcounty;
    EditText facilitysubcounty, facility, facilitycode, facilitytype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_sub_county_link_facility);

        facility = findViewById(R.id.assignedlinkfacilityname);
        facilitycode = findViewById(R.id.assignedlinkfacilitycode);
        facilitytype = findViewById(R.id.linkfacilitytype);
        facilitysubcounty = findViewById(R.id.subcountylinkfacility);
        facilityData = new ArrayList<>();

        getIncomingIntent();

        facility.setText(name);
        facilitycode.setText(code);
        facilitytype.setText(type);
        facilitysubcounty.setText(subcounty);
    }

    private void getIncomingIntent() {

        if (getIntent().hasExtra("NAME") && getIntent().hasExtra("CODE")) {

            name = getIntent().getStringExtra("NAME");
            code = getIntent().getStringExtra("CODE");
            type = getIntent().getStringExtra("TYPE");
            subcounty = getIntent().getStringExtra("SUBCOUNTY");

        }
    }
}