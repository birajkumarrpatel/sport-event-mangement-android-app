package com.example.dell.teamup;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText email,password;
    TextView forgot,signup;
    Button submit;
    private static String url = "http://teamupyou.xyz/login.php";
    private Snackbar snackbar;
    private ProgressDialog pd;
    private Session session;
    private Session1 session1;
    private Session2 session2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (EditText) findViewById(R.id.loginid);
        password = (EditText) findViewById(R.id.pass);
        forgot = (TextView) findViewById(R.id.forgot);
        signup = (TextView) findViewById(R.id.signuptext);
        submit = (Button) findViewById(R.id.login);
        session=new Session(this);
        session1 = new Session1(this);
        session2 = new Session2(this);
        if(session.loggedin()){
            startActivity(new Intent(MainActivity.this,Home.class));
            finish();
        }
        if(session1.loggedin()){
            startActivity(new Intent(MainActivity.this,Home1.class));
        }




        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Registration.class);
                startActivity(i);
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        submit.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        loginRequest();
    }

    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
    public boolean emailValidator(String email){
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9]+)*@[A-Za-z0-9]+(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public void loginRequest() {

        //pd.setMessage("Signing In...");
        //pd.show();
        if (!isOnline()) {
            Toast.makeText(getApplicationContext(), "Please enable your data", Toast.LENGTH_SHORT).show();
        } else {
            if (TextUtils.isEmpty(email.getText().toString().trim()) || TextUtils.isEmpty(password.getText().toString().trim())) {
                email.setError("Email can't be Empty");
                password.setError("Password can't be empty");
            } else if (!emailValidator(email.getText().toString())) {
                email.setError("Please enter valid email address");
            }

            if (email.getError() == null && password.getError() == null) {
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String response = null;

                final String finalResponse = response;

                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // pd.hide();
                                showSnackbar(response);

                                if(response.contains("Organizer"))
                                {
                                    session.setLoggedin(true);
                                    startActivity(new Intent(getApplicationContext(), Home.class));
                                }
                                else if(response.contains("Player"))
                                {
                                    session1.setLoggedin(true);
                                    startActivity(new Intent(getApplicationContext(), Home1.class));
                                }
                                else if (response.contains("Captain"))
                                {
                                    session2.setLoggedin(true);
                                    startActivity(new Intent(getApplicationContext(), HomeCap.class));
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
                        params.put("emailid", email.getText().toString());
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
    boolean twice;
    public void onBackPressed()
    {
        if(twice == true)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
        }
        Toast.makeText(getApplicationContext(),"Please press Back again to exit",Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                twice = false;
            }
        },3000);
        twice = true;
    }
}
