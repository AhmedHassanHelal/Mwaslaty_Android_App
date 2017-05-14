package com.example.seko.mwaslaty.android.cells;

import android.widget.TextView;

/**
 * Created by Helal on 14/05/2017.
 */
public class SolutionCell {
    TextView tvSolutionName;
    TextView tvSolutionCost;
    TextView tvChangeTransportationNum;

    public TextView getTvSolutionName() {
        return tvSolutionName;
    }

    public void setTvSolutionName(TextView tvSolutionName) {
        this.tvSolutionName = tvSolutionName;
    }

    public TextView getTvSolutionCost() {
        return tvSolutionCost;
    }

    public void setTvSolutionCost(TextView tvSolutionCost) {
        this.tvSolutionCost = tvSolutionCost;
    }

    public TextView getTvChangeTransportationNum() {
        return tvChangeTransportationNum;
    }

    public void setTvChangeTransportationNum(TextView tvChangeTransportationNum) {
        this.tvChangeTransportationNum = tvChangeTransportationNum;
    }
}
