package com.example.owner.itinerick3;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FragmentMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<String> dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent intent = getIntent();
        dataset = intent.getStringArrayListExtra(ActivityLocation.LIST_OF_PLACES);
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
        final String COUNTRY = " Singapore";
        mMap = googleMap;
        UiSettings settings = mMap.getUiSettings();
        settings.setAllGesturesEnabled(true);
        settings.setMyLocationButtonEnabled(true);
        settings.setZoomControlsEnabled(true);
        settings.setCompassEnabled(true);
        settings.setMapToolbarEnabled(true);
        LatLng sg = new LatLng(1.352083, 103.819836);
        if (dataset.isEmpty()) {
            // Add a marker in Sydney and move the camera

            mMap.addMarker(new MarkerOptions().position(sg).title("Marker in Singapore"));

        } else {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses;
            ArrayList<Marker> markers = new ArrayList<>();
            for (String location: dataset) {
                try {
                    addresses = geocoder.getFromLocationName(location + COUNTRY, 1);
                    Address address = addresses.get(0);
                    LatLng coordinates = new LatLng(address.getLatitude(), address.getLongitude());
                    Marker marker = mMap.addMarker(new MarkerOptions().position(coordinates).title("Marker of " + location));
                    markers.add(marker);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sg));
            mMap.moveCamera(CameraUpdateFactory.zoomTo(12.0f));

        }
    }
}
