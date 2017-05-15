package com.example.seko.mwaslaty.android.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Helal on 10/05/2017.
 */
public class Solution implements Serializable {

    String tybe;
    @SerializedName("Cost")
    private String cost;
    @SerializedName("Checkpoint")
    private List<Checkpoint> checkpoints;

    public String getTybe() {
        return tybe;
    }

    public void setTybe(String tybe) {
        this.tybe = tybe;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public List<Checkpoint> getCheckpoints() {
        return checkpoints;
    }

    public void setCheckpoints(List<Checkpoint> checkpoints) {
        this.checkpoints = checkpoints;
    }

    @Override
    public String toString() {
        return "Solution [Cost = " + cost + ", Chekpoint = " + checkpoints + "]";
    }
}
