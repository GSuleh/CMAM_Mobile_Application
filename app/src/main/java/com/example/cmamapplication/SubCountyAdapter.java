package com.example.cmamapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class SubCountyAdapter extends RecyclerView.Adapter {

    List<SubCountyClass> subcounties_list;
    Context context;

    public SubCountyAdapter( Context context, List<SubCountyClass> subcounties_list) {
        this.subcounties_list = subcounties_list;
        this.context = context;
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
        final SubCountyClass sub_county = subcounties_list.get(position);

        myholder.name.setText(sub_county.getSub_county());
        myholder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MapUserSubCountyActivity.class);
                intent.putExtra("NAME", String.valueOf(sub_county.getSub_county()));
                intent.putExtra("CODE", String.valueOf(sub_county.getCode()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subcounties_list.size();
    }
}
