package com.example.cmamapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChuHolder extends RecyclerView.ViewHolder {

    TextView name,code;

    public ChuHolder(@NonNull View itemView) {
        super(itemView);

        this.name = (TextView) itemView.findViewById(R.id.chunameid);
        this.code = (TextView) itemView.findViewById(R.id.code);
    }
}
