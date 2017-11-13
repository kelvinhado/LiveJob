package com.kelvinhado.livejob.jobs;

import android.support.annotation.NonNull;

import com.kelvinhado.livejob.data.model.Job;
import com.kelvinhado.livejob.data.source.JobDataSource;

import java.util.List;


public class JobsPresenter implements JobsContract.Presenter {

    private JobsContract.View mView;

    private JobDataSource mRepository;

    public JobsPresenter(JobsContract.View view, JobDataSource repository) {
        this.mView = view;
        this.mRepository = repository;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        requestLoadJobs(false);
    }

    @Override
    public void requestLoadJobs(boolean forceRefresh) {
        mRepository.getJobs(new JobDataSource.LoadJobsCallback() {
            @Override
            public void onJobsLoaded(@NonNull List<Job> applicationList) {
                mView.showLoadedJobsList(applicationList);
                mView.showLoadingIndicator(false);
            }

            @Override
            public void onDataNotAvailable() {
                mView.showLoadingFailed();
                loadFakeData();
            }
        });
    }

    private void loadFakeData() {
        //TODO
    }
}
