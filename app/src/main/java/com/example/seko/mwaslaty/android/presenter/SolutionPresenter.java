package com.example.seko.mwaslaty.android.presenter;

import android.os.AsyncTask;

import com.example.seko.mwaslaty.android.model.ErrorCodes;
import com.example.seko.mwaslaty.android.model.Solution;
import com.example.seko.mwaslaty.android.model.Station;
import com.example.seko.mwaslaty.android.services.INetworkService;
import com.example.seko.mwaslaty.android.services.IWebServiceManager;
import com.example.seko.mwaslaty.android.services.impl.NetworkService;
import com.example.seko.mwaslaty.android.services.impl.WebServiceManager;
import com.example.seko.mwaslaty.android.utill.Constants;
import com.example.seko.mwaslaty.android.view.ISolutionView;
import com.example.seko.mwaslaty.parsers.SolutionsParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Helal on 13/05/2017.
 */
public class SolutionPresenter {

    ISolutionView mSolutionView;

    INetworkService mNetworkService;
    IWebServiceManager mWebServiceManager;

    List<Solution> mSolutions;

    List<Station> mAllStations;
    Station mSourceStation;
    Station mDestinationStation;


    public SolutionPresenter(ISolutionView solutionView) {
        this.mSolutionView = solutionView;

        this.mNetworkService = new NetworkService();
        this.mWebServiceManager = new WebServiceManager();

        mSolutions = null;
    }

    public List<Station> getmAllStations() {
        return mAllStations;
    }

    public Station getmSourceStation() {
        return mSourceStation;
    }

    public void setAllStations(List<Station> allStations) {
        this.mAllStations = allStations;
    }

    public void setSourceStation(Station sourceStation) {
        this.mSourceStation = sourceStation;
    }

    public void setDestinationStation(Station destinationStation) {
        this.mDestinationStation = destinationStation;
    }

    public List<Solution> getSolutions() {
        return mSolutions;
    }

//    public void buttonClicked(int id) {
//
//
//    }

    public void loadSolutions() {
        loadBestTimeSolution();
    }

    private void loadBestTimeSolution() {
        new AsyncTask<Void, Void, Void>() {
            boolean isConnected;
            Solution solution;

            @Override
            protected void onPreExecute() {
                mSolutionView.showLoadingIndicator();
                solution = new Solution();
                isConnected = false;
            }

            @Override
            protected Void doInBackground(Void... params) {
                String BestTimeUrl = Constants.MwaslatyGetBestTimeSolutionURL + "SID=" + mSourceStation.getID() + "&DID=" + mDestinationStation.getID();
                String mBestTimeJsonResponse = null;
                isConnected = mNetworkService
                        .isInternetConnectionAvailable(mSolutionView
                                .getContext());
                if (isConnected) {
                    mWebServiceManager.setCookies(Constants.MwaslatyCookie);
                    mBestTimeJsonResponse = mWebServiceManager.GET(BestTimeUrl);
                }
                if (mBestTimeJsonResponse != null)
                    solution = new SolutionsParser()
                            .getSolution(mBestTimeJsonResponse);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
//                mSolutionView.hideLoadingIndicator();
                if (!isConnected)
                    mSolutionView.showLoadingError(ErrorCodes.NO_INTERNET_CONNECTION);
                if (null == solution || solution.getCheckpoints() == null) {
                    mSolutionView.showLoadingError(ErrorCodes.ERROR_LOADING);
                } else if (solution.getCheckpoints().size() == 0) {
                    mSolutionView.showLoadingError(ErrorCodes.NO_SOLUTION);
                } else {
                    solution.setTybe("Default route:");
                    if (mSolutions == null)
                        mSolutions = new ArrayList<Solution>();
                    mSolutions.add(solution);
                    mSolutionView.showSolutions();
                    loadBestCostSolution();
                }
            }

            ;
        }.execute();
    }

    private void loadBestCostSolution() {
        new AsyncTask<Void, Void, Void>() {
            boolean isConnected;
            Solution solution;

            @Override
            protected void onPreExecute() {
//                mSolutionView.showLoadingIndicator();
                solution = new Solution();
                isConnected = false;
            }

            ;

            @Override
            protected Void doInBackground(Void... params) {
                String url = Constants.MwaslatyGetBestCostSolutionURL + "SID=" + mSourceStation.getID() + "&DID=" + mDestinationStation.getID();
                String mJsonResponse = null;
                isConnected = mNetworkService
                        .isInternetConnectionAvailable(mSolutionView
                                .getContext());
                if (isConnected) {
                    mWebServiceManager.setCookies(Constants.MwaslatyCookie);
                    mJsonResponse = mWebServiceManager.GET(url);
                }
                if (mJsonResponse != null)
                    solution = new SolutionsParser()
                            .getSolution(mJsonResponse);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                mSolutionView.hideLoadingIndicator();
                if (!isConnected)
                    mSolutionView.showLoadingError(ErrorCodes.NO_INTERNET_CONNECTION);
                if (null == solution || solution.getCheckpoints() == null) {
                    mSolutionView.showLoadingError(ErrorCodes.ERROR_LOADING);
                } else if (solution.getCheckpoints().size() == 0) {
                    mSolutionView.showLoadingError(ErrorCodes.NO_SOLUTION);
                } else {
                    solution.setTybe("Best Cost:");
                    if (mSolutions == null)
                        mSolutions = new ArrayList<Solution>();
                    mSolutions.add(solution);
                    mSolutionView.showSolutions();
                }
            }

            ;
        }.execute();
    }
}
