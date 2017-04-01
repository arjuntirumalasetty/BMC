package cache;

import android.location.Address;

import java.util.Locale;

import businessojects.StadiumDetails;

/**
 * Created by Arjun on 3/18/2017.
 */

public class UICache {
    public StadiumDetails getStadiumDetails(){
        StadiumDetails stadiumDetails = new StadiumDetails();
        stadiumDetails.setStadiumName("Balewadi Stadium");
        stadiumDetails.setSportName("Tennis");
        stadiumDetails.setNumberOfCourts("3");
     //   stadiumDetails.setTimings("6:00 to 9:00");
        stadiumDetails.setAddress(getAddress());
        return stadiumDetails;
    }

    public Address getAddress() {
        Address address = new Address(Locale.getDefault());
        address.setAddressLine(1,"Balewadi Stadium");
        address.setAdminArea("Balewadi");
        address.setCountryCode("123");
        address.setCountryName("India");
        return address;
    }
}
