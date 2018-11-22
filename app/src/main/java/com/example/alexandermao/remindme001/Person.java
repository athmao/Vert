package com.example.alexandermao.remindme001;

import java.net.MalformedURLException;
import java.net.URL;

public class Person {

    private String name;
    private URL profileImageURL;
    private String email;



    private String phonenumber;

    private final String defaultImageURL = "https://i.imgur.com/sL1iINP.png";

    public Person(String name, String email, String phonenumber) {
        this.name = name;
        try {
            this.profileImageURL = new URL(defaultImageURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.email = email;
        this.phonenumber = phonenumber;
    }

    public Person(String name, String email, String phonenumber, String profileImageURLString) {
        this.name = name;
        try {
            this.profileImageURL = new URL(profileImageURLString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.email = email;
        this.phonenumber = phonenumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getProfileImageURL() {
        return profileImageURL;
    }

    public void setProfileImageURL(String profileImageURLString) {
        try {
            this.profileImageURL = new URL(profileImageURLString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
