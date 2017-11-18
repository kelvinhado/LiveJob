package com.kelvinhado.livejob.data.source.local;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.kelvinhado.livejob.data.source.local.dao.JobDao;
import com.kelvinhado.livejob.data.source.local.entities.JobEntity;

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
        JobEntity jobEntity = new JobEntity();
        jobEntity.title = "lol";
        jobEntity.id = "F3232423";
        jobEntity.creationDate = new Date();
        mJobDao.insertJob(jobEntity);

        // When subscribing to the emissions of jobs
        mJobDao.getAllJobs()
                .test()
                // assertValue asserts that there was only one emission
                .assertValue(new Predicate<List<JobEntity>>() {
                    @Override
                    public boolean test(List<JobEntity> jobEntities) throws Exception {
                        return "lol".equals(jobEntities.get(0).title);
                    }
                });
    }

}