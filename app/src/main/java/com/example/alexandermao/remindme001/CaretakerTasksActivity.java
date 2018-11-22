package com.example.alexandermao.remindme001;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

/* This activity is part of the tablayout

    1. Should contain a list of tasks in chronological order. Tasks should be sorted in priority queue in chronological order already
    2. Should display tasks, finished tasks should be gray, new tasks should be colored. Only display completed tasks within the day. Use java.util.Date.
    3. Clicking the task should take us to TaskViewActivity. Hitting the checkmark by the tasks should set task to completed and turn it gray.
    4. Clicking new task should take us to NewTaskActivity. Current caretaker should be defaulted as the caretaker for the new task.
    5. User should be able to scroll up and down to view all tasks.


 */
public class CaretakerTasksActivity extends AppCompatActivity {

    private Caretaker caretaker;
    private HashMap<String, Patient> patients;
    private HashMap<String, Caretaker> caretakers;
    public CaretakerTasksActivity(Caretaker caretaker, HashMap<String, Patient> patients, HashMap<String, Caretaker> caretakers) {
        this.caretaker = caretaker;
        this.patients = patients;
        this.caretakers = caretakers;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caretaker_tasks);
    }
}