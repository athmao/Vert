package com.example.alexandermao.remindme001;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    private ArrayList<Patient> patientlist;
    private ArrayList<Patient> spinnerPatientlist;
    private GlobalVars v;
    private CustomAdapter adapter;
    private ListView listView;
    private Button addPatientButton;
    private Spinner choosePatient;
    private int chosen;
    private Caretaker currentCaretaker;
    private CustomAdapter spinnerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.view = inflater.inflate(R.layout.activity_caretaker_patients, container, false);

        v = GlobalVars.getSingleInstance();
        this.caretakers = v.getCaretakers();
        this.patients = v.getPatients();
        this.currentCaretaker = v.getCurrentlyLoggedIn();


        this.choosePatient = view.findViewById(R.id.choosepatient);
        this.chosen = 0;
        this.spinnerPatientlist = new ArrayList<>();
        patientlist = new ArrayList<>();

        for (Patient p: this.currentCaretaker.getPatients().values()) {
            patientlist.add(p);
        }
        for(Patient p: this.patients.values()) {
            if (!patientlist.contains(p)) {
                spinnerPatientlist.add(p);
            }
        }

       /* ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this.getActivity(), R.array.patientarray, R.layout.spin_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.choosePatient.setAdapter(adapter2);*/

        spinnerAdapter = new CustomAdapter(spinnerPatientlist);
        this.choosePatient.setAdapter(spinnerAdapter);

        this.choosePatient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent != null && parent.getChildAt(0) != null) {
                    chosen = position;
                }}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        this.addPatientButton = view.findViewById(R.id.addpatient);

        this.addPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPatient(chosen);
            }
        });

        this.listView = (ListView) view.findViewById(R.id.listview);
        adapter = new CustomAdapter(patientlist);
        this.listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Patient p = (Patient) listView.getItemAtPosition(position);
                HashMap<String, Caretaker> c = p.getCaretakers();
                String op = "";
                for(String ctn: c.keySet()) {
                    if (op.isEmpty()) {
                        op = op + ctn;
                    } else {
                        op = op + "\n" + ctn;
                    }
                }
                Toast.makeText(getActivity(), op,Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public void addPatient(int chosen) {
        Patient p = this.spinnerPatientlist.get(chosen);
        HashMap<String, Patient> t = this.currentCaretaker.getPatients();
        t.put(p.getName(), p);
        this.currentCaretaker.setPatients(t);
        v.setCurrentlyLoggedIn(this.currentCaretaker);
        this.caretakers.put(currentCaretaker.getName(), currentCaretaker);
        v.setCaretakers(this.caretakers);
        HashMap<String, Caretaker> t1 = p.getCaretakers();
        t1.put(currentCaretaker.getName(), currentCaretaker);
        p.setCaretakers(t1);
        patients.put(p.getName(), p);
        v.setPatients(patients);

        spinnerPatientlist.remove(p);
        spinnerAdapter = new CustomAdapter(spinnerPatientlist);
        this.choosePatient.setAdapter(spinnerAdapter);

        patientlist.add(p);
        adapter = new CustomAdapter(patientlist);
        this.listView.setAdapter(adapter);
        return;

    }

    class CustomAdapter extends BaseAdapter{
        ArrayList<Patient> paaa;
        public CustomAdapter(ArrayList<Patient> a) {
            this.paaa = a;
        }
        @Override
        public int getCount() {
            return paaa.size();
        }

        @Override
        public Object getItem(int position) {
            return patientlist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.patient_list_item, null);
            TextView pname = (TextView) convertView.findViewById(R.id.patientname);
            pname.setText(paaa.get(position).getName());
            return convertView;
        }
    }
}
