package com.example.alexandermao.remindme001;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/*
    This is what you see when you go to a patient, should be similar to caretaker activity so there can be overlap
   1. Tab Layout for Patient's tasks, and patient info
   2. Task tab has PatientTasksActivity
   3. Patients tab has PatientProfileActivity
 */
public class PatientsActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Button back;
    private ImageView refresh;
    private PatientTasksActivity tasks;
    private PatientProfileActivity profile;
    private String currpatient;
    private String user;
    private GlobalVars globalVars;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients);

        globalVars = GlobalVars.getSingleInstance();
        user = globalVars.getUser();
        currpatient = globalVars.getPatient();

        title = (TextView) findViewById(R.id.title);
        title.setText(currpatient);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        tasks = new PatientTasksActivity();
        profile = new PatientProfileActivity();
        adapter.AddFragment(tasks, "Tasks");
        adapter.AddFragment(profile, "Profile");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        refresh = findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                refresh();
            }
        });


    }
    public void back() {
        NavUtils.navigateUpFromSameTask(this);
    }

    public void refresh() {
        this.profile.refresh();
        this.tasks.refresh();
    }

}
