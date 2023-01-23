package com.example.qualifandro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.qualifandro.Models.Job;
import com.example.qualifandro.databinding.ActivityJobDetailBinding;

public class JobDetailActivity extends AppCompatActivity {

    ActivityJobDetailBinding binding;
    Intent prevInt;
    Job job;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJobDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        prevInt = getIntent();
        job = (Job) prevInt.getSerializableExtra("Job");

        binding.tvJobName.setText(job.getJobName());
        binding.tvAddress.setText(job.getAddress());
        binding.tvCompany.setText(job.getCompany());
        binding.tvJobDescription.setText(job.getDescription());


    }
}