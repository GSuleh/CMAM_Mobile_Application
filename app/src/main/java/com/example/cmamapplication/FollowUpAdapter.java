package com.example.cmamapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class FollowUpAdapter extends RecyclerView.Adapter {

    List<FollowUp> followup_list;
    Context context;
    String name;
    private DatabaseReference reference;

    public FollowUpAdapter(Context context, List<FollowUp> followup_list) {
        this.followup_list = followup_list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.followup_row,parent,false);
        FollowUpHolder myholder=new FollowUpHolder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        FollowUpHolder myholder = (FollowUpHolder) holder;
        final FollowUp followups = followup_list.get(position);

        myholder.patientname.setText(followups.getPatient_id());
        myholder.date.setText(followups.getDate());
        myholder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MapTsfpPatientActivity.class);
                intent.putExtra("PATIENT_ID", String.valueOf(followups.getPatient_id()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return followup_list.size();
    }
}
