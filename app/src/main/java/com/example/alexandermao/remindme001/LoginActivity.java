package com.example.alexandermao.remindme001;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button loginButton;
    private TextView registerButton;
    private String serverURL = "http://54.67.72.192/";
    private String loginSuffix = "login?username=%1$s&password=%2$s";
    private GlobalVars globalVars;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.globalVars = GlobalVars.getSingleInstance();

        this.username = findViewById(R.id.usernametext);
        this.password = findViewById(R.id.passwordtext);
        this.loginButton = findViewById(R.id.loginbutton);
        this.registerButton = findViewById(R.id.registerbutton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAccount();
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerAccount();
            }
        });
    }

    private void loginAccount() {
        String loginURL = String.format(serverURL + loginSuffix, username.getText().toString(), password.getText().toString());

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest loginJSON = new JsonObjectRequest(Request.Method.GET, loginURL, new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.
                        try {
                            response.get("success");
                            globalVars.setUser(username.getText().toString());
                            proceed();
                        } catch(JSONException error) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setMessage("Incorrect username or password!");
                            builder.setTitle("Please try again");
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            return;
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

    private void proceed() {
        Intent intent = new Intent(this, CaretakerActivity.class);
        startActivity(intent);
    }

    private void registerAccount() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
