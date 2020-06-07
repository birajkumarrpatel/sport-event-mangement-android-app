package com.example.dell.teamup;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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

public class PlayerProfile extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbarWidget;
    ImageView icon_submit;
    EditText DOB,city,pincode,address,name;
    private SimpleDateFormat dateFormatter;
    private DatePickerDialog datePickerDialog;
    Spinner chooseevent,chooseteam;
    private static final String PATH_TO_SERVER = "http://teamupyou.xyz/eventspinner.php";
    private static final String PATH_TO_SERVER1 = "http://teamupyou.xyz/teamspinner.php";
    public static final String url = "http://teamupyou.xyz/playerprofile.php";
    protected List<DataObject> spinnerData;
    private RequestQueue queue;
    protected List<DataObject2> spinnerdata1;
    Snackbar snackbar;
    JSONArray result;
    ArrayList<String> students;
    ArrayList<String> students1;
    String chooseevent1,chooseteam1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_profile);
        toolbarWidget = (Toolbar) findViewById(R.id.playerprofiletoolbar);

        toolbarWidget.setNavigationIcon(R.drawable.ic_close_black_24dp);

        setSupportActionBar(toolbarWidget);
        icon_submit = (ImageView)toolbarWidget.findViewById(R.id.icon_submit);
        icon_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playerProfile();
            }
        });
        toolbarWidget.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PlayerProfile.super.onBackPressed();

                    }
                }

        );
        DOB = (EditText)findViewById(R.id.DOB);
        city = (EditText)findViewById(R.id.city);
        address = (EditText)findViewById(R.id.address);
        pincode = (EditText)findViewById(R.id.pincode);
        chooseevent = (Spinner)findViewById(R.id.chooseevent);
        chooseteam = (Spinner)findViewById(R.id.chooseteam);
        name = findViewById(R.id.name);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        DOB.setInputType(InputType.TYPE_NULL);
        DOB.requestFocus();
        setDateTimeField();
        students = new ArrayList<String>();
        students1 = new ArrayList<String>();
        requestJsonObject();
        requestJsonObject1();
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
        chooseevent.setAdapter(null);
        chooseevent.setAdapter(new ArrayAdapter<String>(PlayerProfile.this, android.R.layout.simple_spinner_dropdown_item, students));
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
        chooseteam.setAdapter(null);
        chooseteam.setAdapter(new ArrayAdapter<String>(PlayerProfile.this, android.R.layout.simple_spinner_dropdown_item, students1));
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

    private void setDateTimeField() {
        DOB.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                DOB.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    public void onClick(View view) {
        if (view == DOB) {
            datePickerDialog.show();
        }
    }
    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
    public void playerProfile()
    {
        if (!isOnline()) {
            Toast.makeText(getApplicationContext(), "Please enable your data", Toast.LENGTH_SHORT).show();
        } else {

            if (DOB.getText().length() == 0) {
                DOB.setError(getText(R.string.error_DOB));
                DOB.requestFocus();
            } else {
                DOB.setError(null);
            }
            if (city.getText().length() == 0) {
                city.setError(getText(R.string.error_city));
                city.requestFocus();
            } else {
                city.setError(null);
            }
            if (address.getText().length() == 0) {
                address.setError(getText(R.string.error_address));
                address.requestFocus();
            } else {
                address.setError(null);
            }
            if (pincode.getText().length() == 0) {
                pincode.setError(getText(R.string.error_pincode));
                pincode.requestFocus();
            } else {
                pincode.setError(null);
            }
            if (name.getText().length() == 0) {
                name.setError(getText(R.string.error_pincode));
                name.requestFocus();
            } else {
                name.setError(null);
            }

            if ( DOB.getError() == null && city.getError() == null && address.getError() == null && pincode.getError()==null && name.getError()==null ) {

                RequestQueue queue;
                queue = Volley.newRequestQueue(PlayerProfile.this);
                String response = null;
                final String finalResponse = response;

                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //pd.hide();
                                showSnackbar(response);

                                if (response.contains("Successfully")) {
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
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

                        params.put("chooseevent", chooseevent.getSelectedItem().toString());
                        params.put("chooseteam", chooseteam.getSelectedItem().toString());
                        params.put("name", name.getText().toString());
                        params.put("DOB", DOB.getText().toString());
                        params.put("city", city.getText().toString());
                        params.put("address", address.getText().toString());
                        params.put("pincode",pincode.getText().toString());


                        return params;
                    }
                };
                postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                queue.add(postRequest);
            } else {
                Toast.makeText(getApplicationContext(), "Please correct your data before submitting", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void showSnackbar(String stringsnackbar){
        snackbar.make(findViewById(android.R.id.content),stringsnackbar.toString(),Snackbar.LENGTH_SHORT)
                .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                .show();
    }
    }

