package com.example.dell.teamup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Statitics extends AppCompatActivity {
    Toolbar toolbarWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statitics);
        toolbarWidget = (Toolbar) findViewById(R.id.stateticstoolbar);

        toolbarWidget.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        setSupportActionBar(toolbarWidget);
        toolbarWidget.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Statitics.super.onBackPressed();
                    }
                }

        );
    }
}
