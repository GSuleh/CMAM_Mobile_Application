package com.example.cmamapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChuAdapter extends RecyclerView.Adapter {

    List<CommunityHealthUnit> chu_list;

    public ChuAdapter(List<CommunityHealthUnit> chu_list) {
        this.chu_list = chu_list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chu_row,parent,false);
        ChuHolder myholder=new ChuHolder(view);
        return myholder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ChuHolder myholder = (ChuHolder) holder;
        CommunityHealthUnit chu = chu_list.get(position);

        myholder.name.setText(chu.getName());
        myholder.code.setText(chu.getFacility());

    }

    @Override
    public int getItemCount() {
        return chu_list.size();
    }
}
