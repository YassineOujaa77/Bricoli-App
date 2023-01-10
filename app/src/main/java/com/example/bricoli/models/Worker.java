package com.example.bricoli.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Worker extends User implements Serializable {
    private Set<Job> jobs = new HashSet<>();
    private Set<Postulation> postulations = new HashSet<>();
    public Worker(Long userId, String cin, String password, String address, Long sommeRating, Integer numberOfRating, String photo, String fullName, String workerField, String phone) {
        setUserId(userId);
        setCin(cin);
        setPassword(password);
        setAddress(address);
        setSommeRating(sommeRating);
        setNumberOfRating(numberOfRating);
        setPhoto(photo);
        setFullName(fullName);
        setWorkerField(workerField);
        setPhone(phone);
    }

    public Worker( String cin, String password, String address, Long sommeRating, Integer numberOfRating, String photo, String fullName, String workerField, String phone) {
        setCin(cin);
        setPassword(password);
        setAddress(address);
        setSommeRating(sommeRating);
        setNumberOfRating(numberOfRating);
        setPhoto(photo);
        setFullName(fullName);
        setWorkerField(workerField);
        setPhone(phone);
    }

    public Worker() {
    }


    public Set<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }

    public Set<Postulation> getPostulations() {
        return postulations;
    }

    public void setPostulations(Set<Postulation> postulations) {
        this.postulations = postulations;
    }

    @Override
    public String toString() {
        return "Worker{" + super.toString() +
                "jobs=" + jobs +
                ", postulations=" + postulations +
                '}';
    }
}
