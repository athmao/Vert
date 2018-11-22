package com.example.alexandermao.remindme001;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

/* This activity is the page caretakers see after they login

   1. Caretaker profile button, allows caretaker to edit own information, see CaretakerProfileActivity
   2. Logout button, allows switching of users, goes back to LoginActivity
   3. Tab Layout for Caretaker's tasks, and patients
   4. Task tab has CaretakerTasksActivity
   5. Patients tab has CaretakerPatientsActivity

*/
public class CaretakerActivity extends AppCompatActivity{

    private Caretaker caretaker;
    private HashMap<String, Patient> patients;
    private HashMap<String, Caretaker> caretakers;

    public CaretakerActivity() {
        /*Intent intent = getIntent();
        this.patients = (HashMap<String, Patient>) intent.getSerializableExtra("PATIENTS");
        this.caretakers = (HashMap<String, Caretaker>) intent.getSerializableExtra("CARETAKERS");
        this.caretaker = (Caretaker) intent.getSerializableExtra("CARETAKER");*/
        //NavUtils.navigateUpFromSameTask(this);
        GlobalVars v = GlobalVars.getSingleInstance();
        this.patients = v.getPatients();
        this.caretakers = v.getCaretakers();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caretaker);
    }
}
