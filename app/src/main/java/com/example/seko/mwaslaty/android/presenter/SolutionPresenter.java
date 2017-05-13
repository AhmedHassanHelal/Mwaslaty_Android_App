package com.example.seko.mwaslaty.android.presenter;

import android.os.AsyncTask;

import com.example.seko.mwaslaty.android.model.ErrorCodes;
import com.example.seko.mwaslaty.android.model.Solution;
import com.example.seko.mwaslaty.android.services.INetworkService;
import com.example.seko.mwaslaty.android.services.IWebServiceManager;
import com.example.seko.mwaslaty.android.services.impl.NetworkService;
import com.example.seko.mwaslaty.android.services.impl.WebServiceManager;
import com.example.seko.mwaslaty.android.utill.Constants;
import com.example.seko.mwaslaty.android.view.ISolutionView;
import com.example.seko.mwaslaty.parsers.SolutionsParser;

/**
 * Created by Helal on 13/05/2017.
 */
public class SolutionPresenter {

    ISolutionView mSolutionView;

    INetworkService mNetworkService;
    IWebServiceManager mWebServiceManager;

    Solution mSolution;

    public SolutionPresenter(ISolutionView solutionView) {
        this.mSolutionView = solutionView;

        this.mNetworkService = new NetworkService();
        this.mWebServiceManager = new WebServiceManager();

        mSolution = null;
    }


    public void buttonClicked(int id) {


    }

    public void loadSolutions() {
        new AsyncTask<Void, Void, Void>() {
            boolean isConnected;
            Solution solution;

            @Override
            protected void onPreExecute() {
                mSolutionView.showLoadingIndicator();
                solution = new Solution();
                isConnected = false;
            }

            ;

            @Override
            protected Void doInBackground(Void... params) {
                String url = Constants.MwaslatyGetSolutionURL;
                String mJsonResponse = null;
                isConnected = mNetworkService
                        .isInternetConnectionAvailable(mSolutionView
                                .getContext());
                if (isConnected) {
                    mWebServiceManager.setCookies(Constants.MwaslatyGetSolutionCookies);
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
                if (null == solution) {
                    mSolutionView.showLoadingError(ErrorCodes.ERROR_LOADING);
                } else if (solution.getChekpoint().size() == 0) {
                    mSolutionView.showLoadingError(ErrorCodes.NO_SOLUTION);
                } else {
                    mSolution = solution;
                    mSolutionView.showSolution();
                }
            }

            ;
        }.execute();
    }
}
