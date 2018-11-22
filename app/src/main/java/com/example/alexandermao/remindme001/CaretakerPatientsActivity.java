package com.example.alexandermao.remindme001;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

/*
    This Activity shows all the patients of a chartaker
    1. One should see all the patients a caretaker has
    2. Clicking a patient should take you to PatientView
    3. Clicking add patient should allow you to choose from database of patients, and add patient to chartaker's patient hashmap

 */
public class CaretakerPatientsActivity extends AppCompatActivity {

    private Caretaker caretaker;
    private HashMap<String, Patient> patients;
    private HashMap<String, Caretaker> caretakers;

    public CaretakerPatientsActivity(Caretaker caretaker, HashMap<String, Patient> patients, HashMap<String, Caretaker> caretakers) {
        this.caretaker = caretaker;
        this.patients = patients;
        this.caretakers = caretakers;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caretaker_patients);
    }
}
