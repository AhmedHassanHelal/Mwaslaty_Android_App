package com.example.seko.mwaslaty.android.presenter;


import android.os.AsyncTask;

import com.example.seko.mwaslaty.android.R;
import com.example.seko.mwaslaty.android.model.ErrorCodes;
import com.example.seko.mwaslaty.android.model.Station;
import com.example.seko.mwaslaty.android.services.INetworkService;
import com.example.seko.mwaslaty.android.services.IWebServiceManager;
import com.example.seko.mwaslaty.android.services.impl.NetworkService;
import com.example.seko.mwaslaty.android.services.impl.WebServiceManager;
import com.example.seko.mwaslaty.android.utill.Constants;
import com.example.seko.mwaslaty.android.view.IMainView;
import com.example.seko.mwaslaty.parsers.AvailableStationsParser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Helal on 25/04/2017.
 */
public class MainPresenter implements Serializable {
    private INetworkService mNetworkService;
    private IWebServiceManager mWebServiceManager;
    private IMainView mMainView;

    private Station mSourceStation;
    private Station mDestinationStation;

    private List<Station> mAvailableStationsList;

    public MainPresenter(IMainView mainView) {
        this.mMainView = mainView;

        this.mNetworkService = new NetworkService();
        this.mWebServiceManager = new WebServiceManager();

        mSourceStation = null;
        mDestinationStation = null;

        mAvailableStationsList = null;
    }

    public Station getSourceStation() {
        return mSourceStation;
    }

    public void setSourceStation(Station mSourceStation) {
        this.mSourceStation = mSourceStation;
    }

    public Station getDestinationStation() {
        return mDestinationStation;
    }

    public void setDestinationStation(Station mDestinationStation) {
        this.mDestinationStation = mDestinationStation;
    }

    public List<Station> getmAvailableStationsList() {
        return mAvailableStationsList;
    }

    public void loadAvailableStations() {
        new AsyncTask<Void, Void, Void>() {
            boolean isConnected;
            List<Station> availableStationsList;

            @Override
            protected void onPreExecute() {
                mMainView.showLoadingIndicator();
                availableStationsList = new ArrayList<Station>();
                isConnected = false;
            }

            ;

            @Override
            protected Void doInBackground(Void... params) {
                String url = Constants.MwaslatyAllStationURL;
                String mJsonResponse = null;
                isConnected = mNetworkService
                        .isInternetConnectionAvailable(mMainView
                                .getContext());
                if (isConnected) {
                    mWebServiceManager.setCookies(Constants.MwaslatyAllStationCookies);
                    mJsonResponse = mWebServiceManager.GET(url);
                }
                if (mJsonResponse != null)
                    availableStationsList = new AvailableStationsParser()
                            .getAvailableStations(mJsonResponse);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                mMainView.hideLoadingIndicator();
                if (!isConnected)
                    mMainView.showLoadingError(ErrorCodes.NO_INTERNET_CONNECTION);
                if (null == availableStationsList) {
                    mMainView.showLoadingError(ErrorCodes.ERROR_LOADING);
                } else if (availableStationsList.size() == 0) {
                    mMainView.showLoadingError(ErrorCodes.NO_DATA);
                } else {
                    mAvailableStationsList = availableStationsList;
//                    mAvailableCentersView.showAvailableCenters(availableCentersList);
                }
            }

            ;
        }.execute();
    }

    public void buttonClicked(int btnId) {
        switch (btnId) {
            case R.id.btn_get_source_from_map:
                mMainView.getSourceStationFromMap(mAvailableStationsList);
                break;
            case R.id.btn_get_source_from_list:
                mMainView.getSourceStationFromList(mAvailableStationsList);
                break;
            case R.id.btn_get_destination_from_map:
                mMainView.getDestinationStationFromMap(mAvailableStationsList);
                break;
            case R.id.btn_get_destination_from_list:
                mMainView.getDestinationStationFromList(mAvailableStationsList);
                break;
            case R.id.btn_go:
                if (getmAvailableStationsList() == null) {
                    mMainView.showLoadingError(ErrorCodes.NO_DATA);
                } else if (getSourceStation() == null || getDestinationStation() == null) {
                    mMainView.showLoadingError(ErrorCodes.No_STATION_CHOSEN);
                } else {
                    mMainView.navigateToSolutionActivity();
                }
                break;
            default:
                break;
        }
    }
}
