package com.example.cmamapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MedicalInstitutionHoder extends RecyclerView.ViewHolder {

    TextView name,ward;

    public MedicalInstitutionHoder(@NonNull View itemView) {
        super(itemView);

        this.name = (TextView) itemView.findViewById(R.id.healthfacilitynameid);
        this.ward = (TextView) itemView.findViewById(R.id.ward);
    }
}
