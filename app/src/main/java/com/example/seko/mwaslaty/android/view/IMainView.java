package com.example.seko.mwaslaty.android.view;

import android.content.Context;

import com.example.seko.mwaslaty.android.model.ErrorCodes;
import com.example.seko.mwaslaty.android.model.Station;

import java.util.List;

/**
 * Created by Helal on 22/04/2017.
 */
public interface IMainView {
    Context getContext();

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void loadAvailableStations();

    void getSourceStationFromMap(List<Station> stations);

    void getSourceStationFromList(List<Station> stations);

    void getDestinationStationFromMap(List<Station> stations);

    void getDestinationStationFromList(List<Station> stations);

    void showLoadingError(ErrorCodes code);

    void navigateToSolutionActivity();
}
