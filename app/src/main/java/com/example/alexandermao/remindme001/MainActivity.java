package com.example.alexandermao.remindme001;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent loginIntent = new Intent(this, LoginActivity.class);

        startActivity(loginIntent);
    }
}
