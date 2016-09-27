package com.lukeyj.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HeadlineFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("onCreateView - HeadlineFragment");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.news_articles, container, false);

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.container, new HeadlineFragment()).commit();
//        }


    }

    public void sendMessage2(View view) {
        // Do something in response to button
        System.out.println("sendMessage2 - HeadlineFragment");

//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
    }

}