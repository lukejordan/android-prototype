package com.lukeyj.testapp.utility;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lukeyj.testapp.domain.Person;

import java.lang.reflect.Type;
import java.util.List;

public class JsonSerializer {

    public static String serializeObject (Object targetObject) {
        System.out.println("Serialize as json");
        Gson gson = new Gson();
        String serializedObject = gson.toJson(targetObject);

        System.out.println("serialized data: " + serializedObject);

        return serializedObject;
    }

    public static Object deserializeObject (String jsonObject) {
        Gson gson = new Gson();

        Type listOfTestObject = new TypeToken<List<Person>>(){}.getType();

        List person = gson.fromJson(jsonObject, listOfTestObject);
        return person;
    }

//    private List<Person> retrieveObject (String json) {
//        Gson gson = new Gson();
//
//        Type listOfTestObject = new TypeToken<List<Person>>(){}.getType();
//
//        List person = gson.fromJson(json, listOfTestObject);
//        return person;
//    }

}
