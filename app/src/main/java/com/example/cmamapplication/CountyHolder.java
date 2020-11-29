package com.example.cmamapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CountyHolder extends RecyclerView.ViewHolder {

    TextView name, code;
    public CountyHolder(@NonNull View itemView) {
        super(itemView);

        this.name = (TextView) itemView.findViewById(R.id.countyname);
        this.code = (TextView) itemView.findViewById(R.id.countycodeid);
    }
}
