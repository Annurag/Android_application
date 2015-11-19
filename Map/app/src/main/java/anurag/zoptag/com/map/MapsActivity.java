package anurag.zoptag.com.map;

import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static GoogleMap mMap;
    ArrayList<LatLng> markerPoints;
    private static final String LOG_TAG = "JsOn ErRoR";

    private static final String SERVICE_URL = "";
    LocationManager locationManager;
    Criteria criteria;
    String provider;
    Location location;
    final StringBuilder json = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        markerPoints = new ArrayList<LatLng>();

        SupportMapFragment fm = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);

        mMap = fm.getMap();
        mMap.setMyLocationEnabled(true);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {

                if (markerPoints.size() > 1) {
                    markerPoints.clear();
                    mMap.clear();
                }

                markerPoints.add(point);

                MarkerOptions options = new MarkerOptions();

                options.position(point);

                if (markerPoints.size() == 1) {
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                } else if (markerPoints.size() == 2) {
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                }

                mMap.addMarker(options);

                if (markerPoints.size() >= 2) {
                    LatLng origin = markerPoints.get(0);
                    LatLng dest = markerPoints.get(1);

                    String url = getDirectionsUrl(origin, dest);

                    DownloadTask downloadTask = new DownloadTask();

                    downloadTask.execute(url);
                }
            }
        });
    }

    private String getDirectionsUrl(LatLng origin,LatLng dest){


        String str_origin = "origin="+origin.latitude+","+origin.longitude;


        String str_dest = "destination="+dest.latitude+","+dest.longitude;


        String sensor = "sensor=false";


        String parameters = str_origin+"&"+str_dest+"&"+sensor;


        String output = "json";

        String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;

        return url;
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng location = new LatLng(12.969534, 77.638589);

        mMap.addMarker(new MarkerOptions().position(location).title("The Orange ").draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));

        LatLng location1 = new LatLng(13.009485, 77.563375);
        mMap.addMarker(new MarkerOptions().position(location1).title("Soch").draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location1));

        LatLng location2 = new LatLng(12.970925, 77.654095);
        mMap.addMarker(new MarkerOptions().position(location2).title("Nauchandi").draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location2));

        LatLng location3 = new LatLng(12.931713, 77.62835);
        mMap.addMarker(new MarkerOptions().position(location3).title("Kudoz Studio").draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location3));


        PolylineOptions polyline =new PolylineOptions().width(5).color(Color.RED).add(
                new LatLng(12.969534, 77.638589),
                new LatLng(13.009485, 77.563375), new LatLng(12.970925, 77.654095), new LatLng(12.931713, 77.62835)
        );


        mMap.addPolyline(polyline);
    }

}




