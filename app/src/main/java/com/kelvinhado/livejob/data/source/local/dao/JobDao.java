package com.kelvinhado.livejob.data.source.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.kelvinhado.livejob.data.source.local.entities.Job;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by kelvinhado.
 */

@Dao
public interface JobDao {

    @Query("SELECT * FROM jobs")
    Flowable<List<Job>> getAllJobs();

    @Insert
    void insertJob(Job job);

    @Delete
    void deleteJobs(Job... jobs);

    @Update
    void updateJobs(Job... jobs);
}
