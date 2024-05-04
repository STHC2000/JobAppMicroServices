package com.sthc.jobms.Job;


import com.sthc.jobms.Job.dto.JobDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService JobService;

    public JobController(JobService jobService) {
        JobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<JobDto>> findAll(){
        return ResponseEntity.ok(JobService.findAll());
    }

    @PostMapping
    public ResponseEntity<String> CreateJob(@RequestBody Job job){
        JobService.createJob(job);

        return new ResponseEntity<>("Job created successfully.",HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDto> GetJobById(@PathVariable Long id){
        JobDto jobDto = JobService.getJobById(id);
        if (jobDto != null)
            return new ResponseEntity<>(jobDto, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteJob(@PathVariable Long id){
        boolean deleted = JobService.deleteById(id);
        if (deleted)
            return new ResponseEntity<>("Job deleted successfully",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> UpdateJob(@PathVariable Long id,@RequestBody Job updateJob){
        boolean updated = JobService.updateJob(id,updateJob);
        if (updated)
            return  new ResponseEntity<>("Job updated Successfully",HttpStatus.OK);
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
