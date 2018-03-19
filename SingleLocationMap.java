package com.example.robpercival.jsondemo;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SingleLocationMap extends FragmentActivity implements OnMapReadyCallback {

    /*
    Clinton Bates
    Student Code 15703
     */


    private GoogleMap mMap;

    public double LatHolder;
    public double LngHolder;
    public String StationHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Get the Data.
        Intent intent = getIntent();

        //copy and pasted from last code for same reason
        String Lat = intent.getStringExtra("Lat");
        String Lng = intent.getStringExtra("Lng");
        StationHolder = intent.getStringExtra("StationTranfer");

        Log.i("Test:", Lat);

        LatHolder =  Double.parseDouble(Lat);
        LngHolder = Double.parseDouble(Lng);



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
        LatLng Dublin = new LatLng(LatHolder, LngHolder);
        mMap.addMarker(new MarkerOptions().position(Dublin).title(StationHolder).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Dublin, 15));
    }
}
