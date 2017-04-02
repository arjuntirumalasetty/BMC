package businessojects;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by dgup27 on 3/15/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coach implements Serializable{

    private String id;
    private CoachDetails coachDetails;
    private String loginEmail;
    private String loginNumber;
    private String googleDisplayName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }

    public String getLoginNumber() {
        return loginNumber;
    }

    public void setLoginNumber(String loginNumber) {
        this.loginNumber = loginNumber;
    }

    public CoachDetails getCoachDetails() {
        return coachDetails;
    }

    public void setCoachDetails(CoachDetails coachDetails) {
        this.coachDetails = coachDetails;
    }

    public String getGoogleDisplayName() {
        return googleDisplayName;
    }

    public void setGoogleDisplayName(String googleDisplayName) {
        this.googleDisplayName = googleDisplayName;
    }
}