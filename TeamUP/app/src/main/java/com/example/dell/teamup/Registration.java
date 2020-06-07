package com.example.dell.teamup;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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

public class Registration extends AppCompatActivity {

    EditText fullname, mobileno, password, confirmpassword, emailid;
    TextView login;
    Button register;
    RadioGroup g;
    private static String url = "http://teamupyou.xyz/signup.php";
    String b;
    private Snackbar snackbar;
    CheckBox terms;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        login = (TextView) findViewById(R.id.already_user);
        fullname = (EditText) findViewById(R.id.fullName);
        mobileno = (EditText) findViewById(R.id.mobileNumber);
        password = (EditText) findViewById(R.id.password);
        confirmpassword = (EditText) findViewById(R.id.confirmPassword);
        register = (Button) findViewById(R.id.signUpBtn);
        g = (RadioGroup) findViewById(R.id.gen_g);
        terms=(CheckBox)findViewById(R.id.terms_conditions);

        emailid = (EditText) findViewById(R.id.userEmailId);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Registration.this, MainActivity.class);
                startActivity(i);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(terms.isChecked()==true)
                {
                    signupRequest();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please Accept Terms & Conditions",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
    public void signupRequest() {
        //pd.setMessage("Signing Up...");
        //pd.show();
        if (!isOnline()) {
            Toast.makeText(getApplicationContext(), "Please enable your data", Toast.LENGTH_SHORT).show();
        } else {

            if (fullname.getText().length() == 0) {
                fullname.setError(getText(R.string.error_name));
                fullname.requestFocus();
            } else {
                fullname.setError(null);
            }
            if (emailid.getText().length() == 0) {
                emailid.setError(getText(R.string.error_email));
                emailid.requestFocus();
            } else {
                emailid.setError(null);
            }
            if (mobileno.getText().length() == 0) {
                mobileno.setError(getText(R.string.error_mobile));
                mobileno.requestFocus();
            } else {
                mobileno.setError(null);
            }
            if (password.getText().length() == 0) {
                password.setError(getText(R.string.error_password));
                password.requestFocus();
            } else {
                password.setError(null);
            }
            if (confirmpassword.getText().length() == 0) {
                confirmpassword.setError(getText(R.string.error_confirmpassword));
                confirmpassword.requestFocus();
            } else {
                confirmpassword.setError(null);
            }
            if (password.getText() == confirmpassword.getText()) {
                password.setError(getText(R.string.error_password));
                confirmpassword.setError(getText(R.string.error_password));
                password.requestFocus();
                confirmpassword.requestFocus();
            } else {
                password.setError(null);
                confirmpassword.setError(null);
            }
            if (emailid.getError() == null && mobileno.getError() == null && emailid.getError() == null && password.getError() == null && confirmpassword.getError() == null) {

                RequestQueue queue;
                queue = Volley.newRequestQueue(Registration.this);
                String response = null;
                final String finalResponse = response;

                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //pd.hide();
                                showSnackbar(response);

                                if (response.equals("Successfully Signed In")) {
                                    Intent intent = new Intent(getApplicationContext(), Registration2.class);
                                    intent.putExtra("Status",emailid.getText().toString());
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
                        String gender = ((RadioButton) findViewById(g.getCheckedRadioButtonId())).getText().toString();
                        Map<String, String> params = new HashMap<String, String>();

                        params.put("fullname", fullname.getText().toString());
                        params.put("emailid", emailid.getText().toString());
                        params.put("mobileno", mobileno.getText().toString());
                        params.put("password", password.getText().toString());
                        params.put("confirmpassword", confirmpassword.getText().toString());
                        params.put("gender", gender);

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
