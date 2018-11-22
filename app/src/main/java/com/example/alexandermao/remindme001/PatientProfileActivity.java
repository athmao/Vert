package com.example.alexandermao.remindme001;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/*
    View profile of the patient
    1. Should simply display information
    2. Should have an edit button which takes you to EditPatientProfileActivity
 */
public class PatientProfileActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_profile);
    }

}
