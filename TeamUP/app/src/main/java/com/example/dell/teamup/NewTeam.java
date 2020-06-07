package com.example.dell.teamup;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.example.dell.teamup.captain.HomeCap;
import com.firebase.client.Config;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class NewTeam extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbarWidget;
    ImageView icon_submit;
    Spinner eventspinner,tshirtsize;
    EditText teamname,DOB,Address,City,Pincode,captain;
    private SimpleDateFormat dateFormatter;
    private DatePickerDialog datePickerDialog;
    String eventspinner1,tshirtsize1,queryparam;
    Snackbar snackbar;
    String url="http://teamupyou.xyz/team.php";
    private static final String PATH_TO_SERVER = "http://teamupyou.xyz/eventspinner.php";
    protected List<DataObject> spinnerData;
    private RequestQueue queue;
    TextView skip;
    ArrayList<String> students;
    JSONArray result;
    String image = "http://teamupyou.xyz/images/volleyball.png";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_team);
        queue = Volley.newRequestQueue(this);
        students = new ArrayList<String>();
        requestJsonObject();
        toolbarWidget = (Toolbar) findViewById(R.id.newteamtoolbar);

        toolbarWidget.setNavigationIcon(R.drawable.ic_close_black_24dp);

       setSupportActionBar(toolbarWidget);
        icon_submit = (ImageView)toolbarWidget.findViewById(R.id.icon_submit);
        icon_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registration();
            }
        });
        toolbarWidget.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        closemethod(v);
                    }
                }

        );
        eventspinner = (Spinner)findViewById(R.id.eventspinner);
        teamname = (EditText)findViewById(R.id.teamname);
        captain = (EditText)findViewById(R.id.captain);
        tshirtsize = (Spinner)findViewById(R.id.tshirtsize);
        DOB = (EditText)findViewById(R.id.DOB);
        Address = (EditText)findViewById(R.id.address);
        City = (EditText)findViewById(R.id.city);
        Pincode = (EditText)findViewById(R.id.pincode);
        skip = (TextView)findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Home1.class);
                startActivity(i);
            }
        });
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        DOB.setInputType(InputType.TYPE_NULL);
        DOB.requestFocus();
        setDateTimeField();
    }
    private void requestJsonObject(){
        StringRequest stringRequest = new StringRequest("http://teamupyou.xyz/eventspinner.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            j = new JSONObject(response);
                            result = j.getJSONArray(com.example.dell.teamup.Config.JSON_ARRAY);
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
        for(int i=0;i<j.length();i++){
            try {
                JSONObject json = j.getJSONObject(i);
                students.add(json.getString(com.example.dell.teamup.Config.TAG_EVENTNAME));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        eventspinner.setAdapter(new ArrayAdapter<String>(NewTeam.this, android.R.layout.simple_spinner_dropdown_item, students));
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
    private void registration() {
        if (!isOnline()) {
            Toast.makeText(getApplicationContext(), "Please enable your data", Toast.LENGTH_SHORT).show();
        } else {
            if (teamname.getText().length() == 0) {
                teamname.setError(getText(R.string.error_teamname));
                teamname.requestFocus();
            } else {
                teamname.setError(null);
            }
            if (captain.getText().length() == 0) {
                captain.setError(getText(R.string.error_captain));
                captain.requestFocus();
            } else {
                captain.setError(null);
            }
            if (tshirtsize.getSelectedItemPosition() == 0) {
                TextView errorText = (TextView) tshirtsize.getSelectedView();
                errorText.setError("");
                errorText.setTextColor(Color.RED);
                errorText.setText(getText(R.string.error_tshirt));
                tshirtsize1 = errorText.getText().toString();
                errorText.requestFocus();
            } else {
                TextView errorText = (TextView) tshirtsize.getSelectedView();
                errorText.setError(null);
                tshirtsize1 = null;
            }
            if (DOB.getText().length() == 0) {
                DOB.setError(getText(R.string.event_name));
                DOB.requestFocus();
            } else {
                DOB.setError(null);
            }
            if (Address.getText().length() == 0) {
                Address.setError(getText(R.string.zip_code));
                Address.requestFocus();
            } else {
                Address.setError(null);
            }
            if (City.getText().length() == 0) {
                City.setError(getText(R.string.zip_code));
                City.requestFocus();
            } else {
                City.setError(null);
            }
            if (Pincode.getText().length() == 0) {
                Pincode.setError(getText(R.string.zip_code));
                Pincode.requestFocus();
            } else {
                Pincode.setError(null);
            }




            if (teamname.getError() == null && captain.getError() == null && eventspinner1 == null && tshirtsize1 == null && DOB.getError() == null && Address.getError() == null && City.getError() == null && Pincode.getError() == null) {
                RequestQueue queue;
                queue = Volley.newRequestQueue(NewTeam.this);
                String response = null;
                final String finalResponse = response;

                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //pd.hide();
                                //showSnackbar(response);
                                Log.d("bcd",response);
                                startActivity(new Intent(NewTeam.this, HomeCap.class));
                                /*if (response.contains("Successfully")) {

                                }*/
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

                        params.put("eventspinner",eventspinner.getSelectedItem().toString());
                        params.put("teamname", teamname.getText().toString());
                        params.put("captain",captain.getText().toString());
                        params.put("tshirtsize", tshirtsize.getSelectedItem().toString());
                        params.put("DOB", DOB.getText().toString());
                        params.put("Address", Address.getText().toString());
                        params.put("City", City.getText().toString());
                        params.put("Pincode",Pincode.getText().toString());
                        params.put("image",image);
                        params.put("Status","0");

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
        snackbar.make(findViewById(android.R.id.content),stringsnackbar.toString(), Snackbar.LENGTH_SHORT)
                .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                .show();
    }
    public void closemethod(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to discard these changes?");
        alertDialogBuilder.setPositiveButton("KEEP EDITING",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent i = new Intent(getApplicationContext(),NewTeam.class);
                        startActivity(i);
                    }
                });

        alertDialogBuilder.setNegativeButton("DISCARD",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i =new Intent(getApplicationContext(),Home1.class);
                startActivity(i);
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
