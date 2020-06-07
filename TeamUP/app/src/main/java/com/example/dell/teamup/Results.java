package com.example.dell.teamup;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Results extends AppCompatActivity {

    Spinner eventspinner,winningteam,runnerupteam;
    Button add1;
    Toolbar toolbarWidget;
    private static final String PATH_TO_SERVER = "http://teamupyou.xyz/eventspinner.php";
    private static final String PATH_TO_SERVER1 = "http://teamupyou.xyz/teamspinner.php";

    private static String url = "http://teamupyou.xyz/addresults.php";

    protected List<DataObject> spinnerData;
    private RequestQueue queue;
    protected List<DataObject2> spinnerdata1;
    Snackbar snackbar;
    JSONArray result;
    ArrayList<String> students;
    ArrayList<String> students1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        toolbarWidget = (Toolbar) findViewById(R.id.resultstoolbar);

        toolbarWidget.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        setSupportActionBar(toolbarWidget);
        toolbarWidget.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Results.super.onBackPressed();
                    }
                }

        );
        queue = Volley.newRequestQueue(this);
        eventspinner = (Spinner)findViewById(R.id.eventspinner);
        winningteam = (Spinner)findViewById(R.id.winningteamspinner);
        runnerupteam = (Spinner)findViewById(R.id.runningteamspinner);
        students = new ArrayList<String>();
        students1 = new ArrayList<String>();
        requestJsonObject();
        requestJsonObject1();
        requestJsonObject2();
        add1 = (Button) findViewById(R.id.add1);
        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addResults();
            }
        });

    }

    private void requestJsonObject() {
        StringRequest stringRequest = new StringRequest("http://teamupyou.xyz/eventspinner.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            j = new JSONObject(response);
                            result = j.getJSONArray(Config.JSON_ARRAY);
                            getStudents(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void getStudents(JSONArray j){
        students.clear();
        for(int i=0;i<j.length();i++){
            try {
                JSONObject json = j.getJSONObject(i);
                students.add(json.getString(Config.TAG_EVENTNAME));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        eventspinner.setAdapter(null);
        eventspinner.setAdapter(new ArrayAdapter<String>(Results.this, android.R.layout.simple_spinner_dropdown_item, students));
    }
    private void getStudents1(JSONArray j){
        students1.clear();
        for(int i=0;i<j.length();i++){
            try {
                JSONObject json = j.getJSONObject(i);
                students1.add(json.getString(Config1.TAG_TEAMNAME));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        winningteam.setAdapter(null);
        winningteam.setAdapter(new ArrayAdapter<String>(Results.this, android.R.layout.simple_spinner_dropdown_item, students1));
    }

    private void requestJsonObject1() {
        StringRequest stringRequest = new StringRequest("http://teamupyou.xyz/teamspinner.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            j = new JSONObject(response);
                            result = j.getJSONArray(Config1.JSON_ARRAY);
                            getStudents1(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void requestJsonObject2() {
        StringRequest stringRequest = new StringRequest("http://teamupyou.xyz/teamspinner.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            j = new JSONObject(response);
                            result = j.getJSONArray(Config1.JSON_ARRAY);
                            getStudents2(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void getStudents2(JSONArray j){
        students1.clear();
        for(int i=0;i<j.length();i++){
            try {
                JSONObject json = j.getJSONObject(i);
                students1.add(json.getString(Config1.TAG_TEAMNAME));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        runnerupteam.setAdapter(null);
        runnerupteam.setAdapter(new ArrayAdapter<String>(Results.this, android.R.layout.simple_spinner_dropdown_item, students1));
    }

    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public void addResults() {
        Log.d("eventname",eventspinner.getSelectedItem().toString());
        if (!isOnline()) {
            Toast.makeText(getApplicationContext(), "Please enable your data", Toast.LENGTH_SHORT).show();
        } else {

            RequestQueue queue;
            queue = Volley.newRequestQueue(Results.this);
            String response = null;
            final String finalResponse = response;

            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //pd.hide();
                            showSnackbar(response);

                            if (response.contains("Successfully")) {
                               Toast.makeText(getApplicationContext(),"Results added successfully",Toast.LENGTH_SHORT).show();

                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("ErrorResponse", error.toString());
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {

                    Map<String, String> params = new HashMap<String, String>();


                    params.put("eventspinner", eventspinner.getSelectedItem().toString());
                    params.put("winningteam", winningteam.getSelectedItem().toString());
                    params.put("runnerupteam", runnerupteam.getSelectedItem().toString());


                    return params;
                }
            };
            postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(postRequest);
        }
    }


    public void showSnackbar(String stringsnackbar){
        snackbar.make(findViewById(android.R.id.content),stringsnackbar.toString(), Snackbar.LENGTH_SHORT)
                .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                .show();
    }
}

