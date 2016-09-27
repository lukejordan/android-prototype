package com.lukeyj.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.lukeyj.testapp.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("onCreate - MainActivity");
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view) {
        // Do something in response to button
        System.out.println("Buttoned it mate in main. Goto main2");
        //setContentView(R.layout.activity_main2);

        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }



    public void sendMessage2(View view) {
        // Do something in response to button
        System.out.println("Unexpected");

        Intent intent = new Intent(this, NextActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

        //setContentView(R.layout.activity_main);
    }
}