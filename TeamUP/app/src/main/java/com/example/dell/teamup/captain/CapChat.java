package com.example.dell.teamup.captain;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dell.teamup.Login;
import com.example.dell.teamup.R;

/**
 * Created by Dell on 5/8/2018.
 */

public class CapChat extends android.support.v4.app.Fragment {
    View myView;
    Button group,personal;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.tabcapchat, container, false);
        myView = inflater.inflate(R.layout.player_fragment4, container, false);
        group = (Button)myView.findViewById(R.id.groupchat);
        personal = (Button)myView.findViewById(R.id.personalchat);
        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),Login.class);
                startActivity(i);
            }
        });
        return myView;
    }
}