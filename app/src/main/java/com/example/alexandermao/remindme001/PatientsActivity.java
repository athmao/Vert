package com.example.alexandermao.remindme001;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/*
    This is what you see when you go to a patient, should be similar to caretaker activity so there can be overlap
   1. Tab Layout for Patient's tasks, and patient info
   2. Task tab has PatientTasksActivity
   3. Patients tab has PatientProfileActivity
 */
public class PatientsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients);
    }
}
