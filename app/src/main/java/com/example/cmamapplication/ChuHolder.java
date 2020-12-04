package com.example.cmamapplication;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChuHolder extends RecyclerView.ViewHolder {

    TextView name,code;

    LinearLayout parent;
    public ChuHolder(@NonNull View itemView) {
        super(itemView);

        this.name = (TextView) itemView.findViewById(R.id.chunameid);
        this.code = (TextView) itemView.findViewById(R.id.code);
        this.parent = (LinearLayout) itemView.findViewById(R.id.chuparent);
    }
}
