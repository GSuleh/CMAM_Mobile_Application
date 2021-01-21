package com.example.cmamapplication;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PatientHolder extends RecyclerView.ViewHolder {

    TextView patientname, patienttype;
    LinearLayout parent;

    public PatientHolder(@NonNull View itemView) {
        super(itemView);

        this.patientname = (TextView) itemView.findViewById(R.id.patientnameid);
        this.patienttype = (TextView) itemView.findViewById(R.id.patienttype);
        this.parent = (LinearLayout) itemView.findViewById(R.id.patientparent);

    }
}
