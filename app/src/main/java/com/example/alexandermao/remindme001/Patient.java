package com.example.alexandermao.remindme001;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Patient extends Person {

    private HashMap<String, Caretaker> caretakers;
    private PriorityQueue<Task> tasks;
    private Date birthday;
    private String notes;
    private String gender;

    public Patient(String name, String email, String phonenumber, String profileImageURLString,
                   Date birthday, String notes, String gender, ArrayList<Caretaker> caretakers) {
        super(name, email, phonenumber, profileImageURLString);
        this.birthday = birthday;
        this.notes = notes;
        this.gender = gender;
        this.caretakers = new HashMap<>();
        this.tasks = new PriorityQueue<>();
        for (Caretaker c: caretakers) {
            this.caretakers.put(c.getName(), c);
        }
    }

    public HashMap<String, Caretaker> getCaretakers() {
        return caretakers;
    }

    public void setCaretakers(HashMap<String, Caretaker> caretakers) {
        this.caretakers = caretakers;
    }

    public PriorityQueue<Task> getTasks() {
        return tasks;
    }

    public void setTasks(PriorityQueue<Task> tasks) {
        this.tasks = tasks;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
