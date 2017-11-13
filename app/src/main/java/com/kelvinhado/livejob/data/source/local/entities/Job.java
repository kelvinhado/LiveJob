package com.kelvinhado.livejob.data.source.local.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by kelvin on 30/10/2017 .
 */

@Entity(tableName = "jobs")
public class Job {

    @NonNull
    @ColumnInfo(name = "job_id")
    @PrimaryKey
    public String id;

    public String title;

    @Embedded
    public Company company;

    public double maxSalary;

    public double minSalary;

    public double currentSalary;

    public int processPercentage;

    public int rating;

    public Date creationDate;

    public Date lastUpdateDate;
}
