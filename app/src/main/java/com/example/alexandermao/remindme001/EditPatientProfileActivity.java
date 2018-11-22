package com.example.alexandermao.remindme001;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/*
    Should allow you to edit patient profile
    1. Needs back button and submit button
    2. Should update patient object
 */
public class EditPatientProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_patient_profile);
    }
}
