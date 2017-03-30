package handlers;

import com.example.bmc.globalvariable.GlobalClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import businessojects.CoachDetails;
import businessojects.StadiumDetails;
import restEndPoints.RestCoachHandler;

/**
 * Created by Arjun on 3/28/2017.
 */

public class ProfileHandler {

    private static StadiumDetails stadiumDetails = null;
    private static List<StadiumDetails> stadiumList;
    private static List<String> stadiumNames;
    private static Map<String,List<String>>  stadiumDetailsMap;

    public static Map<String,List<String>> fillData(){
        stadiumNames = new ArrayList<String>();
        List<String> stadiumDetailsList= null;

        stadiumDetailsMap = new HashMap<String,List<String>>();
        stadiumDetailsList= new ArrayList<String>();
        stadiumDetails = stadiumList.get(0);
        stadiumNames.add(stadiumDetails.getStadiumName());
        stadiumDetailsList.add(stadiumDetails.getStadiumName());
        stadiumDetailsList.add(stadiumDetails.getNumberOfCourts());
        stadiumDetailsList.add(stadiumDetails.getSportName());
        stadiumDetailsList.add(stadiumDetails.getTimings());
        stadiumDetailsMap.put(stadiumDetails.getStadiumName(),stadiumDetailsList);
        return stadiumDetailsMap;
    }

    public static List<StadiumDetails> getStadiumList() {
        return stadiumList;
    }

    public static void setStadiumList(List<StadiumDetails> stadiumList) {
        ProfileHandler.stadiumList = stadiumList;
    }

    public static List<String> getStadiumNames() {
        return stadiumNames;
    }

    public static void setStadiumNames(List<String> stadiumNames) {
        ProfileHandler.stadiumNames = stadiumNames;
    }

    public static Map<String, List<String>> getStadiumDetailsMap() {
        return stadiumDetailsMap;
    }

    public static void setStadiumDetailsMap(Map<String, List<String>> stadiumDetailsMap) {
        ProfileHandler.stadiumDetailsMap = stadiumDetailsMap;
    }
/*
 * This method is used to call the service to persist the coachDetails
 */
    public static void persistProfileDetails(CoachDetails coachDetails, GlobalClass globalVariable) {
        globalVariable.getCoach().setCoachDetails(coachDetails);
        RestCoachHandler.getInstance().updateCoachDetails(globalVariable.getCoach());
    }
}
