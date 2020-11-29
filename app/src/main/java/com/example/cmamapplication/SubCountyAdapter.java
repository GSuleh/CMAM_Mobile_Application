package com.example.cmamapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SubCountyAdapter extends RecyclerView.Adapter {

    List<SubCountyClass> subcounties_list;

    public SubCountyAdapter(List<SubCountyClass> subcounties_list) {
        this.subcounties_list = subcounties_list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_row,parent,false);
        SubCountyHolder myholder=new SubCountyHolder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        SubCountyHolder myholder = (SubCountyHolder) holder;
        SubCountyClass sub_county = subcounties_list.get(position);

        myholder.name.setText(sub_county.getSub_counties());

    }

    @Override
    public int getItemCount() {
        return subcounties_list.size();
    }
}
