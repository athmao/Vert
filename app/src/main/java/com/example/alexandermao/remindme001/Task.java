package com.example.alexandermao.remindme001;
import java.util.Date;
public class Task implements Comparable<Task>{



    private Date time;
    private String title;
    private Caretaker caretaker;
    private Patient patient;
    private String notes;
    private boolean completed;

    public Task(Date time, String title, Caretaker caretaker, Patient patient, String notes) {
        this.time = time;
        this.title = title;
        this.caretaker = caretaker;
        this.patient = patient;
        this.notes = notes;
        this.completed = false;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Caretaker getCaretaker() {
        return caretaker;
    }

    public void setCaretaker(Caretaker caretaker) {
        this.caretaker = caretaker;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public int compareTo(Task othertask) {
        if (this.time.before(othertask.time)) {
            return 1;
        } else if (this.time.after(othertask.time)) {
            return -1;
        }
        return 0;
    }


}
