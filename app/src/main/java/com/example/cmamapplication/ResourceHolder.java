package com.example.cmamapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ResourceHolder extends RecyclerView.ViewHolder {

    TextView resourcename, resourcetype;

    public ResourceHolder(@NonNull View itemView) {
        super(itemView);

        this.resourcename = (TextView) itemView.findViewById(R.id.resourcenameid);
        this.resourcetype = (TextView) itemView.findViewById(R.id.resourcetype);

    }
}
