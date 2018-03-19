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

public class AllLocationsMap extends FragmentActivity implements OnMapReadyCallback {
    /*
        Clinton Bates
        Student Code 15703
         */
    private GoogleMap mMap;

    public double LatHolder;
    public double LngHolder;
    public String StationHolder;
    public  String[] myStrings = new String[100];


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

    @Override
    public void onMapReady(GoogleMap googleMap) {

        Bundle b=this.getIntent().getExtras();
        String[] array=b.getStringArray("Lat i");
        String[] array2=b.getStringArray("Lng i");
        String[] array3=b.getStringArray("Name");
        //checking it came
        for (int i = 0; i < 100; i++) {
            Log.i("Test", array[i]);
            Log.i("Test2", array2[i]);
            Log.i("Test2", array3[i]);
        }

        mMap = googleMap;
        for (int i = 0; i < 100; i++) {
            LatLng Dublin = new LatLng(Double.parseDouble(array[i]), Double.parseDouble(array2[i]));
            mMap.addMarker(new MarkerOptions().position(Dublin).title(array3[i]).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Dublin, 15));
        }
    }
}
