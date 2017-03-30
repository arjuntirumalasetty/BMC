package com.example.bmc.globalvariable;

import android.app.Application;

import businessojects.Coach;

public class GlobalClass extends Application {
    
    private String loginEmail;
    private String loginNumber;
    private String googleProfileName;
    private Coach coach;

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

    public String getGoogleProfileName() {
        return googleProfileName;
    }

    public void setGoogleProfileName(String googleProfileName) {
        this.googleProfileName = googleProfileName;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }
}