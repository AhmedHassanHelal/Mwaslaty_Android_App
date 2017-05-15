package com.example.seko.mwaslaty.android;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.seko.mwaslaty.android.model.Solution;
import com.example.seko.mwaslaty.android.model.Station;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

/**
 * Created by Helal
 */


public class DrawSolutionOnGoogleMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    private List<Station> stations;
    private Station sourceStation;
    private Solution solution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_solution_on_google_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Bundle bundle = getIntent().getExtras();
        stations = (List<Station>) bundle.getSerializable(getResources().getString(R.string.all_stations_key));
        sourceStation = (Station) bundle.getSerializable(getResources().getString(R.string.source_station_key));
        solution = (Solution) bundle.getSerializable(getResources().getString(R.string.solution_key));
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    private Station getStationById(int id) {
        for (Station station : stations) {
            if (station.getID() == id)
                return station;
        }
        return null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        String transportation;
        if (solution.getCheckpoints().get(0).getBusID() == null)
            transportation = "مترو ";
        else
            transportation = "اتوبيس: " + solution.getCheckpoints().get(0).getBusID().toString();
        String msg = "اركب " + transportation + "من محطة: " + sourceStation.getName().toString();
        mMap.addMarker(new MarkerOptions().position(new LatLng(sourceStation.getLatitude(), sourceStation.getLongitude())).title(msg));
        Station station;
        PolylineOptions rectOptions = new PolylineOptions()
                .add(new LatLng(sourceStation.getLatitude(), sourceStation.getLongitude()));
        for (int i = 1; i < solution.getCheckpoints().size(); i++) {
            station = getStationById(Integer.parseInt(solution.getCheckpoints().get(i - 1).getStationID()));
            if (solution.getCheckpoints().get(i).getBusID() == null)
                transportation = "مترو ";
            else
                transportation = "اتوبيس: " + solution.getCheckpoints().get(i).getBusID().toString();
            msg = "انزل محطة: " + station.getName().toString() + "واركب: " + transportation;
            mMap.addMarker(new MarkerOptions().position(new LatLng(station.getLatitude(), station.getLongitude())).title(msg));
            rectOptions.add(new LatLng(station.getLatitude(), station.getLongitude()));
        }
        station = getStationById(Integer.parseInt(solution.getCheckpoints().get(solution.getCheckpoints().size() - 1).getStationID()));
        msg = "انزل محطة: " + station.getName().toString() + " حمد الله ع السلامة :)";
        mMap.addMarker(new MarkerOptions().position(new LatLng(station.getLatitude(), station.getLongitude())).title(msg));
        rectOptions.add(new LatLng(station.getLatitude(), station.getLongitude()));
        Polyline polyline = mMap.addPolyline(rectOptions);
        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
