package com.lukeyj.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

public class NextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("onCreate - NextActivity");

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_next);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        System.out.println("message: " + message);

        System.out.println("On end next create baby.");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle app bar item clicks here. The app bar
        // automatically handles clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // FIXME Commented out because of action settings
//        if (id == R.id.action_settings) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }



    public void sendMessage(View view) {
        // Do something in response to button
        System.out.println("Buttoned it mate in main. Goto main2");
        //setContentView(R.layout.activity_main2);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void sendMessage2(View view) {
        // Do something in response to button
        System.out.println("sendMessage2 - Next");

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}