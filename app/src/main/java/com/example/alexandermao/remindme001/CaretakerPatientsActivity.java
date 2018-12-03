package com.example.alexandermao.remindme001;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/*
    This Activity shows all the patients of a chartaker
    1. One should see all the patients a caretaker has
    2. Clicking a patient should take you to PatientView
    3. Clicking add patient should allow you to choose from database of patients, and add patient to chartaker's patient hashmap

 */
public class CaretakerPatientsActivity extends Fragment {


    private View view;
    private GlobalVars globalVars;
    private ArrayList<String> myPatients;
    private String user;

    private PatientsAdapter patientsAdapter;

    private ListView patientsListView;
    private View actionB;
    private View actionA;

    private String serverURL = "http://54.67.72.192/";
    private String patientListSuffix = "get_user_patients?user=%s";
    private String patientListSuffix1= "get_patients";
    private LayoutInflater i;
    private ViewGroup c;
    private Bundle s;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.view = inflater.inflate(R.layout.activity_caretaker_patients, container, false);

        this.globalVars = GlobalVars.getSingleInstance();
        this.user = globalVars.getUser();

        this.actionB = view.findViewById(R.id.action_b);
        this.actionA = view.findViewById(R.id.action_a);
        actionB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addExistingPatient();
            }
        });

        actionA.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addNewPatient();
            }
        });

        this.patientsListView = view.findViewById(R.id.listview);
        this.myPatients = new ArrayList<>();



        this.patientsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viewPatient((String) patientsListView.getItemAtPosition(position));
            }
        });
        String patientListURL = String.format(serverURL + patientListSuffix, user);
        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        JsonArrayRequest patientsJSON = new JsonArrayRequest(Request.Method.GET, patientListURL, new JSONArray(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Display the first 500 characters of the response string.
                        addPatients(response);
                        patientsAdapter = new PatientsAdapter();
                        patientsListView.setAdapter(patientsAdapter);
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
    public void refresh() {
        this.myPatients = new ArrayList<>();



        this.patientsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viewPatient((String) patientsListView.getItemAtPosition(position));
            }
        });
        String patientListURL = String.format(serverURL + patientListSuffix, user);
        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        JsonArrayRequest patientsJSON = new JsonArrayRequest(Request.Method.GET, patientListURL, new JSONArray(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Display the first 500 characters of the response string.
                        addPatients(response);
                        patientsAdapter = new PatientsAdapter();
                        patientsListView.setAdapter(patientsAdapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });
        queue.add(patientsJSON);
    }
    public void addNewPatient() {
        Intent intent = new Intent(view.getContext(), AddNewPatientActivity.class);
        startActivity(intent);
    }

    public void addExistingPatient() {
        Intent intent = new Intent(view.getContext(), AddExistingPatientActivity.class);
        startActivity(intent);
    }
    public void viewPatient(String patient) {
        Intent intent = new Intent(view.getContext(), PatientsActivity.class);
        this.globalVars.setPatient(patient);
        startActivity(intent);
    }

    public void addPatients(JSONArray patientsJSON) {
        if (patientsJSON != null) {
            for (int i = 0; i < patientsJSON.length(); i++) {
                try {
                    JSONObject p = (JSONObject) patientsJSON.get(i);
                    String pn = p.getJSONObject("profile").getString("name");
                    this.myPatients.add(pn);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return;

    }
    public class PatientsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return myPatients.size();
        }

        @Override
        public Object getItem(int position) {
            return myPatients.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.patient_list_item, null);
            TextView pname = (TextView) convertView.findViewById(R.id.patientname);
            pname.setText(myPatients.get(position));
            return convertView;
        }
    }

}
