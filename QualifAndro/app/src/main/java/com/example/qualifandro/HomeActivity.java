package com.example.qualifandro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.qualifandro.Adapters.JobAdapter;
import com.example.qualifandro.Models.Job;
import com.example.qualifandro.Models.User;
import com.example.qualifandro.databinding.ActivityHomePageBinding;
import com.example.qualifandro.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    User user;
    ArrayList<Job> jobList;
    Intent prevIntent;
    TextView etUsername;
    ActivityHomePageBinding binding;

    DBHelper db = DBHelper.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prevIntent = getIntent();
        jobList = db.getJobs();
        Log.d("size", "size : "+ jobList.size());

        user = db.getUser(prevIntent.getStringExtra("email"));
        etUsername = findViewById(R.id.username);
//        etUsername.setText("Hello, " + user.getUsername());

        JobAdapter jobAdapter = new JobAdapter(HomeActivity.this, jobList);

        binding.lvJobs.setAdapter(jobAdapter);
        binding.lvJobs.setClickable(true);
        binding.lvJobs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HomeActivity.this, JobDetailActivity.class);
                intent.putExtra("Job", jobList.get(i));
                startActivity(intent);
            }
        });
    }
}