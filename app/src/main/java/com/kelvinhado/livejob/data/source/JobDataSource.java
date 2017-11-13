package com.kelvinhado.livejob.data.source;

import android.support.annotation.NonNull;

import com.kelvinhado.livejob.data.model.Job;

import java.util.List;

/**
 * Created by kelvin on 19/10/2017  .
 */

public interface JobDataSource {

    /**
     * gets the jobs stored in data source
     *
     * @param callback callback
     */
    void getJobs(@NonNull LoadJobsCallback callback);

    /**
     * deletes all saved jobs
     */
    void deleteAllJobs();

    /**
     * Saves a new job to the data source
     *
     * @param job to be saved
     */
    void saveJob(@NonNull Job job);

    interface LoadJobsCallback {

        void onJobsLoaded(@NonNull List<Job> jobs);

        void onDataNotAvailable();
    }

}
