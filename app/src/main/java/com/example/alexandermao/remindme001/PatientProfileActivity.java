package com.example.alexandermao.remindme001;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*
    View profile of the patient
    1. Should simply display information
    2. Should have an edit button which takes you to EditPatientProfileActivity
 */
public class PatientProfileActivity extends Fragment {

    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.activity_patients_profile, container, false);
        return view;
    }

    public void refresh() {

    }

}
