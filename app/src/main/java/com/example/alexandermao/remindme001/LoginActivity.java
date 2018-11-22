package com.example.alexandermao.remindme001;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private HashMap<String, Patient> patients;
    private HashMap<String, Caretaker> caretakers;
    private HashMap<String, String> userVer;
    private HashMap<String, Caretaker> userToCare;
    private GlobalVars v;

    private EditText username;
    private EditText password;
    private Button loginButton;
    private TextView registerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        v = GlobalVars.getSingleInstance();
        this.patients = v.getPatients();
        this.caretakers = v.getCaretakers();
        this.userVer = v.getUserVer();
        this.userToCare = v.getUserToCare();

        this.username = findViewById(R.id.usernametext);
        this.password = findViewById(R.id.passwordtext);
        this.loginButton = findViewById(R.id.loginbutton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAccount();
            }
        });
        this.registerButton = findViewById(R.id.registerbutton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerAccount();
            }
        });
    }

    private void loginAccount() {
        Intent intent = new Intent(this, CaretakerActivity.class);
        if (userVer.containsKey(this.username.getText().toString())) {
            if (userVer.get(this.username.getText().toString()).equals(this.password.getText().toString())) {
                v.setCurrentlyLoggedIn(this.userToCare.get(this.username.getText().toString()));
                startActivity(intent);
            } else {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Password is Incorrect",
                        Toast.LENGTH_SHORT);

                toast.show();
            }
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "User does not exist",
                    Toast.LENGTH_SHORT);

            toast.show();
        }

    }

    private void registerAccount() {


        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
