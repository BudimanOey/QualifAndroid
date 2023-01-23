package com.example.qualifandro.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qualifandro.Models.Job;
import com.example.qualifandro.R;

import java.util.ArrayList;

public class JobAdapter extends ArrayAdapter<Job> {

    public JobAdapter(Context ctx, ArrayList<Job> jobList){
        super(ctx, R.layout.job_list, jobList);
    }

    public View getView(int i, View convertView, ViewGroup parent){

        Job job = getItem(i);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.job_list, parent, false);
        }

        ImageView ivJobImg = convertView.findViewById(R.id.job_img);
        TextView tvJobName = convertView.findViewById(R.id.job_name);
        TextView tvJobCompany = convertView.findViewById(R.id.job_company);

        ivJobImg.setImageResource(R.drawable.ic_baseline_work_outline_24);
        tvJobName.setText(job.getJobName());
        tvJobCompany.setText(job.getCompany());

        return convertView;
    }

}
