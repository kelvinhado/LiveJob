package com.kelvinhado.livejob.data.source.local;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

import com.kelvinhado.livejob.data.model.Job;
import com.kelvinhado.livejob.data.source.JobDataSource;
import com.kelvinhado.livejob.data.source.local.dao.JobDao;

import static com.google.common.base.Preconditions.checkNotNull;


public class JobLocalDataSource implements JobDataSource {

    private static final String TAG = JobLocalDataSource.class.getSimpleName();

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
        callback.onJobsLoaded(mJobDao.getAllJobs());
    }

    @Override
    public void deleteAllJobs() {

    }

    @Override
    public void saveJob(@NonNull Job job) {
        mJobDao.insertJob(job);
    }

}
