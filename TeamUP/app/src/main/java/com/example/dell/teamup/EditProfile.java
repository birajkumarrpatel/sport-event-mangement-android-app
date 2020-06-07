package com.example.dell.teamup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class EditProfile extends AppCompatActivity {
    Toolbar toolbarWidget;
    Button change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        toolbarWidget = (Toolbar) findViewById(R.id.editprofiletoolbar);

        toolbarWidget.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        setSupportActionBar(toolbarWidget);
        toolbarWidget.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditProfile.super.onBackPressed();
                    }
                }

        );
        change = (Button)findViewById(R.id.changepassword);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ForgotPassword.class);
                startActivity(i);
            }
        });
    }
}
