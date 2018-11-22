package com.example.alexandermao.remindme001;

import android.app.Application;

import java.util.HashMap;

public class GlobalVars extends Application {
    private HashMap<String, Patient> patients;
    private HashMap<String, Caretaker> caretakers;
    private HashMap<String, String> userVer;
    private HashMap<String, Caretaker> userToCare;
    private static GlobalVars singleInstance = null;

    public static GlobalVars getSingleInstance()
    {
        return singleInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleInstance = this;
        patients = new HashMap<>();
        caretakers = new HashMap<>();
        userVer = new HashMap<>();
        userToCare = new HashMap<>();
    }

    public HashMap<String, Patient> getPatients() {
        return patients;
    }

    public void setPatients(HashMap<String, Patient> patients) {
        this.patients = patients;
    }

    public HashMap<String, Caretaker> getCaretakers() {
        return caretakers;
    }

    public void setCaretakers(HashMap<String, Caretaker> caretakers) {
        this.caretakers = caretakers;
    }

    public HashMap<String, String> getUserVer() {
        return userVer;
    }

    public void setUserVer(HashMap<String, String> userVer) {
        this.userVer = userVer;
    }

    public HashMap<String, Caretaker> getUserToCare() {
        return userToCare;
    }

    public void setUserToCare(HashMap<String, Caretaker> userToCare) {
        this.userToCare = userToCare;
    }
}
