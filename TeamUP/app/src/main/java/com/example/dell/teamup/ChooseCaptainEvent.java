package com.example.dell.teamup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dell.teamup.Adapters.ProductsAdapter9;
import com.example.dell.teamup.captain.HomeCap;
import com.example.dell.teamup.constructor_models.Product1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChooseCaptainEvent extends AppCompatActivity {
    private static final String URL_PRODUCTS = "http://teamupyou.xyz/Api1.php";
    List<Product1> productList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_captain_event);
        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productList = new ArrayList<>();
        loadProducts();
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
                                productList.add(new Product1(
                                        product1.getString("eventname"),
                                        product1.getString("image"),
                                        product1.getString("location")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            ProductsAdapter9 adapter = new ProductsAdapter9(getApplicationContext(), productList);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent i = new Intent(getApplicationContext(),NewTeam.class);
                                    startActivity(i);
                                }
                            });
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
        Volley.newRequestQueue(this).add(stringRequest);
    }

}
