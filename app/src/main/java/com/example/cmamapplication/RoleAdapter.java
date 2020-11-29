package com.example.cmamapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RoleAdapter extends RecyclerView.Adapter {

    //Context c;
    List<User> users_list;

    public RoleAdapter(List<User> users) {
        this.users_list = users;
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
        User users = users_list.get(position);

        myholder.name.setText(users.getFirstname());
        myholder.email.setText(users.getEmail());
        myholder.role.setText(users.getRole());
    }

    @Override
    public int getItemCount() {
        return users_list.size();
    }
}
