package com.example.cmamapplication;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FollowUpHolder extends RecyclerView.ViewHolder {

    TextView patientname, date;
    LinearLayout parent;

    public FollowUpHolder(@NonNull View itemView) {
        super(itemView);

        this.patientname = (TextView) itemView.findViewById(R.id.patientnameid);
        this.date = (TextView) itemView.findViewById(R.id.date);
        this.parent = (LinearLayout) itemView.findViewById(R.id.followupparent);

    }
}
