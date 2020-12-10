package com.example.cmamapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RoleAdapter extends RecyclerView.Adapter {

    Context context;
    List<User> users_list;

    public RoleAdapter( Context context, List<User> users) {
        this.users_list = users;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        MyHolder myholder=new MyHolder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MyHolder myholder = (MyHolder)holder;
        final User users = users_list.get(position);

        myholder.name.setText(users.getFirstname());
        myholder.email.setText(users.getEmail());
        myholder.role.setText(users.getRole());
        myholder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MapUserRoleActivity.class);
                intent.putExtra("EMAIL", String.valueOf(users.getEmail()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users_list.size();
    }
}
