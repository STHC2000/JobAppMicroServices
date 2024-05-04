package com.sthc.jobms.Job;

import com.sthc.jobms.Job.dto.JobDto;

import java.util.List;

public interface JobService {
    List<JobDto> findAll();
    void createJob(Job job);

    JobDto getJobById(Long id);

    boolean deleteById(Long id);

    boolean updateJob(Long id, Job updateJob);
}
