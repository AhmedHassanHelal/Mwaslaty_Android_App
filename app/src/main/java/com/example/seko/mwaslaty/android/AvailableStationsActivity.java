package com.example.seko.mwaslaty.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.seko.mwaslaty.android.adapter.AvailableStationsAdapter;
import com.example.seko.mwaslaty.android.model.Station;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Helal
 */
public class AvailableStationsActivity extends AppCompatActivity {

    List<Station> mAvailableStationsList;
    List<Station> mFilteredStations;
    ListView lvAvailableStations;
    EditText etFilter;
    AvailableStationsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_stations);
        etFilter = (EditText) findViewById(R.id.etSearch);
        lvAvailableStations = (ListView) findViewById(R.id.lvAvailableStations);
        mFilteredStations = new ArrayList<Station>();
        mAvailableStationsList = (List<Station>) getIntent().getExtras().get(getResources().getString(
                R.string.all_stations_key));

        showAvailableStations(mAvailableStationsList);

        lvAvailableStations.setTextFilterEnabled(true);

        lvAvailableStations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                Intent returnIntent = new Intent();
                Bundle bundle = new Bundle();
                if (mFilteredStations.size() != 0)
                    bundle.putSerializable(getResources().getString(
                            R.string.res_station_key), mFilteredStations.get(position));
                else
                    bundle.putSerializable(getResources().getString(
                            R.string.res_station_key), mAvailableStationsList.get(position));
                returnIntent.putExtras(bundle);
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });

        etFilter.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub

                for (Station station : mAvailableStationsList) {
                    if (station.getName().toString().contains(arg0))
                        mFilteredStations.add(station);
                }
//                AvailableStationsActivity.this.adapter.getFilter().filter(arg0.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub
                mFilteredStations.clear();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                showAvailableStations(mFilteredStations);
            }
        });

    }

    public void showAvailableStations(List<Station> stations) {
        if (null != stations) {
            adapter = new AvailableStationsAdapter(AvailableStationsActivity.this,
                    R.id.lvAvailableStations, stations);
            lvAvailableStations.setAdapter(adapter);
        }
    }
}
