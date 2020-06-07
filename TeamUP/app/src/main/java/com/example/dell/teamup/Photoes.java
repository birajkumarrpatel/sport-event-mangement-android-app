package com.example.dell.teamup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class Photoes extends AppCompatActivity  {

    Toolbar toolbarWidget;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoes);

        toolbarWidget = (Toolbar) findViewById(R.id.addphotostoolbar);

        toolbarWidget.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        setSupportActionBar(toolbarWidget);
        toolbarWidget.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Photoes.super.onBackPressed();
                    }
                }

        );

    }
}
