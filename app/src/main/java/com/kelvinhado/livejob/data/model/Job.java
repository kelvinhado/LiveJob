package com.kelvinhado.livejob.data.model;

import java.sql.Date;

/**
 * Created by kelvin on 19/10/2017 .
 */

public class Job {

    private String id;

    private Company company;

    private String title;

    private double maxSalary;

    private double minSalary;

    private double currentSalary;

    private int processPercentage;

    private int rating;

    private Date creationDate;

    private Date lastUpdateDate;

    public Job() {
    }

    public Job(String id, Company company, String title, double maxSalary, double minSalary, double currentSalary, int processPercentage, int rating, Date creationDate, Date lastUpdateDate) {
        this.id = id;
        this.company = company;
        this.title = title;
        this.maxSalary = maxSalary;
        this.minSalary = minSalary;
        this.currentSalary = currentSalary;
        this.processPercentage = processPercentage;
        this.rating = rating;
        this.creationDate = creationDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(double maxSalary) {
        this.maxSalary = maxSalary;
    }

    public double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(double minSalary) {
        this.minSalary = minSalary;
    }

    public double getCurrentSalary() {
        return currentSalary;
    }

    public void setCurrentSalary(double currentSalary) {
        this.currentSalary = currentSalary;
    }

    public int getProcessPercentage() {
        return processPercentage;
    }

    public void setProcessPercentage(int processPercentage) {
        this.processPercentage = processPercentage;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setCreationDate(int datetime) {
        this.creationDate = new Date(datetime);
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(int datetime) {
        this.lastUpdateDate = new Date(datetime);
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

}
