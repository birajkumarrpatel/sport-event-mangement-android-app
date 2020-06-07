package com.example.dell.teamup;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dell.teamup.organizer.OrganizerRegister;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ScheduleMatch extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbarWidget;
    ImageView icon_submit;
    private SimpleDateFormat dateFormatter;
    private DatePickerDialog datePickerDialog;
    EditText datepick,timepick,venue,description;
    private static final String PATH_TO_SERVER = "http://teamupyou.xyz/teamspinner.php";
    private static final String PATH_TO_SERVER1 = "http://teamupyou.xyz/eventspinner.php";
    protected List<DataObject2> spinnerData;
    protected List<DataObject> spinnerData1;
    private RequestQueue queue;
    Snackbar snackbar;
    String url="http://teamupyou.xyz/schedulematch.php";
    String eventname,team1,team2;
    ArrayList<String> students;
    ArrayList<String> students1;
    JSONArray result;
    Spinner eventname1,teamspinner1,teamspinner2;


    Spinner spinner,spinner1,spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_match);
        queue = Volley.newRequestQueue(this);
        requestJsonObject2();
        requestJsonObject();
        requestJsonObject1();


        toolbarWidget = (Toolbar) findViewById(R.id.matchtoolbar);

        toolbarWidget.setNavigationIcon(R.drawable.ic_close_black_24dp);

        setSupportActionBar(toolbarWidget);
        icon_submit = (ImageView)toolbarWidget.findViewById(R.id.icon_submit);
        icon_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registration();
            }
        });
        toolbarWidget.setNavigationOnClickListener(new View.OnClickListener() {
                                                       @Override
                                                       public void onClick(View v) {
                                                           closemethod(v);
                                                       }
                                                   }
        );
        students = new ArrayList<String>();
        students1 = new ArrayList<String>();
        eventname1 = (Spinner)findViewById(R.id.eventspinner);
        teamspinner1 = (Spinner)findViewById(R.id.teamname1spinner);
        teamspinner2 = (Spinner)findViewById(R.id.teamname2spinner);
        datepick = (EditText)findViewById(R.id.datepick);
        timepick = (EditText)findViewById(R.id.timepick);
        venue = (EditText)findViewById(R.id.venue);
        description = (EditText)findViewById(R.id.description);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        datepick.setInputType(InputType.TYPE_NULL);
        datepick.requestFocus();
        setDateTimeField();

        timepick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ScheduleMatch.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timepick.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
    }


    //When no item is selected this method would execute

    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
    private void registration() {
        if (!isOnline()) {
            Toast.makeText(getApplicationContext(), "Please enable your data", Toast.LENGTH_SHORT).show();
        } else {

                RequestQueue queue;
                queue = Volley.newRequestQueue(ScheduleMatch.this);
                String response = null;
                final String finalResponse = response;

                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //pd.hide();
                                showSnackbar(response);

                                if (response.contains("Successfully")) {
                                    startActivity(new Intent(ScheduleMatch.this, Home.class));
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

                        params.put("eventspinner",eventname1.getSelectedItem().toString());
                        params.put("team1",teamspinner1.getSelectedItem().toString());
                        params.put("team2",teamspinner2.getSelectedItem().toString());
                        params.put("date",datepick.getText().toString());
                        params.put("time",timepick.getText().toString());
                        params.put("Venue",venue.getText().toString());
                        params.put("description",description.getText().toString());

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
    private void requestJsonObject2(){
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
        eventname1.setAdapter(null);
        eventname1.setAdapter(new ArrayAdapter<String>(ScheduleMatch.this, android.R.layout.simple_spinner_dropdown_item, students));
    }

    private void requestJsonObject(){
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
        teamspinner1.setAdapter(null);
        teamspinner1.setAdapter(new ArrayAdapter<String>(ScheduleMatch.this, android.R.layout.simple_spinner_dropdown_item, students1));
    }
    private void requestJsonObject1(){
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
        teamspinner2.setAdapter(null);
        teamspinner2.setAdapter(new ArrayAdapter<String>(ScheduleMatch.this, android.R.layout.simple_spinner_dropdown_item, students1));
    }

    private void setDateTimeField() {
        datepick.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                datepick.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    public void onClick(View view) {
        if (view == datepick) {
            datePickerDialog.show();
        }
    }
    public void closemethod(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to discard these changes?");
        alertDialogBuilder.setPositiveButton("KEEP EDITING",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent i = new Intent(getApplicationContext(),OrganizerRegister.class);
                        startActivity(i);
                    }
                });

        alertDialogBuilder.setNegativeButton("DISCARD",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i =new Intent(getApplicationContext(),Home.class);
                startActivity(i);
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
