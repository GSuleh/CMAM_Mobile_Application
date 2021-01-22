package com.example.cmamapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter {

    List<Patient> patient_list;
    Context context;

    public PatientAdapter(Context context, List<Patient> patient_list) {
        this.patient_list = patient_list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_row,parent,false);
        PatientHolder myholder=new PatientHolder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        PatientHolder myholder = (PatientHolder) holder;
        final Patient patients = patient_list.get(position);

        myholder.patientname.setText(patients.getFullname());
        myholder.patienttype.setText(patients.getTreatment_group());
        myholder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MapTsfpPatientActivity.class);
                intent.putExtra("NAME", String.valueOf(patients.getFullname()));
                intent.putExtra("TREATMENT_GROUP", String.valueOf(patients.getTreatment_group()));
                intent.putExtra("STATUS", String.valueOf(patients.getStatus()));
                intent.putExtra("PHASE", String.valueOf(patients.getPregnant()));
                intent.putExtra("CHU", String.valueOf(patients.getChu_code()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return patient_list.size();
    }
}
