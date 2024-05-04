package com.sthc.jobms.Job.mapper;

import com.sthc.jobms.Job.Job;
import com.sthc.jobms.Job.dto.JobWithCompanydto;
import com.sthc.jobms.Job.external.Company;

public class JobMapper {
    public static JobWithCompanydto mapToJobWithCompanydto(Job job, Company company){
        JobWithCompanydto jobWithCompanydto = new JobWithCompanydto();

        jobWithCompanydto.setCompany(company);
        jobWithCompanydto.setId(job.getId());
        jobWithCompanydto.setDescription(job.getDescription());
        jobWithCompanydto.setTitle(job.getTitle());
        jobWithCompanydto.setLocation(job.getLocation());
        jobWithCompanydto.setMaxSalary(job.getMaxSalary());
        jobWithCompanydto.setMinSalary(job.getMinSalary());

        return jobWithCompanydto;
    }
}
