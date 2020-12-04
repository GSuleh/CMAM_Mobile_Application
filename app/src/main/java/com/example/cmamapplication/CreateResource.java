package com.example.cmamapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateResource extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Spinner spinner;
    private TextView txt;
    private EditText serialnumber , productname, amount;
    private Button create;
    private ProgressBar progressBar;
    ResourceClass resource;

    String item;
    String[] resourcetype = {"Select Resource Type", "RUTF", "MEDICATION"};
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_resource);

        reference = FirebaseDatabase.getInstance().getReference().child("Resource");
        resource = new ResourceClass();

        serialnumber = (EditText) findViewById(R.id.serialnumber);
        productname = (EditText) findViewById(R.id.product);
        amount = (EditText) findViewById(R.id.inventory);
        spinner = (Spinner) findViewById(R.id.spinnertextview);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter array = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,resourcetype);
        array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(array);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        create = (Button) findViewById(R.id.createinventory);
        create.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = spinner.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void InsertData() {
        progressBar.setVisibility(View.VISIBLE);
        if(item == "Select Resource Type" ){
            Toast.makeText(CreateResource.this, "Select resource type!", Toast.LENGTH_LONG).show();
        }else{

            final String serial = serialnumber.getText().toString().trim();
            final String product = productname.getText().toString().trim();
            final String inventoryamount = amount.getText().toString().trim();
            Long inv = Long.parseLong(inventoryamount);
            final String type = spinner.getSelectedItem().toString();

            Long all = Long.valueOf(0);
            Long max = Long.valueOf(10000);
            Long min = Long.valueOf(1000);
            Long countyid = Long.valueOf(46);

            resource = new ResourceClass(type,product,serial, countyid,inv,inv,inv, all,max, min);
            reference.push().setValue(resource);
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.createinventory:
        InsertData();
        startActivity(new Intent(this, resources.class));
        break;
    }
    }
}