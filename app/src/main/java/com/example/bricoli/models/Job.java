package com.example.bricoli.models;

public class Job {
    private Long jobId;
    private Worker worker;
    private String picture;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId=" + jobId +
                ", worker=" + worker + '\'' +
                '}';
    }
}
