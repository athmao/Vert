package com.example.alexandermao.remindme001;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/*
    In this activity, one should be able to view all the information associated with a task.
    1. There should be an edit button to edit the task
    2. There should be a back button to return to where you came from (can come from different places, important)
 */
public class TaskViewActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_view);
    }
}
