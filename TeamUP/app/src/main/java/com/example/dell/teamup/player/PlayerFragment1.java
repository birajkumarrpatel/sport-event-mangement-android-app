package com.example.dell.teamup.player;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dell.teamup.Home;
import com.example.dell.teamup.Home1;
import com.example.dell.teamup.MainActivity;
import com.example.dell.teamup.captain.HomeCap;
import com.example.dell.teamup.constructor_models.Product6;
import com.example.dell.teamup.Adapters.ProductsAdapter5;
import com.example.dell.teamup.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dell on 3/29/2018.
 */

public class PlayerFragment1 extends Fragment implements View.OnClickListener{
    View myView;
    FloatingActionButton idFab, idFab1;
    TextView febmessage;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;
    private Boolean isFabOpen = false;
    private static final String URL_PRODUCTS = "http://teamupyou.xyz/Api5.php";
    List<Product6> productList;
    RecyclerView recyclerView;
    Context c;
    Snackbar snackbar;
    private static final String url = "http://teamupyou.xyz/constraint.php";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.player_fragment1, container, false);

        c=getActivity();
        recyclerView = myView.findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(c));
        productList = new ArrayList<>();
        loadProducts();
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
                                                    JSONObject product1 = array.getJSONObject(i);

                                                    //adding the product to product list
                                                    productList.add(new Product6(
                                                            product1.getString("eventname"),
                                                            product1.getString("address"),
                                                            product1.getString("date"),
                                                            product1.getString("sport"),
                                                            product1.getString("image")
                                                    ));
                                                }

                                                //creating adapter object and setting it to recyclerview
                                                ProductsAdapter5 adapter = new ProductsAdapter5(getActivity(), productList);
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
