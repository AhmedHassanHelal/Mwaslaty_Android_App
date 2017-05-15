package com.example.seko.mwaslaty.android.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Helal on 10/05/2017.
 */
public class Checkpoint implements Serializable {
    @SerializedName("BusID")
    private String BusID;

    @SerializedName("StationID")
    private String StationID;

    public String getBusID() {
        return BusID;
    }

    public void setBusID(String BusName) {
        this.BusID = BusName;
    }

    public String getStationID() {
        return StationID;
    }

    public void setStationID(String StationID) {
        this.StationID = StationID;
    }

    @Override
    public String toString() {
        return "Checkpoint [BusName = " + BusID + ", StationID = " + StationID + "]";
    }
}
