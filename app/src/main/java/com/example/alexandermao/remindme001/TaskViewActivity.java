package com.example.alexandermao.remindme001;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*
    In this activity, one should be able to view all the information associated with a task.
    1. There should be an edit button to edit the task
    2. There should be a back button to return to where you came from (can come from different places, important)
 */
public class TaskViewActivity extends AppCompatActivity{
    private JSONObject task;
    private Button delete;
    private Button edit;
    private Button cancel;
    private Button complete;
    private TextView taskname;
    private TextView due;
    private TextView notes;
    private GlobalVars globalVars;
    private String currPatient;
    private String tasknameString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_view);
        Intent intent = getIntent();
        delete = (Button) findViewById(R.id.deletebutton);
        edit = (Button) findViewById(R.id.editButton);
        cancel = (Button) findViewById(R.id.cancelbutton);
        complete = (Button) findViewById(R.id.donebutton);
        taskname = (TextView) findViewById(R.id.taskname);
        due = (TextView) findViewById(R.id.duelabel2);
        notes = (TextView) findViewById(R.id.noteslabel2);
        globalVars = GlobalVars.getSingleInstance();
        currPatient = globalVars.getPatient();
        try {
            task = new JSONObject(intent.getStringExtra("task"));
            tasknameString = task.getString("name");
            taskname.setText(tasknameString);
            due.setText(task.getString("due"));
            notes.setText(task.getString("notes"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTask();

            }
        });

        complete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                completeTask();

            }
        });

        edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                editTask();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cancel();
            }
        });

    }

    public void deleteTask() {
        removeTaskFromServer();
        Toast toast = Toast.makeText(getApplicationContext(),
                "Task Deleted",
                Toast.LENGTH_SHORT);

        toast.show();

        NavUtils.navigateUpFromSameTask(this);

    }

    public void completeTask() {
        removeTaskFromServer();
        Toast toast = Toast.makeText(getApplicationContext(),
                "Task completed",
                Toast.LENGTH_SHORT);

        toast.show();

        NavUtils.navigateUpFromSameTask(this);

    }

    public void removeTaskFromServer() {
        String patientListURL = String.format("http://54.67.72.192/delete_task?patient=%1$s&item_name=%2$s", currPatient, tasknameString);
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest patientsJSON = new JsonArrayRequest(Request.Method.GET, patientListURL, new JSONArray(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Display the first 500 characters of the response string.
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });
        queue.add(patientsJSON);
    }
    public void editTask() {
        //editTask()
        Intent intent = new Intent(this, EditTaskActivity.class);
        Bundle b = new Bundle();
        b.putString("task", task.toString());
        intent.putExtras(b);
        startActivity(intent);
    }

    public void cancel() {
        NavUtils.navigateUpFromSameTask(this);
    }
}
