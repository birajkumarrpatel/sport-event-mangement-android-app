package com.example.dell.teamup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dell.teamup.Adapters.ProductAdapter8;
import com.example.dell.teamup.constructor_models.Product8;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShowResults extends AppCompatActivity {
    Toolbar toolbarWidget;
    private static final String URL_PRODUCTS = "http://teamupyou.xyz/Api7.php";
    List<Product8> productList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_results);
        toolbarWidget = (Toolbar) findViewById(R.id.showresultstoolbar);

        toolbarWidget.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        setSupportActionBar(toolbarWidget);
        toolbarWidget.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShowResults.super.onBackPressed();
                    }
                }

        );
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
                                productList.add(new Product8(
                                        product1.getString("eventspinner"),
                                        product1.getString("winningteam"),
                                        product1.getString("runnerupteam")

                                ));
                                Log.d("runner",product1.getString("runnerupteam"));
                            }

                            //creating adapter object and setting it to recyclerview
                            ProductAdapter8 adapter = new ProductAdapter8(ShowResults.this, productList);
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
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
