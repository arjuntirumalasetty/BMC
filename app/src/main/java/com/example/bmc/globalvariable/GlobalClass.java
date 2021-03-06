package com.example.bmc.globalvariable;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.bmc.R;

import java.io.IOException;

import businessojects.Coach;
import cache.UICacheImpl;

public class GlobalClass extends Application {
    
    private String loginEmail;
    private String loginNumber;
    private String googleProfileName;
    private Coach coach;
    private String logout;
    private static Context context;
    SharedPreferences sharedPreferences;
    private static final String REG_USER = "reg";
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initCache();
    }


    private void initCache() {
        boolean isPresentInServer = false;
        UICacheImpl cache = UICacheImpl.getInstance(this);
        try {
            sharedPreferences = getApplicationContext().getSharedPreferences(REG_USER,Context.MODE_PRIVATE);
            String userEmailKey = getResources().getString(R.string.email_id);
            String userEmail = sharedPreferences.getString(getString(R.string.email_id),userEmailKey);
            if(userEmail!= null && !userEmail.equals(userEmailKey)){
                cache.readCaoch(this);
            }
        } catch (IOException e) {
            Log.e("Exception in intiCache", e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            Log.e("Exception in intiCache", e.getMessage());
            e.printStackTrace();
        }
    }

    public static Context getContext() {
        return context;
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

    public String getLogout() {
        return logout;
    }

    public void setLogout(String logout) {
        this.logout = logout;
    }
}