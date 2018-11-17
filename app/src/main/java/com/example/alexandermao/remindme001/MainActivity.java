package com.example.alexandermao.remindme001;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import org.json.JSONException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button registerButton = findViewById(R.id.registerButton);

        final Button loginButton = findViewById(R.id.loginButton);

        EditText usernameText = findViewById(R.id.usernameText);

        EditText passwordText = findViewById(R.id.passwordText);

        String getUserName = usernameText.getText().toString();

        String getPassword = passwordText.getText().toString();

        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button


            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button


            }
        });
    }

    public void getRequest() {

        RequestQueue queue = Volley.newRequestQueue(this);
        String tempUrl ="http://www.google.com";
        String url = "";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        String tempResponse = null;
                        JSONArray fullResults = null;
                        String getDistrictNumber = null;
                        String firstName = null;
                        JSONArray districtsArray = null;
                        JSONArray legislatorsArray = null;
                        String person1lastName = "";
                        String state = "";

                        try {
                            tempResponse = response.getJSONArray("results").getJSONObject(0).getJSONObject("address_components").getString("city");
                            fullResults = response.getJSONArray("results");
                            getDistrictNumber = response.getJSONArray("results").getJSONObject(0).getJSONObject("fields").getJSONArray("congressional_districts").getJSONObject(0).getString("district_number");
                            state = fullResults.getJSONObject(0).getJSONObject("address_components").getString("state");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                    }
                });
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
//        String blah = jsonObjectRequest.toString();
//        Log.d("log message", blah);



    }










}
