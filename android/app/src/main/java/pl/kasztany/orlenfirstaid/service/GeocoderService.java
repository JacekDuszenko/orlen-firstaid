package pl.kasztany.orlenfirstaid.service;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static pl.kasztany.orlenfirstaid.util.Constants.LOCATION_FINDING_IN_PROGRESS;

class GeocoderService {
    private Geocoder geocoder;

    GeocoderService(Activity activity) {
        this.geocoder = new Geocoder(activity, Locale.getDefault());
    }

    String findAddressByLatLongLocation(Location location) {
        String address = tryFindAddress(location);

        if (address == null || address.equals("")) {
            return LOCATION_FINDING_IN_PROGRESS;
        }
        return address;
    }

    private String tryFindAddress(Location location) {
        try {
            return findAddress(location);
        } catch (IOException e) {
            Log.e("EXCEPTION", "Exception thrown while finding geolocation by latlong data");
            return null;
        }
    }

    private String findAddress(Location location) throws IOException {
        List<Address> listOfAddresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        if (listOfAddresses.size() == 0) {
            return null;
        }

        return getFirstAddressLine(listOfAddresses);
    }

    private String getFirstAddressLine(List<Address> listOfAddresses) {
        Address address = listOfAddresses.get(0);
        return address.getAddressLine(0);
    }
}
