package com.example.cmamapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SubcountyChuAdapter extends RecyclerView.Adapter {

    List<CommunityHealthUnit> chu_list;
    Context context;

    public SubcountyChuAdapter(Context context, List<CommunityHealthUnit> chu_list) {
        this.chu_list = chu_list;
        this.context = context;
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
        final CommunityHealthUnit chu = chu_list.get(position);

        myholder.name.setText(chu.getName());
        myholder.code.setText(chu.getFacility());

        myholder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MapSubCountyCHUS.class);
                intent.putExtra("NAME", String.valueOf(chu.getName()));
                intent.putExtra("CODE", String.valueOf(chu.getCode()));
                intent.putExtra("FACILITY_SUBCOUNTY", String.valueOf(chu.getFacility_subcounty()));
                intent.putExtra("STATUS", String.valueOf(chu.getStatus()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return chu_list.size();
    }
}
