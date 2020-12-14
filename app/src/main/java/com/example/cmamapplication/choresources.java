package com.example.cmamapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class choresources extends AppCompatActivity implements View.OnClickListener {
    private CardView request, allocate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choresources);

        request = (CardView) findViewById(R.id.requestresourcecard);
        request.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.requestresourcecard:
                startActivity(new Intent(this, RequestResource.class));
                break;

        }
    }
}