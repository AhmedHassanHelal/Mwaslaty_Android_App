package com.example.seko.mwaslaty.android.model;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Helal on 10/05/2017.
 */
public class Solution {

    @SerializedName("Cost")
    private String cost;

    @SerializedName("Checkpoint")
    private Checkpoint[] checkpoints;

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public List<Checkpoint> getChekpoint() {
        return Arrays.asList(checkpoints);
    }

    public void setChekpoint(Checkpoint[] checkpoint) {
        this.checkpoints = checkpoint;
    }

    @Override
    public String toString() {
        return "Solution [Cost = " + cost + ", Chekpoint = " + checkpoints + "]";
    }
}
