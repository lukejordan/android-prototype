package com.lukeyj.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;


public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        System.out.println("onCreate - FragmentActivity");

        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_main);

        System.out.println("On next create baby.");

        setContentView(R.layout.activity_next);


        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        System.out.println("message: " + message);


        System.out.println("onCreate end - FragmentActivity");

    }

}
