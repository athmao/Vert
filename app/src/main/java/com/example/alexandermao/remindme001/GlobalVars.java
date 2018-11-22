package com.example.alexandermao.remindme001;

import android.app.Application;

import java.util.Date;
import java.util.HashMap;

public class GlobalVars extends Application {
    private HashMap<String, Patient> patients;
    private HashMap<String, Caretaker> caretakers;
    private HashMap<String, String> userVer;
    private HashMap<String, Caretaker> userToCare;
    private static GlobalVars singleInstance = null;
    private Caretaker currentlyLoggedIn;

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
        currentlyLoggedIn = null;

        Caretaker tester = new Caretaker("Alex Mao", "test@gmail.com", "1-800-999-9999", "alex");
        userVer.put("alex", "1");
        userToCare.put("alex", tester);
        caretakers.put("Alex Mao", tester);

        Patient p1 = new Patient("Judith", "judy@gmail.com", "1", new Date(), "very old", "Female");
        Patient p2 = new Patient("Wilbur", "wilb@gmail.com", "2", new Date(), "very very old", "Male");
        Patient p3 = new Patient("We Tu Lo", "wtl@gmail.com", "3", new Date(), "very very very old", "Female");

        patients.put("Judith", p1);
        patients.put("Wilber", p2);
        patients.put("We Tu Lo", p3);
    }

    public Caretaker getCurrentlyLoggedIn() {
        return currentlyLoggedIn;
    }

    public void setCurrentlyLoggedIn(Caretaker currentlyLoggedIn) {
        this.currentlyLoggedIn = currentlyLoggedIn;
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
