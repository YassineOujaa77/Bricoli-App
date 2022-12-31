package com.example.bricoli.models;

import com.google.gson.annotations.SerializedName;

public class Job
{
    @SerializedName("jobId")
    private Long jobId;

    @SerializedName("worker")
    private Worker worker;

    @SerializedName("picture")
    private String picture;

    public Long getJobId() {
        return jobId;
    }

    public Worker getWorker() {
        return worker;
    }

    public String getPicture() {
        return picture;
    }
}
