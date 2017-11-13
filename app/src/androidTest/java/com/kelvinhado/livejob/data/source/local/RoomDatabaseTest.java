package com.kelvinhado.livejob.data.source.local;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.kelvinhado.livejob.data.source.local.dao.JobDao;
import com.kelvinhado.livejob.data.source.local.entities.Job;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import io.reactivex.functions.Predicate;

/**
 * Created by kelvinhado.
 */
@RunWith(AndroidJUnit4.class)
public class RoomDatabaseTest {
    private JobDao mJobDao;
    private AppDatabase mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                //for testing purpose
                .allowMainThreadQueries()
                .build();
        mJobDao = mDb.getJobDao();
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {

        // Given that we have a user in the data source
        Job job = new Job();
        job.title = "lol";
        job.id = "F3232423";
        job.creationDate = new Date();
        mJobDao.insertJob(job);

        // When subscribing to the emissions of jobs
        mJobDao.getAllJobs()
                .test()
                .assertValue(new Predicate<List<Job>>() {
                    @Override
                    public boolean test(List<Job> jobs) throws Exception {
                        return "lol".equals(jobs.get(0).title);
                    }
                });
    }

}