package com.kelvinhado.livejob.data.model;

/**
 * Created by kelvin on 19/10/2017 .
 */

public class Company {

    private String name;

    private String type;

    private String thumbnail;

    public Company() {
    }

    public Company(String name, String type, String thumbnail) {
        this.name = name;
        this.type = type;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
