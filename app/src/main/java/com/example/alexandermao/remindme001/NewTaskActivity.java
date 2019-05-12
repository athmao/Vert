package com.example.alexandermao.remindme001;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

public class NewTaskActivity  extends AppCompatActivity {
    private GlobalVars globalVars;
    private String user;
    private String currPatient;

    private String serverURL = "http://54.67.72.192/";
    private String newTaskURLSuffix = "add_task?patient=%1$s&item_name=%2$s&due_date=%3$s&notes=%4$s&caretakers=%5$s";

    private EditText name;
    private EditText due;
    private EditText notes;

    private Button cancel;
    private Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        this.name = findViewById(R.id.nametext);
        this.due = findViewById(R.id.duetext);
        this.notes = findViewById(R.id.notestext);

        this.cancel = findViewById(R.id.cancelbutton);
        this.submit = findViewById(R.id.donebutton);

        globalVars = GlobalVars.getSingleInstance();
        user = globalVars.getUser();
        currPatient = globalVars.getPatient();

        cancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                cancelCreate();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                createTask();
            }
        });
    }

    public void cancelCreate() {
        NavUtils.navigateUpFromSameTask(this);
    }

    public void createTask() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = String.format(serverURL + newTaskURLSuffix, currPatient,
                name.getText().toString(), due.getText().toString(),
                notes.getText().toString(), this.user);

        JsonObjectRequest loginJSON = new JsonObjectRequest(Request.Method.GET, url, new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        proceed();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });

        // Add the request to the RequestQueue.
        queue.add(loginJSON);
    }
    public void proceed() {
        Toast toast = Toast.makeText(getApplicationContext(),
                "New Task Added Successfully",
                Toast.LENGTH_SHORT);

        toast.show();

        NavUtils.navigateUpFromSameTask(this);
    }
}
