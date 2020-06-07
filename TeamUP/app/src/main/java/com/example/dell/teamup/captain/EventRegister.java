package com.example.dell.teamup.captain;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dell.teamup.Home;
import com.example.dell.teamup.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class EventRegister extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbarWidget;
    ImageView icon_submit;
    EditText eventname, location, address, datepick, timepick;
    Spinner sport;
    String sport1;
    Snackbar snackbar;
    String url = "http://teamupyou.xyz/eventregister.php";
    private SimpleDateFormat dateFormatter;
    private DatePickerDialog datePickerDialog;
    String image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_register);
        toolbarWidget = (Toolbar) findViewById(R.id.eventregistertoolbar);

        toolbarWidget.setNavigationIcon(R.drawable.ic_close_black_24dp);

        setSupportActionBar(toolbarWidget);
        icon_submit = (ImageView) toolbarWidget.findViewById(R.id.icon_submit);
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
        eventname = (EditText) findViewById(R.id.eventname);
        sport = (Spinner) findViewById(R.id.sport);
        location = (EditText) findViewById(R.id.location);
        address = (EditText) findViewById(R.id.address);
        datepick = (EditText) findViewById(R.id.datepick);
        timepick = (EditText) findViewById(R.id.timepick);


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
                mTimePicker = new TimePickerDialog(EventRegister.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timepick.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
                if(sport.getSelectedItem().toString() == "Cricket")
                {
                    image = "http://teamupyou.xyz/images/cricketball.png";
                }
                else if(sport.getSelectedItem().toString() == "Volleyball")
                {
                    image = "http://teamupyou.xyz/images/volleyball.png";
                }
                else if(sport.getSelectedItem().toString() == "Basketball")
                {
                    image = "http://teamupyou.xyz/images/basketball.png";
                }
                else if(sport.getSelectedItem().toString() == "Football")
                {
                    image = "http://teamupyou.xyz/images/football.png";
                }
                else
                {
                    image = "http://teamupyou.xyz/images/cricketball.png";
                }


            }
        });


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

    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    private void registration() {
        if (!isOnline()) {
            Toast.makeText(getApplicationContext(), "Please enable your data", Toast.LENGTH_SHORT).show();
        } else {
            if (eventname.getText().length() == 0) {
                eventname.setError(getText(R.string.event_name));
                eventname.requestFocus();
            } else {
                eventname.setError(null);
            }
            if (sport.getSelectedItemPosition() == 0) {
                TextView errorText = (TextView) sport.getSelectedView();
                errorText.setError("");
                errorText.setTextColor(Color.RED);
                errorText.setText(getText(R.string.error_country));
                sport1 = errorText.getText().toString();
                errorText.requestFocus();
            } else {
                TextView errorText = (TextView) sport.getSelectedView();
                errorText.setError(null);
                sport1 = null;
            }
            if (location.getText().length() == 0) {
                location.setError(getText(R.string.error_event));
                location.requestFocus();
            } else {
                location.setError(null);
            }
            if (address.getText().length() == 0) {
                address.setError(getText(R.string.zip_code));
                address.requestFocus();
            } else {
                address.setError(null);
            }
            if (datepick.getText().length() == 0) {
                datepick.setError(getText(R.string.zip_code));
                datepick.requestFocus();
            } else {
                datepick.setError(null);
            }
            if (timepick.getText().length() == 0) {
                timepick.setError(getText(R.string.zip_code));
                timepick.requestFocus();
            } else {
                timepick.setError(null);
            }
            Log.d("sport",sport.getSelectedItem().toString());


            if (eventname.getError() == null && sport1 == null && location.getError() == null && address.getError() == null && datepick.getError() == null && timepick.getError() == null) {

                RequestQueue queue;
                queue = Volley.newRequestQueue(EventRegister.this);
                String response = null;
                final String finalResponse = response;

                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //pd.hide();
                                showSnackbar(response);

                                if (response.contains("Successfully")) {
                                    startActivity(new Intent(EventRegister.this, Home.class));
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



                        params.put("eventname", eventname.getText().toString());
                        params.put("sport", sport.getSelectedItem().toString());
                        params.put("location", location.getText().toString());
                        params.put("address", address.getText().toString());
                        params.put("date", datepick.getText().toString());
                        params.put("time", timepick.getText().toString());
                        params.put("image",image.toString());

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

    public void showSnackbar(String stringsnackbar) {
        snackbar.make(findViewById(android.R.id.content), stringsnackbar.toString(), Snackbar.LENGTH_SHORT)
                .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                .show();
    }

    public void closemethod(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to discard these changes?");
        alertDialogBuilder.setPositiveButton("KEEP EDITING",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                      Intent i = new Intent(getApplicationContext(),EventRegister.class);
                      startActivity(i);
                    }
                });

        alertDialogBuilder.setNegativeButton("DISCARD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(getApplicationContext(), Home.class);
                startActivity(i);
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}