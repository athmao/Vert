package com.example.alexandermao.remindme001;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity{

    private EditText username;
    private EditText password;
    private EditText name;
    private EditText phoneNumber;
    private Button cancel;
    private Button done;
    private String serverURL = "http://54.67.72.192/";
    private String registerSuffix = "create_account?username=%1$s&password=%2$s&name=%3$s&phone=%4$s";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent intent = getIntent();

        this.username = findViewById(R.id.usernametext);
        this.password = findViewById(R.id.passwordtext);
        this.name = findViewById(R.id.nametext);
        this.phoneNumber = findViewById(R.id.phonenumbertext);
        this.cancel = findViewById(R.id.cancelbutton);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToLogin();
            }
        });

        this.done = findViewById(R.id.donebutton);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });
    }

    public void backToLogin() {
        NavUtils.navigateUpFromSameTask(this);
    }

    public void registerNewUser() {
        String rName = this.name.getText().toString().trim();
        String rPhone = this.phoneNumber.getText().toString().trim();
        String rUsername = this.username.getText().toString().trim();
        String rPassword = this.password.getText().toString();

        if ( rName.isEmpty()|| rPassword.isEmpty()
                || rPhone.isEmpty() || rUsername.isEmpty()) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Missing a field",
                    Toast.LENGTH_SHORT);

            toast.show();
            return;
        }

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = String.format(serverURL + registerSuffix,
                username.getText().toString(),
                password.getText().toString(),
                name.getText().toString(),
                phoneNumber.getText().toString());

        JsonObjectRequest loginJSON = new JsonObjectRequest(Request.Method.GET, url, new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.
                        try {
                            response.get("success");

                            proceed();
                        } catch (JSONException e) {
                            fail();
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

    public void proceed() {
        Toast toast = Toast.makeText(getApplicationContext(),
                "Registered Successfully",
                Toast.LENGTH_SHORT);

        toast.show();

        NavUtils.navigateUpFromSameTask(this);
    }

    public void fail() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Account creation failure");
        builder.setTitle("Failure");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
