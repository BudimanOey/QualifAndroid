package com.example.qualifandro.Models;

import java.io.Serializable;

public class Job implements Serializable {
    private int id;
    private String jobName;
    private String company;
    private String address;
    private String description;

    public Job(int id, String jobName, String company, String address, String description){
        this.id = id;
        this.jobName = jobName;
        this.company = company;
        this.address = address;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
