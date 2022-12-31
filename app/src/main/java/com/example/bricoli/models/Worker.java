package com.example.bricoli.models;

import com.google.gson.annotations.SerializedName;

import java.util.HashSet;
import java.util.Set;

public class Worker extends User
{
    @SerializedName("jobs")
    private Set<Job> jobs;

    @SerializedName("postulations")
    private Set<Postulation> postulations;

    public Set<Job> getJobs() {
        return jobs;
    }

    public Set<Postulation> getPostulations() {
        return postulations;
    }
}
