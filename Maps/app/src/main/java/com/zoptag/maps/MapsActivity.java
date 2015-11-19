package com.zoptag.maps;

import android.content.IntentSender;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements  GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,LocationListener, OnMapReadyCallback {

    public static GoogleMap myMap;
    private GoogleApiClient myApiClient;
    private Location location;
    private LocationRequest myLocationRequest;
    public static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 7000;
    LatLng latLng;
    LatLng locations;
    double currentLongitude, currentLatitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment which notified when the map is ready to used
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        myApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();


        // Create the LocationRequest object
        myLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)//for accurate position
                .setInterval(10 * 1000)        // for 10 seconds
                .setFastestInterval(1 * 1000); // for 1 second
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i("TAG", "Location Service connected");
        location = LocationServices.FusedLocationApi.getLastLocation(myApiClient);

        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(myApiClient, myLocationRequest, this);
        } else {
            handleNewLocation(location);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("TAG", "Location Service not connected.  please connected...");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution())
        {
            try
            {
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            }
            catch (IntentSender.SendIntentException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            Log.i("TAG", "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        myApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (myApiClient.isConnected())
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(myApiClient, this);
            myApiClient.disconnect();
        }
    }

    private void setUpMapIfNeeded() {
        if (myMap == null)
        {
            myMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            if (myMap != null)
            {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        myMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    private void handleNewLocation(Location location) {
        Log.d("TAG", location.toString());

        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();

        latLng = new LatLng(currentLatitude, currentLongitude);

        MarkerOptions options = new MarkerOptions().position(latLng).title("I am here!");
        myMap.addMarker(options);
        myMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(location);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        List list= new ArrayList();
        myMap = googleMap;
        String jsonString = null;
        JSONObject jsonObject = null;
        InputStream stream =null;
        PolylineOptions polylineOptions =new PolylineOptions();

        try {
            /*open the json file */
            stream = getAssets().open("Locations.json");
            int size = stream.available();

            byte [] bytes = new byte[size];
            stream.read(bytes);

            jsonString = new String(bytes);
            jsonObject = new JSONObject(jsonString);

            /*itrate the jsonarray*/
            JSONArray jsonArray = jsonObject.getJSONArray("locations");
            for(int i=0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);

                /*to read and store the value*/
                String store_name = jsonObject.optString("store_name").toString();
                double lat = Double.parseDouble(jsonObject.optString("latitude").toString());
                double lag = Double.parseDouble(jsonObject.optString("longitude").toString());

                /*to add marker in map*/
                locations = new LatLng(lat, lag);
                myMap.addMarker(new MarkerOptions().position(locations).title(store_name).draggable(true));
                myMap.moveCamera(CameraUpdateFactory.newLatLng(locations));
                list.add(locations);
               // String url = getDirectionsUrl(latLng, location);


            }
            polylineOptions.addAll(list);
            polylineOptions.width(5);
            polylineOptions.color(Color.BLUE);
            myMap.addPolyline(polylineOptions);
            String url = getDirectionsUrl(latLng, locations);
            new RoutePrint().execute(url);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (stream != null)
                {
                    stream.close();
                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private String getDirectionsUrl(LatLng origin,LatLng dest) {
        String str_origin = "origin=" + /*origin.latitude*/ currentLatitude+ "," + currentLongitude/*origin.longitude*/;
        String str_dest = "destination=" + dest.latitude/*12.969534*/ + "," +/*77.638589*/ dest.longitude;

        String parameters = str_origin + "&" + str_dest + "&" + "&sensor=false";

        String url = "https://maps.googleapis.com/maps/api/directions/json?"  + parameters;

        return url;
    }


}
