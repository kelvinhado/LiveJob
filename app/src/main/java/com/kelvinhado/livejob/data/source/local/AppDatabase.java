package com.kelvinhado.livejob.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.kelvinhado.livejob.data.source.local.dao.JobDao;
import com.kelvinhado.livejob.data.source.local.entities.JobEntity;


@Database(entities = {JobEntity.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract JobDao getJobDao();
}
