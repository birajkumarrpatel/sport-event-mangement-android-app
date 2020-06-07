package com.example.dell.teamup.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.example.dell.teamup.R;
import com.example.dell.teamup.captain.HomeCap;
import com.example.dell.teamup.constructor_models.Product3;
import com.example.dell.teamup.constructor_models.Product9;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dell on 5/9/2018.
 */

public class ProductsAdapter11 extends RecyclerView.Adapter<ProductsAdapter11.ProductViewHolder> {

    private Context mCtx;
    private List<Product9> productList;
    String status;
    private String url = "http://teamupyou.xyz/status1.php";

    public ProductsAdapter11(Context mCtx, List<Product9> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductsAdapter11.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.product_list8, null);
        return new ProductsAdapter11.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductsAdapter11.ProductViewHolder holder,final int position) {
        final Product9 product9 = productList.get(position);
        holder.name.setText(product9.getName());
        holder.DOB.setText(product9.getDOB());
        holder.city.setText(product9.getCity());
        holder.status.setText(product9.getStatus());
        holder.takeaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mCtx);
                alertDialogBuilder.setMessage("Player Approval");
                alertDialogBuilder.setPositiveButton("Approve",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                //holder.approv.setVisibility(View.VISIBLE);
                                status = "1";

                                RequestQueue queue = Volley.newRequestQueue(mCtx);
                                String response = null;

                                final String finalResponse = response;
                                //Log.d("teamname",holder.teamname.getText().toString());
                                //Log.d("status1",status);


                                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                // pd.hide();

                                                if (response.contains("Successfully")) {
                                                    Intent i = new Intent(mCtx,HomeCap.class);
                                                    mCtx.startActivity(i);
                                                    Toast.makeText(mCtx, "Player Approved Successfully", Toast.LENGTH_SHORT).show();
                                                    ProductsAdapter11.this.notifyDataSetChanged();
                                                    ProductsAdapter11.this.notifyItemRangeChanged(position,productList.size());


                                                }
                                            }
                                        },
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                //pd.hide();
                                                Log.d("ErrorResponse", finalResponse);
                                            }
                                        }
                                ) {
                                    protected Map<String, String> getParams() {
                                        Map<String, String> params = new HashMap<String, String>();
                                        params.put("name", holder.name.getText().toString());
                                        params.put("status", status);
                                        return params;
                                    }
                                };
                                postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                                queue.add(postRequest);
                                holder.takeaction.setEnabled(false);


                            }
                        });

                alertDialogBuilder.setNegativeButton("Reject",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        status = "0";

                        RequestQueue queue = Volley.newRequestQueue(mCtx);
                        String response = null;

                        final String finalResponse = response;

                        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        // pd.hide();

                                        if (response.contains("Successfully")) {
                                            Intent i = new Intent(mCtx,HomeCap.class);
                                            mCtx.startActivity(i);
                                            Toast.makeText(mCtx,"Player Rejected Succesfully",Toast.LENGTH_SHORT).show();
                                            ProductsAdapter11.this.notifyDataSetChanged();
                                            ProductsAdapter11.this.notifyItemRangeChanged(position,productList.size());

                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //pd.hide();
                                        Log.d("ErrorResponse", finalResponse);
                                    }
                                }
                        ) {
                            protected Map<String, String> getParams() {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("name", holder.name.getText().toString());
                                params.put("status", status);
                                return params;
                            }
                        };
                        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                        queue.add(postRequest);

                        holder.takeaction.setEnabled(false);


                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();



            }


        });

    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder  {

        TextView name,city,DOB,status;
        Button takeaction;
        public ProductViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            DOB = itemView.findViewById(R.id.DOB);
            city = itemView.findViewById(R.id.city);
            takeaction = itemView.findViewById(R.id.takeaction);
            status = itemView.findViewById(R.id.status);

        }



    }
}
