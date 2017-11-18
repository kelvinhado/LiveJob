package com.kelvinhado.livejob.data.source.local;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

import com.kelvinhado.livejob.data.model.Job;
import com.kelvinhado.livejob.data.source.JobDataSource;
import com.kelvinhado.livejob.data.source.local.dao.JobDao;
import com.kelvinhado.livejob.data.source.local.entities.JobEntity;
import com.kelvinhado.livejob.data.source.local.utils.DaoUtils;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;


public class JobLocalDataSource implements JobDataSource {

    private JobDao mJobDao;
    private AppDatabase mDb;

    private static JobLocalDataSource sInstance = null;


    //prevent direct instantiation
    private JobLocalDataSource(@NonNull Context context) {
        checkNotNull(context);
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        mJobDao = mDb.getJobDao();
    }

    public static JobLocalDataSource getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new JobLocalDataSource(context);
        }
        return sInstance;
    }

    /**
     * Get applications stored in sqlite db
     *
     * @param callback callback
     */
    @Override
    public void getJobs(@NonNull final LoadJobsCallback callback) {
        Flowable<List<JobEntity>> flowable = mJobDao.getAllJobs();
        flowable.subscribeOn(Schedulers.io())
                .map(new Function<List<JobEntity>, List<Job>>() {
                    @Override
                    public  List<Job> apply(List<JobEntity> jobEntities) throws Exception {
                        return DaoUtils.convertAllToJobs(jobEntities);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Job>>() {
                    @Override
                    public void accept(List<Job> jobs) throws Exception {
                        callback.onJobsLoaded(jobs);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.onDataNotAvailable();
                    }
                });
    }

    @Override
    public void deleteAllJobs() {

    }

    @Override
    public void saveJob(@NonNull Job job) {
        mJobDao.insertJob(DaoUtils.convertToJobEntity(job));
    }

}
