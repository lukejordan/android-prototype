package com.lukeyj.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.lukeyj.testapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("onCreate - Main2Activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void sendMessage3(View view) {
        // Do something in response to button
        System.out.println("Buttoned it mate in main2.");
        setContentView(R.layout.activity_main2);
    }

    public void sendMessage2(View view) {
        // Do something in response to button
        System.out.println("sendMessage2 - Main2");

        Intent intent = new Intent(this, NextActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();

        System.out.println("Message to submit: " + message);

        intent.putExtra(EXTRA_MESSAGE, message);

        startActivity(intent);
    }

}
