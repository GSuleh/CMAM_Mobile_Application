package com.example.cmamapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MedicalInstitutionAdapter  extends RecyclerView.Adapter {
    List<Hospital> hospitals_list;

    public MedicalInstitutionAdapter(List<Hospital> hospitals_list) {
        this.hospitals_list = hospitals_list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.healthfacility_row,parent,false);
        MedicalInstitutionHoder myholder=new MedicalInstitutionHoder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MedicalInstitutionHoder myholder = (MedicalInstitutionHoder) holder;
        Hospital hospitals = hospitals_list.get(position);

        myholder.name.setText(hospitals.getOfficialname());
        myholder.ward.setText(hospitals.getWard());
    }

    @Override
    public int getItemCount() {
        return hospitals_list.size();
    }
}
