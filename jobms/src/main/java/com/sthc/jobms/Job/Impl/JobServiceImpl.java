package com.sthc.jobms.Job.Impl;

import com.sthc.jobms.Job.Job;
import com.sthc.jobms.Job.JobRepository;
import com.sthc.jobms.Job.JobService;
import com.sthc.jobms.Job.dto.JobDto;
import com.sthc.jobms.Job.external.Company;
import com.sthc.jobms.Job.external.Review;
import com.sthc.jobms.Job.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    //private List<Job> jobs = new ArrayList<>();
    private JobRepository jobRepository;

    @Autowired
    RestTemplate restTemplate;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobDto> findAll() {
        List<Job> jobs = jobRepository.findAll();
//        List<JobDto> jobDtos = new ArrayList<>();

       return jobs.stream()
               .map(this::convertToDto)
               .collect(Collectors.toList());
    }

    private JobDto convertToDto(Job job){
//        RestTemplate restTemplate = new RestTemplate();
//        JobWithCompanydto jobWithCompanydto = new JobWithCompanydto();
//        jobWithCompanydto.setJob(job);
        Company company = restTemplate.getForObject(
                "http://COMPANY-SERVICE:8081/companies/" + job.getCompanyId(),
                Company.class);

        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange(
                "http://REVIEW-SERVICE:8083/reviews?companyId=" + job.getCompanyId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Review>>() {
                });

        List<Review> reviews = reviewResponse.getBody();

        JobDto jobDto = JobMapper.mapToJobWithCompanydto(job,company,reviews);
//        jobDto.setCompany(company);
        return jobDto;

    }

    @Override
    public void createJob(Job job) {

//        jobs.add(job);
        jobRepository.save(job);

    }

    @Override
    public JobDto getJobById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        return convertToDto(job);
       /* for (Job job:
             jobs) {
            if (job.getId().equals(id)){
                return job;
            }
        }
        return null;*/
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return  false;
        }

      /*  Iterator<Job> iterator = jobs.iterator();
        while (iterator.hasNext()){
            Job job = iterator.next();
            if (job.getId().equals(id)){
                iterator.remove();
                return true;
            }

        }
        return false;*/
    }

    @Override
    public boolean updateJob(Long id, Job updateJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()){
            Job job = jobOptional.get();
            job.setTitle(updateJob.getTitle());
            job.setDescription(updateJob.getDescription());
            job.setMinSalary(updateJob.getMinSalary());
            job.setMaxSalary(updateJob.getMaxSalary());
            job.setLocation(updateJob.getLocation());
            job.setCompanyId(updateJob.getCompanyId());
            jobRepository.save(job);
            return true;
        }
        return false;
       /* for (Job job:
             jobs) {
            if (job.getId().equals(id)){
                job.setTitle(updateJob.getTitle());
                job.setDescription(updateJob.getDescription());
                job.setMinSalary(updateJob.getMinSalary());
                job.setMaxSalary(updateJob.getMaxSalary());
                job.setLocation(updateJob.getLocation());
                return true;
            }

        }*/

    }
}
