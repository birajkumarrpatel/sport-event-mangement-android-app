package com.example.dell.teamup;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ForgotPassword2 extends AppCompatActivity {
    EditText password;
    Button submit;
    Snackbar snackbar;
    private static String url = "http://teamupyou.xyz/forgot2.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password2);
        password = (EditText)findViewById(R.id.password);
        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotPasswordRequest();
            }
        });

    }
    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
    public void forgotPasswordRequest() {

        Intent intent = getIntent();
        final String email = intent.getStringExtra("Status");




        //pd.setMessage("Signing In...");
        //pd.show();
        if (!isOnline()) {
            Toast.makeText(getApplicationContext(), "Please enable your data", Toast.LENGTH_SHORT).show();
        } else {
            if (TextUtils.isEmpty(password.getText().toString().trim())) {
                password.setError("Password can't be empty");
            }

            if (password.getError() == null) {
                RequestQueue queue = Volley.newRequestQueue(ForgotPassword2.this);
                String response = null;

                final String finalResponse = response;

                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // pd.hide();
                                showSnackbar(response);

                                if (response.contains("Successfully")) {
                                    Intent intent = new Intent(getApplicationContext(), ForgotPassword3.class);
                                    intent.putExtra("Status1",email);
                                    startActivity(intent);

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


                        Map<String, String> params = new HashMap<String, String>();
                        params.put("emailid", email);
                        params.put("password", password.getText().toString());
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

    public void showSnackbar(String stringSnackbar) {
        snackbar.make(findViewById(android.R.id.content), stringSnackbar.toString(), Snackbar.LENGTH_SHORT)
                .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                .show();
    }
}
