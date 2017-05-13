package com.example.seko.mwaslaty.parsers;

import com.example.seko.mwaslaty.android.model.Station;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;


/**
 * Created by Helal on 05/05/2017.
 */

public class AvailableStationsParser {
    public List<Station> getAvailableStations(String mJsonResponse) {
        List<Station> mAvailableStationsList = null;
        Gson gson = new GsonBuilder().create();
        try {
            mAvailableStationsList = Arrays.asList(gson.fromJson(mJsonResponse, Station[].class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mAvailableStationsList;
    }
}