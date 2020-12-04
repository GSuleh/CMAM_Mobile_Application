package com.example.cmamapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class resources extends AppCompatActivity implements View.OnClickListener {

    private CardView create, update, allocate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        create = (CardView) findViewById(R.id.createresourcecard);
        create.setOnClickListener(this);

        update = (CardView) findViewById(R.id.updateresourcecard);
        update.setOnClickListener(this);

        allocate = (CardView) findViewById(R.id.assignresourcecard);
        allocate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.createresourcecard:
                startActivity(new Intent(this, CreateResource.class));
                break;
            case R.id.updateresourcecard:
                startActivity(new Intent(this, UpdateResource.class));
                break;
            case R.id.assignresourcecard:
                startActivity(new Intent(this, AllocateResource.class));
                break;

        }
    }
}