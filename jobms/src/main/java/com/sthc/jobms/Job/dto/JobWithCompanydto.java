package com.sthc.jobms.Job.dto;

import com.sthc.jobms.Job.Job;
import com.sthc.jobms.Job.external.Company;

public class JobWithCompanydto {
    private Job job;
    private Company company;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
