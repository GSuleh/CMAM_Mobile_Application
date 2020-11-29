package com.example.cmamapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder {


    TextView name, email, role;

    public MyHolder(@NonNull View itemView) {
        super(itemView);


        this.name = (TextView) itemView.findViewById(R.id.username);
        this.email = (TextView) itemView.findViewById(R.id.emailid);
        this.role = (TextView) itemView.findViewById(R.id.roledesc);
    }
}
