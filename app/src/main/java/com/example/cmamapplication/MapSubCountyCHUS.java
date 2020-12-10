package com.example.cmamapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MapSubCountyCHUS extends AppCompatActivity {

    private DatabaseReference reference, ref1;
    List<String> chuData;

    String id;
    String name, code,status,subcounty;
    EditText chu, chucode, chusubcounty, chustatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_sub_county_c_h_u_s);

        chu = findViewById(R.id.assignedchuname);
        chucode = findViewById(R.id.assignedchucode);
        chustatus = findViewById(R.id.chustatus);
        chusubcounty = findViewById(R.id.chusubcounty);
        chuData = new ArrayList<>();

        getIncomingIntent();

        reference = FirebaseDatabase.getInstance().getReference();
        ref1 = FirebaseDatabase.getInstance().getReference("Users");


        chu.setText(name);
        chucode.setText(code);
        chustatus.setText(status);
        chusubcounty.setText(subcounty);

    }

    private void getIncomingIntent() {

        if (getIntent().hasExtra("NAME") && getIntent().hasExtra("CODE")) {

            name = getIntent().getStringExtra("NAME");
            code = getIntent().getStringExtra("CODE");
            status = getIntent().getStringExtra("STATUS");
            subcounty = getIntent().getStringExtra("FACILITY_SUBCOUNTY");

        }
    }

}
