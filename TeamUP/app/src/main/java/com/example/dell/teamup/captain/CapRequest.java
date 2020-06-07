package com.example.dell.teamup.captain;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dell.teamup.Adapters.ProductsAdapter11;
import com.example.dell.teamup.constructor_models.Product4;
import com.example.dell.teamup.Adapters.ProductsAdapter3;
import com.example.dell.teamup.R;
import com.example.dell.teamup.constructor_models.Product9;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 5/8/2018.
 */

public class CapRequest extends android.support.v4.app.Fragment {
    View myView;
    private static final String URL_PRODUCTS = "http://teamupyou.xyz/Api8.php";
    List<Product9> productList;
    RecyclerView recyclerView;
    Context c;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.tabcaprequest, container, false);
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
                                JSONObject product4 = array.getJSONObject(i);

                                //adding the product to product list
                                productList.add(new Product9(
                                        product4.getString("name"),
                                        product4.getString("DOB"),
                                        product4.getString("city"),
                                        product4.getString("status")

                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            ProductsAdapter11 adapter = new ProductsAdapter11(getActivity(), productList);
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
        Volley.newRequestQueue(c.getApplicationContext()).add(stringRequest);
    }
}