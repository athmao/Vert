package com.example.alexandermao.remindme001;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class EditTaskActivity extends AppCompatActivity{
    private GlobalVars globalVars;
    private String user;
    private String currPatient;

    private String serverURL = "http://54.67.72.192/";
    private String newTaskURLSuffix = "update_task?patient=%1$s&item_name=%2$s&due_date=%3$s&notes=%4$s&caretakers=%5$s";

    private TextView name;
    private EditText due;
    private EditText notes;

    private Button cancel;
    private Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        Bundle b = getIntent().getExtras();


        this.name = findViewById(R.id.taskname);
        this.due = findViewById(R.id.duelabel2);
        this.notes = findViewById(R.id.noteslabel2);

        try {
            JSONObject task = new JSONObject(b.getString("task"));
            name.setText(task.getString("name"));
            due.setText(task.getString("due"));
            notes.setText(task.getString("notes"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
                updateTask();
            }
        });
    }

    public void cancelCreate() {
        NavUtils.navigateUpFromSameTask(this);
    }

    public void updateTask() {
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
                System.out.println("ERROR HERE");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(loginJSON);
    }
    public void proceed() {
        Toast toast = Toast.makeText(getApplicationContext(),
                "Task Updated Successfully",
                Toast.LENGTH_SHORT);

        toast.show();

        NavUtils.navigateUpFromSameTask(this);
    }
}
