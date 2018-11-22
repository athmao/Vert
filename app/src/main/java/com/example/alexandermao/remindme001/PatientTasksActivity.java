package com.example.alexandermao.remindme001;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class PatientTasksActivity extends AppCompatActivity {

    /*
        Tasks for the patient.
        1. Same requirements as CaretakerTasksActivity, except adding a task defaults the patient of the task to current patient viewed
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_tasks);
    }
}
