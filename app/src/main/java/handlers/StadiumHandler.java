package handlers;

import android.util.Log;

import businessojects.StadiumDetails;

/**
 * Created by Arjun on 3/31/2017.
 */

public class StadiumHandler {

    public static void submitStadiumDetails(StadiumDetails newStadiumDetails){
        Log.i(newStadiumDetails.getStadiumName(),newStadiumDetails.getSportName());
    }
}
