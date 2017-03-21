package businessojects;

import android.location.Address;

/**
 * Created by Arjun on 3/18/2017.
 */

public class StadiumDetails {
    private String stadiumName;
    private String sportName;
    private String timings;
    private String numberOfCourts;
    private Address address;

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getNumberOfCourts() {
        return numberOfCourts;
    }

    public void setNumberOfCourts(String numberOfCourts) {
        this.numberOfCourts = numberOfCourts;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

}
