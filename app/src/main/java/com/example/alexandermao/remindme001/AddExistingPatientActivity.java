package com.example.alexandermao.remindme001;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class AddExistingPatientActivity  extends AppCompatActivity {

    private GlobalVars globalVars;
    private ArrayList<String> patients;
    private ArrayList<String> myPatients;

    private String user;

    private ListView patientsListView;

    private PatientsAdapter patientsAdapter;

    private String serverURL = "http://54.67.72.192/";
    private String patientListSuffix = "get_user_patients?user=%s";
    private String patientListSuffix1= "get_patients";
    private Bundle s;
    private Button cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.s = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_existing_patient);

        this.globalVars = GlobalVars.getSingleInstance();
        this.user = globalVars.getUser();

        this.cancel = findViewById(R.id.done);
        cancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                cancelCreate();
            }
        });
        this.patientsListView = findViewById(R.id.listview);
        this.patientsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectPatient(parent, position);
            }
        });
        //patients
        this.patients = new ArrayList<>();
        this.myPatients = new ArrayList<>();

        addMyPatients();


    }
    public void cancelCreate() {
        NavUtils.navigateUpFromSameTask(this);
    }

    public void selectPatient(AdapterView<?> parent, int position) {
        TextView t = (TextView) parent.getChildAt(position).findViewById(R.id.patientname);
        final String n = t.getText().toString();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(String.format("Would you like to add %s to your patients?", n));
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                addPatient(n);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();


    }
    public void addPatient(String o) {
        final String n = o;
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = String.format("http://54.67.72.192/add_caretaker" +
                        "?user=%1$s&patient=%2$s",
                user, n);

        JsonObjectRequest loginJSON = new JsonObjectRequest(Request.Method.GET, url, new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            response.get("success");
                            finishAddUser(n);
                        } catch (JSONException e) {

                        }
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
    public void finishAddUser(String n) {
        Toast toast = Toast.makeText(getApplicationContext(),
                String.format("Added %s Under Your Care Successfully", n),
                Toast.LENGTH_SHORT);

        toast.show();
        this.patients = new ArrayList<>();
        this.myPatients = new ArrayList<>();

        addMyPatients();


    }
    public void addMyPatients() {
        String patientListURL = String.format(serverURL + patientListSuffix, this.user);
        RequestQueue queue = Volley.newRequestQueue(this);


        JsonArrayRequest patientsJSON = new JsonArrayRequest(Request.Method.GET, patientListURL, new JSONArray(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Display the first 500 characters of the response string.
                        addMyPatientsHelper(response);
                        addAllPatients();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });
        queue.add(patientsJSON);
    }

    public void addMyPatientsHelper(JSONArray response) {
        if (response != null) {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject p = (JSONObject) response.get(i);
                    String pn = p.getJSONObject("profile").getString("name");
                    this.myPatients.add(pn);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addAllPatients() {
        String patientListURL = String.format(serverURL + patientListSuffix1);
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest patientsJSON = new JsonArrayRequest(Request.Method.GET, patientListURL, new JSONArray(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Display the first 500 characters of the response string.
                        addAllPatientsHelper(response);
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

    public void addAllPatientsHelper(JSONArray response) {
        if (response != null) {
            for (int i = 0; i < response.length(); i++) {
                try {
                    if (!myPatients.contains(response.getString(i))) {
                        this.patients.add(response.getString(i));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class PatientsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return patients.size();
        }

        @Override
        public Object getItem(int position) {
            return patients.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.patient_list_item, null);
            TextView pname = (TextView) convertView.findViewById(R.id.patientname);
            pname.setText(patients.get(position));
            return convertView;
        }
    }
}
