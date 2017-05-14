package com.example.seko.mwaslaty.android;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.seko.mwaslaty.android.adapter.SolutionsAdapter;
import com.example.seko.mwaslaty.android.model.ErrorCodes;
import com.example.seko.mwaslaty.android.model.Solution;
import com.example.seko.mwaslaty.android.presenter.SolutionPresenter;
import com.example.seko.mwaslaty.android.view.ISolutionView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Helal
 */

public class SolutionsActivity extends AppCompatActivity implements ISolutionView {

    List<Solution> mSolutions;
    private ProgressDialog progDailog;
    private SolutionPresenter mSolutionPresenter;
    private ListView lvSolutions;

    private SolutionsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solutions);

        init();

        loadSolutions();
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
    }

    @Override
    public void showSolution() {
        if (mSolutionPresenter.getSolution() != null) {
            List<Solution> solutions = new ArrayList<Solution>();
            solutions.add(mSolutionPresenter.getSolution());
            adapter = new SolutionsAdapter(SolutionsActivity.this,
                    R.id.lvSolutions, solutions);
            lvSolutions.setAdapter(adapter);
        } else {
            showLoadingError(ErrorCodes.NO_DATA);
        }
    }

    @Override
    public void showSolutionOnMap() {

    }

    @Override
    public void onClick(View v) {
        mSolutionPresenter.buttonClicked(v.getId());
    }
}
