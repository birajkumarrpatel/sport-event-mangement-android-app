package com.example.dell.teamup.organizer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dell.teamup.constructor_models.Product4;
import com.example.dell.teamup.Adapters.ProductsAdapter3;
import com.example.dell.teamup.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 3/29/2018.
 */

public class Organizer_TabFragment2 extends Fragment {

    View myView;
    private static final String URL_PRODUCTS = "http://teamupyou.xyz/Api3.php";
    List<Product4> productList;
    RecyclerView recyclerView;
    Context c;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.organizer_tabfragment2, container, false);
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
                            Log.d("response",response.toString());
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product4 = array.getJSONObject(i);

                                //adding the product to product list
                                productList.add(new Product4(
                                        product4.getString("teamname"),
                                        product4.getString("captain"),
                                        product4.getString("eventspinner"),
                                        product4.getString("Status")

                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            ProductsAdapter3 adapter = new ProductsAdapter3(getActivity(), productList);
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
