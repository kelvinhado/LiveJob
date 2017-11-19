package com.kelvinhado.livejob.data.source.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.kelvinhado.livejob.data.source.local.entities.JobEntity;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by kelvinhado.
 */

@Dao
public interface JobDao {

    @Query("SELECT * FROM jobs")
    Single<List<JobEntity>> getAllJobs();

    @Insert
    void insertJob(JobEntity jobEntity);

    @Delete
    void deleteJobs(JobEntity... jobEntities);

    @Update
    void updateJobs(JobEntity... jobEntities);
}
