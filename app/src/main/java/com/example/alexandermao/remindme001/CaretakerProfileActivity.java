package com.example.alexandermao.remindme001;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

/* This class is how the caretaker edits his profile
    1. Needs to have ways to edit all caretaker fields and submit, except for patients and tasks.
    3. Submitting should bring back to CaretakerActivity with updated information
    2. Needs back button to bring back to CaretakerActivity
 */
public class CaretakerProfileActivity extends AppCompatActivity {

    private Caretaker caretaker;
    private HashMap<String, Patient> patients;
    private HashMap<String, Caretaker> caretakers;
    public CaretakerProfileActivity(Caretaker caretaker, HashMap<String, Patient> patients, HashMap<String, Caretaker> caretakers) {
        this.caretaker = caretaker;
        this.patients = patients;
        this.caretakers = caretakers;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caretaker_profile);
    }
}
