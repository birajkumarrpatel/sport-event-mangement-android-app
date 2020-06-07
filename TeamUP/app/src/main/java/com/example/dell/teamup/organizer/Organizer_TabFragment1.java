package com.example.dell.teamup.organizer;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dell.teamup.captain.EventRegister;
import com.example.dell.teamup.constructor_models.Product;
import com.example.dell.teamup.Adapters.ProductsAdapter;
import com.example.dell.teamup.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 3/29/2018.
 */

public class Organizer_TabFragment1 extends Fragment implements View.OnClickListener {
    View myView;
    FloatingActionButton idFab, idFab1;
    TextView febmessage;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;
    private Boolean isFabOpen = false;
    private static final String URL_PRODUCTS = "http://teamupyou.xyz/Api.php";
    List<Product> productList;
    RecyclerView recyclerView;
    Context c;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.organizer_tabfragment1, container, false);

        recyclerView = myView.findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(c));
        productList = new ArrayList<>();
        c=getActivity();
        loadProducts();
        idFab = (FloatingActionButton) myView.findViewById(R.id.idFabEmail);
        idFab1 = (FloatingActionButton) myView.findViewById(R.id.idFabMessage);
        febmessage = (TextView) myView.findViewById(R.id.messagenewfloat);
        fab_open = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_backward);
        idFab.setOnClickListener(this);
        idFab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(getActivity(), EventRegister.class);
                startActivity(i2);
            }
        });
        return myView;
    }

    private void loadProducts() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                productList.add(new Product(
                                        product.getString("eventname"),
                                        product.getString("sport"),
                                        product.getString("image"),
                                        product.getString("location"),
                                        product.getString("date")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            ProductsAdapter adapter = new ProductsAdapter(getActivity(), productList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(c).add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.idFabEmail:

                animateFAB();
                break;
            case R.id.idFabMessage:

                Log.d("Raj", "Fab 1");
                break;
        }
    }
    public void animateFAB(){

        if(isFabOpen){

            idFab.startAnimation(rotate_backward);
            idFab1.startAnimation(fab_close);
            febmessage.startAnimation(fab_close);
            idFab1.setClickable(false);
            isFabOpen = false;
            Log.d("Raj", "close");

        } else {

            idFab.startAnimation(rotate_forward);
            idFab1.startAnimation(fab_open);
            febmessage.startAnimation(fab_open);
            idFab1.setClickable(true);
            isFabOpen = true;
            Log.d("Raj","open");

        }
    }
}
