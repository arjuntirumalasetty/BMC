package businessojects;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dgup27 on 3/15/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coach {

    private String id;
    private CoachDetails coachDetails;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CoachDetails getCoachDetails() {
        return coachDetails;
    }

    public void setCoachDetails(CoachDetails coachDetails) {
        this.coachDetails = coachDetails;
    }
}