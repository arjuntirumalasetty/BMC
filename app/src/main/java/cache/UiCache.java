package cache;

import android.content.Context;

import java.io.IOException;

import businessojects.CoachDetails;
import businessojects.StadiumDetails;

/**
 * Created by Arjun on 4/1/2017.
 */

public interface UiCache {
    CoachDetails getCoachDetails() throws IOException, ClassNotFoundException;

    StadiumDetails getStadiumDetails();

    String getUserEmail();

    void writeCoach(Context context, Object object) throws IOException;

    void readCaoch(Context context) throws IOException, ClassNotFoundException;
}
