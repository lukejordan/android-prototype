package com.lukeyj.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.lukeyj.testapp.domain.Job;
import com.lukeyj.testapp.domain.Person;
import com.lukeyj.testapp.utility.RestHelper;

public class NextActivity extends AppCompatActivity {

    public static final String ENVIRONMENT_HOST_NAME = "http://test-loadbalancer-1089136587.us-west-2.elb.amazonaws.com";
                                                      //http://basisapp-env.us-west-2.elasticbeanstalk.com
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("onCreate - NextActivity");

        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_next_dynamic);

        Intent intent = getIntent();

        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        System.out.println("message: " + message);

        String url ="http://www.google.com";
        url = ENVIRONMENT_HOST_NAME + "/service/testMultiple";

        RestHelper.makeGetRequest (url, this);
        RestHelper.makeStandardJsonGet(url, this);
        RestHelper.makeStandardJsonArrayGet(url, this);
        RestHelper.makeStringGetRequest(url, this);
        RestHelper.makePostRequest (prepareMockData (message), ENVIRONMENT_HOST_NAME + "/service/testPost2", this);

        dynamicFragment(savedInstanceState);

        System.out.println("On end next create baby.");
    }

    private Object prepareMockData (String postedData) {
        Person person = new Person ();

        Job job = new Job();
        job.setCompany("post office");
        job.setRole("postie job");

        person.setAge(999);
        person.setFirstName(postedData);
        person.setLastName("postman");
        person.setJob(job);

        return person;
    }

    /**
     *
     * Here be dragons....
     *
     */




    private void dynamicFragment(Bundle savedInstanceState) {
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            HeadlineFragment firstFragment = new HeadlineFragment();
            ArticleFragment secondFragment = new ArticleFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());
            secondFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment)
                    //.add(R.id.fragment_container, secondFragment)
                    .commit();
        }


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