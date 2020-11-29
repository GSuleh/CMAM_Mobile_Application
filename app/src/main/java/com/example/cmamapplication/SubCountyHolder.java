package com.example.cmamapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SubCountyHolder extends RecyclerView.ViewHolder {

    TextView name;

    public SubCountyHolder(@NonNull View itemView) {
        super(itemView);

        this.name = (TextView) itemView.findViewById(R.id.subcountynameid);
    }
}
