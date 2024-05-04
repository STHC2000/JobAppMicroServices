package com.sthc.jobms.Job;

import com.sthc.jobms.Job.dto.JobWithCompanydto;

import java.util.List;

public interface JobService {
    List<JobWithCompanydto> findAll();
    void createJob(Job job);

    JobWithCompanydto getJobById(Long id);

    boolean deleteById(Long id);

    boolean updateJob(Long id, Job updateJob);
}
