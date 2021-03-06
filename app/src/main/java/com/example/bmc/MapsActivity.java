package com.example.bmc;

import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int MY_REQUEST_ACCESS_COARSE_LOCATION = 0;
    private GoogleMap mMap;
    private String ENABLE_PIN;
    private static final int MY_REQUEST_ACCESS_FINE_LOCATION =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if(getIntent().getStringExtra("ENABLE_PIN")!=null){
            ENABLE_PIN = getIntent().getStringExtra("ENABLE_PIN");

        }
    }

    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(latLng));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        Log.i("Longitude ",longitude+"");
        Log.i("Latitude ",latitude+"");
    }

    public void getCurrentLocation(){
        try{
            if (mMap != null) {

                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_REQUEST_ACCESS_FINE_LOCATION);
                    ActivityCompat.requestPermissions(this,
                            new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                            MY_REQUEST_ACCESS_COARSE_LOCATION);
                    Log.i("ACCESS_COARSE_LOCATION",MY_REQUEST_ACCESS_COARSE_LOCATION+"");
                } else{
                    mMap.setMyLocationEnabled(true);
                    LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                    Criteria criteria = new Criteria();
                    String bestLocationProvider = locationManager.getBestProvider(criteria,true);
                    Location location = locationManager.getLastKnownLocation(bestLocationProvider);
                    if (location != null) {
                        onLocationChanged(location);
                    }
                    locationManager.requestLocationUpdates(bestLocationProvider, 20000, 0, (LocationListener) this);
                }
            }
        }catch(Exception e){
            Log.e("Error in getLoccation",e.getMessage());
        }

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
        try {
            mMap = googleMap;
            getCurrentLocation();
        }catch(Exception e){
            Log.e("Error in onMapReady ",e.getMessage());
        }
        // Add a marker in Sydney and move the camera
    }
}
