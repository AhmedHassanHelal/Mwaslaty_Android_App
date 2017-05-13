package com.example.seko.mwaslaty.android.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.seko.mwaslaty.android.R;
import com.example.seko.mwaslaty.android.cells.AvailableStationCell;
import com.example.seko.mwaslaty.android.model.Station;

import java.util.List;

/**
 * Created by Helal on 06/05/2017.
 */
public class AvailableStationsAdapter extends ArrayAdapter<Station> {
    private final Activity activity;
    private final List<Station> availableStationsList;

    public AvailableStationsAdapter(Activity activity, int resource, List<Station> availableStationsList) {
        super(activity, resource, availableStationsList);
        this.activity = activity;
        this.availableStationsList = availableStationsList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        AvailableStationCell holder;
        if (null == row) {
            row = ((Activity) (activity)).getLayoutInflater().inflate(
                    R.layout.item_list_availavle_station, parent, false);
            holder = new AvailableStationCell();

            holder.setTvStationName((TextView) row.findViewById(R.id.tvAvailableStationName));

            row.setTag(holder);
        }
        holder = (AvailableStationCell) row.getTag();
        Station availableStation = availableStationsList.get(position);
        holder.getTvStationName().setText(availableStation.getName());
        return row;
    }

}

