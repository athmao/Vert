package com.example.alexandermao.remindme001;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Caretaker extends Person {



    private HashMap<String, Patient> patients;
    private PriorityQueue<Task> tasks;

    public Caretaker(String name, String email, String phonenumber) {
        super(name, email, phonenumber);
        patients = new HashMap<>();
        tasks = new PriorityQueue<>();
    }

    public Caretaker(String name, String email, String phonenumber, String profileImageURLString) {
        super(name, email, phonenumber, profileImageURLString);
        patients = new HashMap<>();
        tasks = new PriorityQueue<>();
    }

    public HashMap<String, Patient> getPatients() {
        return patients;
    }

    public void setPatients(HashMap<String, Patient> patients) {
        this.patients = patients;
    }

    public PriorityQueue<Task> getTasks() {
        return tasks;
    }

    public void setTasks(PriorityQueue<Task> tasks) {
        this.tasks = tasks;
    }
}
