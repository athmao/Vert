package com.example.alexandermao.remindme001;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class NewTaskActivity extends AppCompatActivity{
    /*
        This creates a new task
        1. Should have back button to come back from where you came
        2. Caretaker for the task should default to current user
        3. Patient for the task should default to the patient you add a task to IF you are adding task from PatientTaskActivity
        4. After selecting a patient, one should be able to choose another chartaker from list of patient's caretakers
        5. Tasks only have 1 chartaker, remember!
        6. After submitting a task, it should be added to priorityqueue of tasks in the caretaker and patient object involved
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
    }
}
