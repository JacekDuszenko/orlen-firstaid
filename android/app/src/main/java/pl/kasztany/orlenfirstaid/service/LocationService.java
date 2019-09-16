package pl.kasztany.orlenfirstaid.service;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class LocationService {

    private Activity activity;
    private TextView locationTv;
    private TextView latLongTv;
    private Location lastFirebasePersistableLocation;
    private FirebaseService firebaseService = new FirebaseService();

    private LatLongLocationCallback latLongLocationCallback;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private GeocoderService geocoderService;

    public LocationService(Activity activity, TextView locationTv, TextView latLongTv) {
        this.activity = activity;
        this.locationTv = locationTv;
        this.latLongTv = latLongTv;
        this.fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);
        this.latLongLocationCallback = new LatLongLocationCallback();
        this.geocoderService = new GeocoderService(activity);
    }

    public void registerLocationCallback(long interval) {
        if (checkPermission()) {
            return;
        }
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(interval);
        locationRequest.setFastestInterval(interval);
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, new LatLongLocationCallback(), Looper.myLooper());
    }

    public void unregisterLocationCallback() {
        trySendNotificationToFirebase();
        this.fusedLocationProviderClient.removeLocationUpdates(latLongLocationCallback);
    }

    private void trySendNotificationToFirebase() {
        if (lastFirebasePersistableLocation != null) {
            String address = geocoderService.findAddressByLatLongLocation(lastFirebasePersistableLocation);
            firebaseService.insertLocationToFirestore(lastFirebasePersistableLocation, address);
        }
    }

    private boolean checkPermission() {
        return activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED;
    }

    private class LatLongLocationCallback extends LocationCallback {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            updateLocation(locationResult);
            super.onLocationResult(locationResult);
        }

        private void updateLocation(LocationResult locationResult) {
            Location lastLocation = locationResult.getLastLocation();
            lastFirebasePersistableLocation = lastLocation;
            String address = geocoderService.findAddressByLatLongLocation(lastLocation);
            String latLongAsString = lastLocation.getLatitude() + ", " + lastLocation.getLongitude();
            latLongTv.setText(latLongAsString);
            locationTv.setText(address);
        }
    }
}
