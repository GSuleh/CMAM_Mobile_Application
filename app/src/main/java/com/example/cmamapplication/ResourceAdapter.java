package com.example.cmamapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ResourceAdapter extends RecyclerView.Adapter {

    List<ResourceClass> resources_list;

    public ResourceAdapter(List<ResourceClass> resources_list) {
        this.resources_list = resources_list;
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
        ResourceClass resources = resources_list.get(position);

        myholder.resourcename.setText(resources.getProduct_name());
        myholder.resourcetype.setText(resources.getType());
    }

    @Override
    public int getItemCount() {
        return resources_list.size();
    }
}
