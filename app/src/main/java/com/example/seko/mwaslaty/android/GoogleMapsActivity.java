package com.example.seko.mwaslaty.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.seko.mwaslaty.android.model.Station;
import com.example.seko.mwaslaty.android.view.IGoogleMapsView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * Created by Helal
 */

public class GoogleMapsActivity extends FragmentActivity implements IGoogleMapsView, OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;

    private List<Station> stations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        Bundle bundle = getIntent().getExtras();
        stations = (List<Station>) bundle.get(getResources().getString(
                R.string.all_stations_key));
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng cairo = new LatLng(30.0444, 31.2357);
//        mMap.addMarker(new MarkerOptions().position(cairo).title("Marker in Cairo"));

        putAllMarkersOnStations();

        mMap.setOnMarkerClickListener(this);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cairo, (float) 15.0));
    }

    private void putAllMarkersOnStations() {
        for (Station station : stations) {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(station.getLatitude(), station.getLongitude()));
            markerOptions.title(station.getName());
            markerOptions.snippet(String.valueOf(station.getID()));
            mMap.addMarker(markerOptions);

        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Intent returnIntent = new Intent();
        Bundle bundle = new Bundle();
        int id = Integer.parseInt(marker.getSnippet()) - 1;
        bundle.putSerializable(getResources().getString(
                R.string.res_station_key), stations.get(id));
        returnIntent.putExtras(bundle);
        setResult(RESULT_OK, returnIntent);
        finish();
        return false;
    }
}
