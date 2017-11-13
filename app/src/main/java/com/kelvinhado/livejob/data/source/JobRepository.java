package com.kelvinhado.livejob.data.source;

import android.support.annotation.NonNull;

import com.kelvinhado.livejob.data.model.Job;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by kelvin on 19/10/2017 .
 */

public class JobRepository implements JobDataSource {

    private static JobRepository sInstance = null;

    private final JobDataSource mLocalDataSource;

    //prevent direct instantiation
    private JobRepository(@NonNull JobDataSource localDataSource) {
        this.mLocalDataSource = checkNotNull(localDataSource);
    }

    public static JobRepository getInstance(@NonNull JobDataSource localDataSource) {
        if(sInstance == null) {
            sInstance = new JobRepository(localDataSource);
        }
        return sInstance;
    }

    @Override
    public void getJobs(@NonNull LoadJobsCallback callback) {
        mLocalDataSource.getJobs(callback);
    }

    @Override
    public void deleteAllJobs() {
        mLocalDataSource.deleteAllJobs();
    }

    @Override
    public void saveJob(@NonNull Job job) {
        mLocalDataSource.saveJob(job);
    }
}
