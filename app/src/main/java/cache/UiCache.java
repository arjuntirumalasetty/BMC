package cache;

import java.io.IOException;

import businessojects.CoachDetails;
import businessojects.StadiumDetails;

/**
 * Created by Arjun on 4/1/2017.
 */

public interface UiCache {
    String getUserEmail();
    StadiumDetails getStadiumDetails();
    CoachDetails getCoachDetails() throws IOException, ClassNotFoundException;
}
