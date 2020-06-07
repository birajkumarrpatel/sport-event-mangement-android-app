package com.example.dell.teamup;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dell.teamup.organizer.OrganizerRegister;

import java.util.HashMap;
import java.util.Map;

public class Registration2 extends AppCompatActivity {

    CardView organizer,player,follower;
    private Snackbar snackbar;
    private static String url = "http://teamupyou.xyz/role.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration2);
        organizer=(CardView)findViewById(R.id.card_view_organizer);
        player=(CardView)findViewById(R.id.card_view_teamreg);
        follower=(CardView)findViewById(R.id.card_view_guest);
        organizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                organizerRole();
            }
        });
        player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerRole();
            }
        });
        follower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                followerrole();
            }
        });

    }
    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
    public void organizerRole() {
        Intent intent = getIntent();
        final String email = intent.getStringExtra("Status");

        //pd.setMessage("Signing In...");
        //pd.show();
        if (!isOnline()) {
            Toast.makeText(getApplicationContext(), "Please enable your data", Toast.LENGTH_SHORT).show();
        }

                RequestQueue queue = Volley.newRequestQueue(Registration2.this);
                String response = null;

                final String finalResponse = response;

                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // pd.hide();
                                showSnackbar(response);

                                if (response.contains("Successfully")) {
                                    startActivity(new Intent(getApplication(), OrganizerRegister.class));

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //pd.hide();
                                Log.d("ErrorResponse", finalResponse);
                            }
                        }
                ) {
                    protected Map<String, String> getParams() {
                        String role = "Organizer";
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("emailid",email);
                        params.put("role",role);
                        return params;
                    }
                };
                postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                queue.add(postRequest);
            }

    public void playerRole() {

        Intent intent = getIntent();
        final String email = intent.getStringExtra("Status");

        //pd.setMessage("Signing In...");
        //pd.show();
        if (!isOnline()) {
            Toast.makeText(getApplicationContext(), "Please enable your data", Toast.LENGTH_SHORT).show();
        }

        RequestQueue queue = Volley.newRequestQueue(Registration2.this);
        String response = null;

        final String finalResponse = response;

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // pd.hide();
                        showSnackbar(response);

                        if (response.contains("Successfully")) {
                            startActivity(new Intent(getApplication(), PlayerProfile.class));

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //pd.hide();
                        Log.d("ErrorResponse", finalResponse);
                    }
                }
        ) {
            protected Map<String, String> getParams() {
                String role = "Player";
                Map<String, String> params = new HashMap<String, String>();
                params.put("emailid",email);
                params.put("role",role);
                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);
    }
   public void followerrole() {
       Intent intent = getIntent();
       final String email = intent.getStringExtra("Status");

       //pd.setMessage("Signing In...");
       //pd.show();
       if (!isOnline()) {
           Toast.makeText(getApplicationContext(), "Please enable your data", Toast.LENGTH_SHORT).show();
       }

       RequestQueue queue = Volley.newRequestQueue(Registration2.this);
       String response = null;

       final String finalResponse = response;

       StringRequest postRequest = new StringRequest(Request.Method.POST, url,
               new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       // pd.hide();
                       showSnackbar(response);

                       if (response.contains("Successfully")) {
                           startActivity(new Intent(getApplication(), ChooseCaptainEvent.class));

                       }
                   }
               },
               new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                       //pd.hide();
                       Log.d("ErrorResponse", finalResponse);
                   }
               }
       ) {
           protected Map<String, String> getParams() {
               String role = "Captain";
               Map<String, String> params = new HashMap<String, String>();
               params.put("emailid",email);
               params.put("role",role);
               return params;
           }
       };
       postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
       queue.add(postRequest);
    }

    public void showSnackbar(String stringSnackbar) {
        snackbar.make(findViewById(android.R.id.content), stringSnackbar.toString(), Snackbar.LENGTH_SHORT)
                .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                .show();
    }
}
