package com.example.cmamapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ResourceAdapterForRequest extends RecyclerView.Adapter {

    List<ResourceClass> resources_list;
    Context context;

    public ResourceAdapterForRequest(Context context, List<ResourceClass> resources_list) {
        this.resources_list = resources_list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.resource_row,parent,false);
        ResourceHolder myholder=new ResourceHolder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ResourceHolder myholder = (ResourceHolder) holder;
        final ResourceClass resources = resources_list.get(position);

        myholder.resourcename.setText(resources.getProduct_name());
        myholder.resourcetype.setText(resources.getType());
        myholder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MapRequestResourceActivity.class);
                intent.putExtra("NAME", String.valueOf(resources.getProduct_name()));
                intent.putExtra("CODE", String.valueOf(resources.getSerial_number()));
                intent.putExtra("TYPE", String.valueOf(resources.getType()));
                intent.putExtra("INVENTORY_ALLOCATED", String.valueOf(resources.getInventory_allocated()));
                intent.putExtra("INVENTORY_AVAILABLE", String.valueOf(resources.getInventory_available()));
                intent.putExtra("INVENTORY_RECEIVED", String.valueOf(resources.getInventory_received()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resources_list.size();
    }
}
