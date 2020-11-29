package com.example.cmamapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CountyAdapter extends RecyclerView.Adapter {

    //Context c;
    List<Counties> counties_list;

    public CountyAdapter(List<Counties> counties_list) {
        this.counties_list = counties_list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.county_row,parent,false);
        CountyHolder myholder=new CountyHolder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        CountyHolder myholder = (CountyHolder) holder;
        Counties counties = counties_list.get(position);

        myholder.name.setText(counties.getCounty());
        myholder.code.setText(counties.getCode().toString());
    }

    @Override
    public int getItemCount() {
        return counties_list.size();
    }
}
