package com.example.cmamapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SubCountyMedicalInstitutionAdapter extends RecyclerView.Adapter {
    Context context;
    List<Hospital> hospitals_list;

    public SubCountyMedicalInstitutionAdapter(Context context, List<Hospital> hospitals_list) {
        this.hospitals_list = hospitals_list;
        this.context = context;
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
        final Hospital hospitals = hospitals_list.get(position);

        myholder.name.setText(hospitals.getOfficialname());
        myholder.ward.setText(hospitals.getWard());
        myholder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MapSubCountyLinkFacility.class);
                intent.putExtra("NAME", String.valueOf(hospitals.getOfficialname()));
                intent.putExtra("CODE", String.valueOf(hospitals.getCode()));
                intent.putExtra("TYPE", String.valueOf(hospitals.getFacility_type_category()));
                intent.putExtra("SUBCOUNTY", String.valueOf(hospitals.getSub_county()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hospitals_list.size();
    }
}
