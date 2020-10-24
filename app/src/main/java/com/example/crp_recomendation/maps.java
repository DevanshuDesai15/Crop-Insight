package com.example.crp_recomendation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.data.geojson.GeoJsonFeature;
import com.google.maps.android.data.geojson.GeoJsonLayer;
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class maps extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,LocationListener {
    LocationManager locationManager;
    LocationListener locationListener;
    //private static final int PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 2;
    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentUserLocationMarker;
    private static final int Request_User_Location_Code=99;
    //private FusedLocationProviderClient mFusedLocationClient;
    // private boolean mLocationPermissiongranted = false;
    //private GeoPoint location;
    private String Imageref;
    private Button button;
    private String optionSelected;
    String myCity = " ";
    String myState = " ";



    String Gujarat = "Gujarat";
    //LatLng curr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        button = (Button)findViewById(R.id.button);




        button.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View v) {
                if(myState.equals(Gujarat)) {
                    opencropactivity();
                }
                else if (myState.equals(" ")){
                    opencropactivity();
                }
                else{
                    openerroractivity();
                }
            }
        });


        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            checkUserLocationPermission();
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    public void opencropactivity(){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("district",myCity);
        startActivity(intent);
    }
    public void openerroractivity(){

        Toast.makeText(this, "You are of "+myState+"\nUser must be of Gujarat", Toast.LENGTH_SHORT).show();
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
        // mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
        {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

        LatLng gujarat = new LatLng(22.2587, 71.1924);
        //  mMap.addMarker(new MarkerOptions().position(gujarat).title("Marker in Sydney"));
        float zoomLevel = 6.5f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gujarat, zoomLevel));


        // LatLng curr;
        // Add a marker in Sydney and move the camera
        // LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        // getlastlocation();
        GeoJsonLayer layer = null;
        try {
            layer = new GeoJsonLayer(mMap,R.raw.zip_codes,getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        layer.addLayerToMap();
        for (GeoJsonFeature feature : layer.getFeatures()) {

            //Initialise polygon style
            GeoJsonPolygonStyle polygonStyle = new GeoJsonPolygonStyle();


            switch (feature.getProperty("Zipcode")) {
                case "1":
                    polygonStyle.setFillColor(Color.YELLOW);
                    feature.setPolygonStyle(polygonStyle);
                    break;
                case "2":
                    polygonStyle.setFillColor(Color.BLUE);
                    feature.setPolygonStyle(polygonStyle);
                    break;
                case "3":
                    polygonStyle.setFillColor(Color.MAGENTA);
                    feature.setPolygonStyle(polygonStyle);
                    break;
                case "7":
                    polygonStyle.setFillColor(Color.rgb(144,238,144));
                    feature.setPolygonStyle(polygonStyle);
                    break;
                case "6":
                    polygonStyle.setFillColor(Color.rgb(50,205,50));
                    feature.setPolygonStyle(polygonStyle);
                    break;
                case "5":
                    polygonStyle.setFillColor(Color.rgb(255,165,0));
                    feature.setPolygonStyle(polygonStyle);
                    break;
                case "4":
                    polygonStyle.setFillColor(Color.rgb(255,182,193));
                    feature.setPolygonStyle(polygonStyle);
                    break;
                case "8":
                    polygonStyle.setFillColor(Color.rgb(255,20,147));
                    feature.setPolygonStyle(polygonStyle);
                    break;

            }
        }
    }


    public boolean checkUserLocationPermission(){
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_User_Location_Code);
            }
            else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_User_Location_Code);
            }

            return  false;
        }
        else{
            return  true;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case Request_User_Location_Code:
                if(grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
                    {
                        if(googleApiClient == null){
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else {
                    Toast.makeText(this, "Permission Denied...", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

    protected synchronized void buildGoogleApiClient(){
        googleApiClient= new GoogleApiClient.Builder(this).
                addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }



    @Override
    public void onLocationChanged(Location location) {
        lastLocation = location;
        if(currentUserLocationMarker!=null){
            currentUserLocationMarker.remove();

        }

        LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("current location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

        currentUserLocationMarker = mMap.addMarker(markerOptions);

        // mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        // mMap.animateCamera(CameraUpdateFactory.zoomBy(2));
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng,
                8);
        mMap.moveCamera(update);

        if(googleApiClient !=null){
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient,this);
        }
        String cityName = getCityName(latLng)[0];
        String stateName = getCityName(latLng)[1];
        Toast.makeText(getApplicationContext(),cityName,Toast.LENGTH_LONG).show();
        myState = stateName;
    }

    public String[] getCityName(LatLng latLng) {

        Geocoder geocoder = new Geocoder(maps.this,Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
            String address = addresses.get(0).getAddressLine(0);
            myCity = addresses.get(0).getSubAdminArea();
            myState = addresses.get(0).getAdminArea();
            //myState = "Mahrashtra";
            Log.d("mylog","complete address" + addresses.toString());
            Log.d("myLog","Address"+address);
            Log.d("mycity","district"+myCity);
            Log.d("myState","state"+myState);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String arr[] = new String[2];
        arr[0] = myCity;
        arr[1] = myState;
        return arr;
        //   return myCity;


    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest=new LocationRequest();
        locationRequest.setInterval(1100);
        locationRequest.setFastestInterval(1100);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient,locationRequest,this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}