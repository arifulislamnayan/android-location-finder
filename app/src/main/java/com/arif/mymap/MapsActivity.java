package com.arif.mymap;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        //tv= (TextView)findViewById(R.id.textView);
        showMap();
    }




    private void showMap() {
            try {

                if (mMap == null) {

                    mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                            .getMap();

                }

                if (mMap != null) {
                    setUpMap();
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
    }
    private void setUpMap()
    {

        mMap.setMyLocationEnabled(true);
        LocationManager locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();

        String provider = locationManager.getBestProvider(criteria, true);
        Location mylocation = locationManager.getLastKnownLocation(provider);
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        double latitude = mylocation.getLatitude();
        double longitude = mylocation.getLongitude();
        Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        LatLng latLng = new LatLng(latitude, longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(60));
        //tv.setText(String.valueOf(latitude));
        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("you are here").icon(BitmapDescriptorFactory.fromResource(R.drawable.android_icon)));
        }

    }



