package com.example.seko.mwaslaty.android;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.seko.mwaslaty.android.model.ErrorCodes;
import com.example.seko.mwaslaty.android.model.Station;
import com.example.seko.mwaslaty.android.presenter.MainPresenter;
import com.example.seko.mwaslaty.android.view.IMainView;

import java.io.Serializable;
import java.util.List;

//import android.util.Log;

/**
 * Created by Helal
 */

public class MainActivity extends AppCompatActivity implements OnClickListener, IMainView {

    private ProgressDialog progDailog;
    private MainPresenter mMainPresenter;

    private Button btnGetSourceFromMap;
    private Button btnGetSourceFromList;
    private Button btnGetDestinationFromMap;
    private Button btnGetDestinationFromList;
    private Button goButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        loadAvailableStations();
    }

    private void init() {
        mMainPresenter = new MainPresenter(this);

        btnGetSourceFromMap = (Button) findViewById(R.id.btn_get_source_from_map);
        btnGetSourceFromList = (Button) findViewById(R.id.btn_get_source_from_list);
        btnGetDestinationFromMap = (Button) findViewById(R.id.btn_get_destination_from_map);
        btnGetDestinationFromList = (Button) findViewById(R.id.btn_get_destination_from_list);
        goButton = (Button) findViewById(R.id.btn_go);
        btnGetSourceFromMap.setOnClickListener(this);
        btnGetSourceFromList.setOnClickListener(this);
        btnGetDestinationFromMap.setOnClickListener(this);
        btnGetDestinationFromList.setOnClickListener(this);
        goButton.setOnClickListener(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showLoadingIndicator() {
        progDailog = new ProgressDialog(MainActivity.this);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Station resultStation = (Station) data.getExtras().getSerializable(getResources().getString(
                    R.string.res_station_key));
            switch (requestCode) {
                case 1:
                case 2:
                    mMainPresenter.setSourceStation(resultStation);
                    btnGetSourceFromList.setText(resultStation.getName());
//                    Log.v("exce", resStation.getName());
                    break;
                case 3:
                case 4:
                    mMainPresenter.setDestinationStation(resultStation);
                    btnGetDestinationFromList.setText(resultStation.getName());
//                    Log.v("exce", resStation.getName());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void loadAvailableStations() {
        mMainPresenter.loadAvailableStations();
    }

    @Override
    public void getSourceStationFromMap(List<Station> stations) {
        if (stations != null) {
            Intent toGoogleMapsActivityIntent = new Intent(MainActivity.this,
                    GoogleMapsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(getResources().getString(
                    R.string.all_stations_key), (Serializable) mMainPresenter.getmAvailableStationsList());
            toGoogleMapsActivityIntent.putExtras(bundle);
            startActivityForResult(toGoogleMapsActivityIntent, getResources().getInteger(R.integer.get_source_from_map_request_code));
        } else {
            showLoadingError(ErrorCodes.NO_DATA);
        }
    }

    @Override
    public void getSourceStationFromList(List<Station> stations) {
        if (stations != null) {
            Intent toAvailableStationsActivity = new Intent(MainActivity.this,
                    AvailableStationsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(getResources().getString(
                    R.string.all_stations_key), (Serializable) mMainPresenter.getmAvailableStationsList());
            toAvailableStationsActivity.putExtras(bundle);
            startActivityForResult(toAvailableStationsActivity, getResources().getInteger(R.integer.get_source_from_list_request_code));
        } else {
            showLoadingError(ErrorCodes.NO_DATA);
        }
    }

    @Override
    public void getDestinationStationFromMap(List<Station> stations) {
        if (stations != null) {
            Intent toGoogleMapsActivityIntent = new Intent(MainActivity.this,
                    GoogleMapsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(getResources().getString(
                    R.string.all_stations_key), (Serializable) mMainPresenter.getmAvailableStationsList());
            toGoogleMapsActivityIntent.putExtras(bundle);
            startActivityForResult(toGoogleMapsActivityIntent, getResources().getInteger(R.integer.get_destination_from_map_request_code));
        } else {
            showLoadingError(ErrorCodes.NO_DATA);
        }
    }

    @Override
    public void getDestinationStationFromList(List<Station> stations) {
        if (stations != null) {
            Intent toAvailableStationsActivity = new Intent(MainActivity.this,
                    AvailableStationsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(getResources().getString(
                    R.string.all_stations_key), (Serializable) mMainPresenter.getmAvailableStationsList());
            toAvailableStationsActivity.putExtras(bundle);
            startActivityForResult(toAvailableStationsActivity, getResources().getInteger(R.integer.get_destination_from_list_request_code));
        } else {
            showLoadingError(ErrorCodes.NO_DATA);
        }
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
            case No_STATION_CHOSEN:
                index = 4;
                break;
        }
        Toast.makeText(MainActivity.this,
                getResources().getStringArray(R.array.error_messages)[index],
                Toast.LENGTH_SHORT).show();
        if (index != 4)
            finish();
    }

    @Override
    public void navigateToSolutionActivity() {
        Intent toSolutionsActivity = new Intent(MainActivity.this,
                SolutionsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(getResources().getString(
                R.string.all_stations_key), (Serializable) mMainPresenter.getmAvailableStationsList());
        bundle.putSerializable(getResources().getString(
                R.string.source_station_key), (Serializable) mMainPresenter.getSourceStation());
        bundle.putSerializable(getResources().getString(
                R.string.destination_station_key), (Serializable) mMainPresenter.getDestinationStation());
        toSolutionsActivity.putExtras(bundle);
        startActivity(toSolutionsActivity);
    }

    @Override
    public void onClick(View v) {
        mMainPresenter.buttonClicked(v.getId());
    }
}
