package com.example.alexandermao.remindme001;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

/*
    View profile of the patient
    1. Should simply display information
    2. Should have an edit button which takes you to EditPatientProfileActivity
 */
public class PatientProfileActivity extends Fragment {

    private View view;
    private GlobalVars globalVars;
    private String user;
    private String currPatient;
    private Button edit;
    private ArrayList<JSONObject> tasks;

    private String serverURL = "http://54.67.72.192/";
    private String patientListSuffix = "get_user_patients?user=%s";

    private JSONObject patient;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.activity_patients_profile, container, false);


        globalVars = GlobalVars.getSingleInstance();
        user = globalVars.getUser();
        currPatient = globalVars.getPatient();

        edit = (Button) view.findViewById(R.id.editPatient);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditPatientProfileActivity.class);
                startActivity(intent);
            }
        });

        String patientListURL = String.format(serverURL + patientListSuffix, user);
        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        JsonArrayRequest patientsJSON = new JsonArrayRequest(Request.Method.GET, patientListURL, new JSONArray(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Display the first 500 characters of the response string.
                        getPatientInfo(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });
        queue.add(patientsJSON);
        return view;
    }

    public void getPatientInfo(JSONArray patientsJSON) {
        if (patientsJSON != null) {
            for (int i = 0; i < patientsJSON.length(); i++) {
                try {
                    JSONObject p = (JSONObject) patientsJSON.get(i);
                    System.out.println(p.getJSONObject("profile").getString("name"));
                    System.out.println(currPatient);
                    if (p.getJSONObject("profile").getString("name").equals(currPatient)) {
                        JSONObject patient = p.getJSONObject("profile");
                        this.patient = patient;
                        updateView();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return;
    }

    public void updateView() {
        try {
            TextView ageTV = view.findViewById(R.id.age);
            ageTV.setText(patient.getString("birthday"));
            TextView genderTV = view.findViewById(R.id.gender);
            genderTV.setText(patient.getString("gender"));
            TextView eName = view.findViewById(R.id.eName);
            eName.setText(patient.getString("emergencyName"));
            TextView ePhone = view.findViewById(R.id.ePhone);
            ePhone.setText(patient.getString("emergencyPhone"));
            TextView notesTV = view.findViewById(R.id.notes);
            notesTV.setText(patient.getString("bio"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void refresh() {

    }


}
