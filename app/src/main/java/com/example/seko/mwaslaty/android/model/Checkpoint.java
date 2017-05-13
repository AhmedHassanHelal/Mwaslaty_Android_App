package com.example.seko.mwaslaty.android.model;

/**
 * Created by Helal on 10/05/2017.
 */
public class Checkpoint {
    private String BusName;

    private String StationID;

    public String getBusName() {
        return BusName;
    }

    public void setBusName(String BusName) {
        this.BusName = BusName;
    }

    public String getStationID() {
        return StationID;
    }

    public void setStationID(String StationID) {
        this.StationID = StationID;
    }

    @Override
    public String toString() {
        return "Checkpoint [BusName = " + BusName + ", StationID = " + StationID + "]";
    }
}
