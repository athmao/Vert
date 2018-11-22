package com.example.alexandermao.remindme001;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

/*
    This Activity shows all the patients of a chartaker
    1. One should see all the patients a caretaker has
    2. Clicking a patient should take you to PatientView
    3. Clicking add patient should allow you to choose from database of patients, and add patient to chartaker's patient hashmap

 */
public class CaretakerPatientsActivity extends Fragment {

    private Caretaker caretaker;
    private HashMap<String, Patient> patients;
    private HashMap<String, Caretaker> caretakers;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.activity_caretaker_tasks, container, false);
        return view;
    }
}
