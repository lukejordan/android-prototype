package com.lukeyj.testapp.utility;

import android.content.Context;
import android.support.v4.util.ArrayMap;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lukeyj.testapp.domain.Person;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public class RestHelper {

    public static void makeGetRequest (String url, Context callingClass) {
        System.out.println("Make get request: " + url);

        final Map<String, String> mHeaders = new ArrayMap<>();
        mHeaders.put("Content-Type", "application/json");
        mHeaders.put("Accept", "application/json");

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //System.out.println ("Response is: "+ response.substring(0,500));
                        System.out.println ("GET Response is: "+ response);

                        //String test = "[{\"firstName\":\"First1\",\"lastName\":\"Last Name 34\",\"age\":21,\"job\":{\"role\":\"role1\",\"company\":\"company2\"}},{\"firstName\":\"Frank\",\"lastName\":\"Smith\",\"age\":25,\"job\":{\"role\":\"bigwig\",\"company\":\"Mountain View\"}}]";
                        //List<Person> persons = (List<Person>)JsonSerializer.deserializeObject (test);
                        //persons.toString();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Get - Failed!");
                String test = "[{\"firstName\":\"First1\",\"lastName\":\"Last Name 34\",\"age\":21,\"job\":{\"role\":\"role1\",\"company\":\"company2\"}},{\"firstName\":\"Frank\",\"lastName\":\"Smith\",\"age\":25,\"job\":{\"role\":\"bigwig\",\"company\":\"Mountain View\"}}]";
                List<Person> persons = (List<Person>) JsonSerializer.deserializeObject (test);
                System.out.println("Person object: " + persons.get(0));
                System.out.println("Person object: " + persons.get(1));
                System.out.println("End of it all!");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                return mHeaders;
            }
        };

        retrieveQueue(callingClass).add(stringRequest);
        System.out.println("Make get request - end");
    }


    public static void makePostRequest (Object object, String url, Context callingClass) {
        System.out.println("Make post request: " + url);

        final String mRequestBody = JsonSerializer.serializeObject(object);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    // Display the first 500 characters of the response string.
                    //System.out.println ("Response is: "+ response.substring(0,500));

                    System.out.println ("Post response is: "+ response);
                }
            }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Post - That didn't work!");
                        String test = "[{\"firstName\":\"First1\",\"lastName\":\"Last Name 34\",\"age\":21,\"job\":{\"role\":\"role1\",\"company\":\"company2\"}},{\"firstName\":\"Frank\",\"lastName\":\"Smith\",\"age\":25,\"job\":{\"role\":\"bigwig\",\"company\":\"Mountain View\"}}]";
                        List<Person> persons = (List<Person>)JsonSerializer.deserializeObject (test);
                        System.out.println("Person object: " + persons.get(0));
                        System.out.println("Person object: " + persons.get(1));


                        System.out.println("Error message: " + error.getMessage());
                        System.out.println("Error message: " + error.getCause());
                        System.out.println("Error message: " + error.getStackTrace());
                        System.out.println("Post - End of it all!");
                    }

            }) {


                //            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                //                Map<String, String> params = new HashMap<>();
                //                //params.put("param1", "parm1");
                //                //params.put("param2", "parm2");
                //                return params;
                //            };

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";

                    if (response != null) {
                        System.out.println("Post - Response status code: " + response.statusCode);
                        responseString = String.valueOf(new String(response.data));
                        System.out.println("Post - Response data: " + responseString);

                        // can get more details such as response.headers
                    }
                    else {
                        System.out.println("Post - Response is null");
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }

                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

//                @Override
//                public String getBodyContentType() {
//                    return "application/json";
//                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    System.out.println("Get the body for object: " + mRequestBody);
                    try {
                        return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                        System.out.println("Unsupported Encoding while trying to get the bytes");
                        return null;
                    }
                }

            };

        retrieveQueue(callingClass).add(stringRequest);

        System.out.println("Make post request - end");
    }

    public static void makeStandardJsonGet (String url, Context callingClass) {

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("Json get Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Error in json get: " + error.getMessage());
                    }
                });

        retrieveQueue(callingClass).add(jsObjRequest);
    }

    public static void makeStandardJsonArrayGet (String url, Context callingClass) {

        JsonArrayRequest jsObjRequest = new JsonArrayRequest
                (url, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println("Json array get Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Error in json array get: " + error.getMessage());
                    }
                });

        retrieveQueue(callingClass).add(jsObjRequest);
    }


    private static RequestQueue retrieveQueue (Context callingClass) {
        // TODO singleton store the request queue for efficient network connectivity

        RequestQueue queue = Volley.newRequestQueue(callingClass);
        return queue;
    }


    public static void makeStringGetRequest (String url, Context currentClass) {

        RequestQueue mRequestQueue = retrieveCustomQueue (currentClass);

        // Formulate the request and handle the response.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Do something with the response
                        System.out.println("String Req - Success. Response: " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        System.out.println("String Req - Fail. Message: " + error.getMessage());
                    }
                });

        mRequestQueue.add(stringRequest);

    }

    private static RequestQueue retrieveCustomQueue(Context currentClass) {

        RequestQueue mRequestQueue = null;

        // Instantiate the cache
        Cache cache = new DiskBasedCache(currentClass.getCacheDir(), 1024 * 1024); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        mRequestQueue = new RequestQueue(cache, network);

        // Start the queue
        mRequestQueue.start();

        return mRequestQueue;
    }

}
