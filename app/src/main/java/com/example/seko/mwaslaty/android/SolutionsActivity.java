package com.example.seko.mwaslaty.android;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.seko.mwaslaty.android.adapter.SolutionsAdapter;
import com.example.seko.mwaslaty.android.model.ErrorCodes;
import com.example.seko.mwaslaty.android.model.Solution;
import com.example.seko.mwaslaty.android.model.Station;
import com.example.seko.mwaslaty.android.presenter.SolutionPresenter;
import com.example.seko.mwaslaty.android.view.ISolutionView;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Helal
 */

public class SolutionsActivity extends AppCompatActivity implements ISolutionView {

    //    List<Station> mAllStations;
    private ProgressDialog progDailog;
    private SolutionPresenter mSolutionPresenter;
    private ListView lvSolutions;

    private SolutionsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solutions);

        init();

        Bundle bundle = getIntent().getExtras();

        mSolutionPresenter.setSourceStation((Station) bundle.getSerializable(getResources().getString(R.string.source_station_key)));
        mSolutionPresenter.setDestinationStation((Station) bundle.getSerializable(getResources().getString(R.string.destination_station_key)));
        mSolutionPresenter.setAllStations((List<Station>) bundle.getSerializable(getResources().getString(R.string.all_stations_key)));

        loadSolutions();

        lvSolutions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                showSolutionOnMap(position);
            }
        });
    }

    private void init() {
        mSolutionPresenter = new SolutionPresenter(this);
        lvSolutions = (ListView) findViewById(R.id.lvSolutions);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showLoadingIndicator() {
        progDailog = new ProgressDialog(SolutionsActivity.this);
        progDailog.setMessage(getString(R.string.LOADING));
        progDailog.setIndeterminate(false);
        progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDailog.setCancelable(true);
        progDailog.show();
    }

    @Override
    public void hideLoadingIndicator() {
        progDailog.dismiss();
    }

    @Override
    public void loadSolutions() {
        mSolutionPresenter.loadSolutions();
    }

    @Override
    public void showLoadingError(ErrorCodes code) {
        int index = 0;
        switch (code) {
            case NO_INTERNET_CONNECTION:
                index = 0;
                break;
            case ERROR_LOADING:
                index = 1;
                break;
            case NO_DATA:
                index = 2;
                break;
            case NO_SOLUTION:
                index = 3;
                break;
        }
        Toast.makeText(SolutionsActivity.this,
                getResources().getStringArray(R.array.error_messages)[index],
                Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showSolutions() {
        List<Solution> solutions = mSolutionPresenter.getSolutions();
        if (solutions != null) {
            adapter = new SolutionsAdapter(SolutionsActivity.this,
                    R.id.lvSolutions, solutions);
            lvSolutions.setAdapter(adapter);
        }
//        else {
//            showLoadingError(ErrorCodes.NO_DATA);
//        }
    }

    @Override
    public void showSolutionOnMap(int solutionIndex) {
        Intent goToDrawSolutionActivityIntent = new Intent(SolutionsActivity.this, DrawSolutionOnGoogleMapActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(getResources().getString(
                R.string.all_stations_key), (Serializable) mSolutionPresenter.getmAllStations());
        bundle.putSerializable(getResources().getString(
                R.string.source_station_key), (Serializable) mSolutionPresenter.getmSourceStation());
        bundle.putSerializable(getResources().getString(
                R.string.solution_key), (Serializable) mSolutionPresenter.getSolutions().get(solutionIndex));
        goToDrawSolutionActivityIntent.putExtras(bundle);
        startActivity(goToDrawSolutionActivityIntent);
    }

    @Override
    public void onClick(View v) {
        mSolutionPresenter.buttonClicked(v.getId());
    }
}
