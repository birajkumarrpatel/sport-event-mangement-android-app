package com.example.dell.teamup.organizer;

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
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.example.dell.teamup.captain.EventRegister;
import com.example.dell.teamup.R;
import com.example.dell.teamup.Registration2;

import java.util.HashMap;
import java.util.Map;

public class OrganizerRegister extends AppCompatActivity {

    EditText eventname,zipcode,organizationname;
    Spinner countryname,timezone,sport;
    Button submit;
    String country1,timezone1,sport1;
    ImageView icon_submit;
    private Snackbar snackbar;
    String url="http://teamupyou.xyz/organizer.php";
    Toolbar toolbarWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizer_register);

        toolbarWidget = (Toolbar) findViewById(R.id.organizertoolbar);

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

        organizationname = (EditText)findViewById(R.id.organization);
        zipcode = (EditText) findViewById(R.id.zipcode);
        countryname = (Spinner) findViewById(R.id.countryname);
        timezone = (Spinner) findViewById(R.id.timezone);
        sport = (Spinner) findViewById(R.id.sport);
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
            if (organizationname.getText().length() == 0) {
                organizationname.setError(getText(R.string.error_organization));
                organizationname.requestFocus();
            } else {
                organizationname.setError(null);
            }
            if (zipcode.getText().length() == 0) {
                zipcode.setError(getText(R.string.zip_code));
                zipcode.requestFocus();
            } else {
                zipcode.setError(null);
            }
            if (countryname.getSelectedItemPosition() == 0) {
                TextView errorText = (TextView) countryname.getSelectedView();
                errorText.setError("");
                errorText.setTextColor(Color.RED);
                errorText.setText(getText(R.string.error_country));
                country1 = errorText.getText().toString();
                errorText.requestFocus();
            } else {
                TextView errorText = (TextView) countryname.getSelectedView();
                errorText.setError(null);
                country1 = null;
            }
            if (timezone.getSelectedItemPosition() == 0) {
                TextView errorText = (TextView) timezone.getSelectedView();
                errorText.setError("");
                errorText.setTextColor(Color.RED);
                errorText.setText(getText(R.string.error_timezone));
                timezone1 = errorText.getText().toString();
                errorText.requestFocus();
            } else {
                TextView errorText = (TextView) timezone.getSelectedView();
                errorText.setError(null);
                timezone1 = null;
            }
            if (sport.getSelectedItemPosition() == 0) {
                TextView errorText = (TextView) sport.getSelectedView();
                errorText.setError("");
                errorText.setTextColor(Color.RED);
                errorText.setText(getText(R.string.error_sport));
                sport1 = errorText.getText().toString();
                errorText.requestFocus();
            } else {
                TextView errorText = (TextView) sport.getSelectedView();
                errorText.setError(null);
                sport1 = null;
            }

            if (organizationname.getError() == null && zipcode.getError() == null && country1 == null && timezone1 == null && sport1 == null) {

                RequestQueue queue;
                queue = Volley.newRequestQueue(OrganizerRegister.this);
                String response = null;
                final String finalResponse = response;

                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //pd.hide();
                                showSnackbar(response);

                                if (response.contains("Successfully")) {
                                    startActivity(new Intent(OrganizerRegister.this, EventRegister.class));
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

                        params.put("organizationname",organizationname.getText().toString());
                        params.put("zipcode", zipcode.getText().toString());
                        params.put("sport", sport.getSelectedItem().toString());
                        params.put("timezone", timezone.getSelectedItem().toString());
                        params.put("country", countryname.getSelectedItem().toString());

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
                        Intent i = new Intent(getApplicationContext(),OrganizerRegister.class);
                        startActivity(i);
                    }
                });

        alertDialogBuilder.setNegativeButton("DISCARD",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i =new Intent(getApplicationContext(),Registration2.class);
                startActivity(i);
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
