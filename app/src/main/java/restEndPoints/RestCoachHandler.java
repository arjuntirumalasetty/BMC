package restEndPoints;

import android.os.AsyncTask;
import android.util.Log;

import java.util.concurrent.ExecutionException;

import businessojects.Coach;

/**
 * Created by dgup27 on 3/30/2017.
 */

public class RestCoachHandler {

    private RestCoachHandler(){}

    private static class SingletonHelper{
        private static final RestCoachHandler INSTANCE = new RestCoachHandler();
    }

    public static RestCoachHandler getInstance(){
        return SingletonHelper.INSTANCE;
    }

    public Coach registerCoach(Coach coach) {
        return callService("registerCoach", coach);
    }

    public Coach getCoach(Coach coach) {
        return callService("getCoach", coach);
    }

    public Coach updateCoachDetails(Coach coach) {
        return callService("updateCoachDetails", coach);
    }

    private Coach callService(String endPoint, Coach coach) {
        Coach returnCoach = null;
        try {
            RestCoachExecutor restCoachExecutor = new RestCoachExecutor();
            restCoachExecutor.setEndPoint(endPoint);
            restCoachExecutor.setCoach(coach);
            AsyncTask<Void, Void, Coach> returnObject = restCoachExecutor.execute();
            if(returnObject != null) {
                returnCoach = returnObject.get();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if(returnCoach == null) {
            Log.i("returnCoach", ">>returnCoach>>>> is null");
        } else {
            Log.i("returnCoach", ">>returnCoach>>>> is not null" +returnCoach.getLoginEmail());
        }
        return returnCoach;
    }

}
