package com.example.seko.mwaslaty.android.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Helal on 22/04/2017.
 */

public class Station implements Serializable {
    @SerializedName("bus")
    private int bus;

    @SerializedName("metro")
    private int metro;

    @SerializedName("name")
    private String name;

    @SerializedName("ID")
    private int ID;

    @SerializedName("longitude")
    private Double longitude;

    @SerializedName("latitude")
    private Double latitude;


    public int getBus() {
        return bus;
    }

    public void setBus(int bus) {
        this.bus = bus;
    }

    public int getMetro() {
        return metro;
    }

    public void setMetro(int metro) {
        this.metro = metro;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Station [bus = " + bus + ", metro = " + metro + ", name = " + name + ", ID = " + ID + ", longitude = " + longitude + ", latitude = " + latitude + "]";
    }
}
