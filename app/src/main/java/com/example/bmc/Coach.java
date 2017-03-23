package com.example.bmc;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dgup27 on 3/15/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coach {

    private String id;

    private String name;

    private String email;

    private String mobile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}