package com.example.alexandermao.remindme001;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.HashMap;

/* This activity is the page caretakers see after they login

   1. Caretaker profile button, allows caretaker to edit own information, see CaretakerProfileActivity
   2. Logout button, allows switching of users, goes back to LoginActivity
   3. Tab Layout for Caretaker's tasks, and patients
   4. Task tab has CaretakerTasksActivity
   5. Patients tab has CaretakerPatientsActivity

*/
public class CaretakerActivity extends AppCompatActivity{


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Button logout;
    private ImageView refresh;
    private Bundle s;
    private CaretakerTasksActivity tasks;
    private CaretakerPatientsActivity patients;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        s = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caretaker);


        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        tasks = new CaretakerTasksActivity();
        patients = new CaretakerPatientsActivity();
        adapter.AddFragment(tasks, "Tasks");
        adapter.AddFragment(patients, "Patients");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
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
    public void logout() {
        NavUtils.navigateUpFromSameTask(this);
    }

    public void refresh() {
        this.patients.refresh();
        this.tasks.refresh();
    }
}
