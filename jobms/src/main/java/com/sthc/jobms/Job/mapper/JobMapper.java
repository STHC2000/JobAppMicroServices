package com.sthc.jobms.Job.mapper;

import com.sthc.jobms.Job.Job;
import com.sthc.jobms.Job.dto.JobDto;
import com.sthc.jobms.Job.external.Company;
import com.sthc.jobms.Job.external.Review;

import java.util.List;

public class JobMapper {
    public static JobDto mapToJobWithCompanydto(Job job, Company company, List<Review> reviews){
        JobDto jobDto = new JobDto();

        jobDto.setId(job.getId());
        jobDto.setDescription(job.getDescription());
        jobDto.setTitle(job.getTitle());
        jobDto.setLocation(job.getLocation());
        jobDto.setMaxSalary(job.getMaxSalary());
        jobDto.setMinSalary(job.getMinSalary());
        jobDto.setCompany(company);
        jobDto.setReviews(reviews);

        return jobDto;
    }
}
