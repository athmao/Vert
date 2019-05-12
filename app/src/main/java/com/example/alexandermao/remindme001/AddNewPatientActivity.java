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

public class AddNewPatientActivity  extends AppCompatActivity {
    private GlobalVars globalVars;
    private String user;

    private String serverURL = "http://54.67.72.192/";
    private String registerURLSuffix = "add_patient?name=%1$s&birthday=%2$s&gender=%3$s&emergencyName=%4$s&emergencyPhone=%5$s&bio=%6$s&user=%7$s";

    private EditText name;
    private EditText gender;
    private EditText birthday;
    private EditText emergency;
    private EditText ephone;
    private EditText notes;

    private Button cancel;
    private Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_patient);

        this.name = findViewById(R.id.nametext);
        this.gender = findViewById(R.id.gendertext);
        this.birthday = findViewById(R.id.bdaytext);
        this.emergency = findViewById(R.id.ectext);
        this.ephone = findViewById(R.id.phonenumbertext);
        this.notes = findViewById(R.id.notestext);

        this.cancel = findViewById(R.id.cancelbutton);
        this.submit = findViewById(R.id.donebutton);

        globalVars = GlobalVars.getSingleInstance();
        user = globalVars.getUser();

        cancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                cancelCreate();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                createPatient();
            }
        });
    }

    public void cancelCreate() {
        NavUtils.navigateUpFromSameTask(this);
    }

    public void createPatient() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = String.format(serverURL + registerURLSuffix,
                name.getText().toString(), birthday.getText().toString(),
                gender.getText().toString(), emergency.getText().toString(),
                ephone.getText().toString(), notes.getText().toString(), this.user);

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
                "New Patient Added Successfully",
                Toast.LENGTH_SHORT);

        toast.show();

        NavUtils.navigateUpFromSameTask(this);
    }
}
