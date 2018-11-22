package com.example.alexandermao.remindme001;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity{
    private HashMap<String, Patient> patients;
    private HashMap<String, Caretaker> caretakers;
    private HashMap<String, String> userVer;
    private HashMap<String, Caretaker> userToCare;
    private GlobalVars v;

    private EditText username;
    private EditText password;
    private EditText name;
    private EditText email;
    private EditText phoneNumber;
    private Button cancel;
    private Button done;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent intent = getIntent();

        v = GlobalVars.getSingleInstance();
        this.patients = v.getPatients();
        this.caretakers = v.getCaretakers();
        this.userVer = v.getUserVer();
        this.userToCare = v.getUserToCare();

        this.username = findViewById(R.id.usernametext);
        this.password = findViewById(R.id.passwordtext);
        this.name = findViewById(R.id.nametext);
        this.email = findViewById(R.id.emailtext);
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
        String rEmail = this.email.getText().toString().trim();
        String rPhone = this.phoneNumber.getText().toString().trim();
        String rUsername = this.username.getText().toString().trim();
        String rPassword = this.password.getText().toString();

        if ( rName.isEmpty()|| rEmail.isEmpty() || rPassword.isEmpty()
                || rPhone.isEmpty() || rUsername.isEmpty()) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Missing a field",
                    Toast.LENGTH_SHORT);

            toast.show();
        } else if (this.userVer.containsKey(rUsername)) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "User Already Exists",
                    Toast.LENGTH_SHORT);

            toast.show();
        } else {
            Caretaker newCaretaker = new Caretaker(rName, rEmail , rPhone, rUsername );
            this.userVer.put(rUsername, rPassword);
            this.userToCare.put(rUsername, newCaretaker);
            this.caretakers.put(rName, newCaretaker);
            v.setCaretakers(this.caretakers);
            v.setUserToCare(this.userToCare);
            v.setUserVer(this.userVer);
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Registered Successfully",
                    Toast.LENGTH_SHORT);

            toast.show();

            NavUtils.navigateUpFromSameTask(this);

        }


    }
}
