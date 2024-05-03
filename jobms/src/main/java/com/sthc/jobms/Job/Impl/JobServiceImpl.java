package com.sthc.jobms.Job.Impl;

import com.sthc.jobms.Job.Job;
import com.sthc.jobms.Job.JobRepository;
import com.sthc.jobms.Job.JobService;
import com.sthc.jobms.Job.external.Company;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    //private List<Job> jobs = new ArrayList<>();
    private JobRepository jobRepository;


    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        RestTemplate restTemplate = new RestTemplate();
       Company company = restTemplate.getForObject("http://localhost:8081/companies/1", Company.class);
       System.out.println("COMPANY NAME : " + company.getName());
        System.out.println("COMPANY ID : " + company.getId());
       return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {

//        jobs.add(job);
        jobRepository.save(job);

    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
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
