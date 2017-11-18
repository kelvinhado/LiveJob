package com.kelvinhado.livejob.data.source.local.utils;

import com.kelvinhado.livejob.data.model.Company;
import com.kelvinhado.livejob.data.model.Job;
import com.kelvinhado.livejob.data.source.local.entities.CompanyEntity;
import com.kelvinhado.livejob.data.source.local.entities.JobEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelvinhado.
 */

public class DaoUtils {

    public static List<Job> convertAllToJobs(List<JobEntity> jobEntities) {
        List<Job> jobList = new ArrayList<>();
        for (JobEntity jobEntity : jobEntities) {
            jobList.add(convertToModelJob(jobEntity));
        }
        return jobList;
    }


    public static Job convertToModelJob(JobEntity je) {
        return new Job(
                je.id,
                convertToModelCompany(je.companyEntity),
                je.title,
                je.maxSalary,
                je.minSalary,
                je.currentSalary,
                je.processPercentage,
                je.rating,
                je.creationDate,
                je.lastUpdateDate);
    }

    public static Company convertToModelCompany(CompanyEntity ce) {
        return new Company(
                ce.name, ce.type, ce.thumbnail);
    }

    public static JobEntity convertToJobEntity(Job job) {
        JobEntity entity = new JobEntity();
        entity.id = job.getId();
        entity.companyEntity = convertToCompanyEntity(job.getCompany());
        entity.title = job.getTitle();
        entity.maxSalary = job.getMaxSalary();
        entity.minSalary = job.getMinSalary();
        entity.currentSalary = job.getCurrentSalary();
        entity.processPercentage = job.getProcessPercentage();
        entity.rating = job.getRating();
        entity.creationDate = job.getCreationDate();
        entity.lastUpdateDate = job.getLastUpdateDate();
        return entity;
    }

    public static CompanyEntity convertToCompanyEntity(Company company) {
        CompanyEntity entity = new CompanyEntity();
        entity.name = company.getName();
        entity.type = company.getType();
        entity.thumbnail = company.getThumbnail();
        return entity;
    }

}
