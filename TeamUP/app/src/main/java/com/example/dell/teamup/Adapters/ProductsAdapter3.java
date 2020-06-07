package com.example.dell.teamup.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
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
import com.example.dell.teamup.constructor_models.Product4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dell on 4/7/2018.
 */

public class ProductsAdapter3 extends RecyclerView.Adapter<ProductsAdapter3.ProductViewHolder> {
    private Context mCtx;
    private List<Product4> productList;
    String status="";
    Snackbar snackbar;
    private String url = "http://teamupyou.xyz/status.php";

    String show_status;

    public ProductsAdapter3(Context mCtx, List<Product4> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductsAdapter3.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.product_list2, null);
        return new ProductsAdapter3.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductsAdapter3.ProductViewHolder holder, final int position) {

        Product4 product4 = productList.get(position);
        holder.teamname.setText(product4.getTeamname());
        holder.captain.setText(product4.getCaptain());
        holder.event.setText(product4.getEventname());
        holder.approv.setVisibility(View.VISIBLE);

        /*if (status.equals("1")) {
        *//*
        } else
        {

        }*/




        holder.takeaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {



                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mCtx);
                alertDialogBuilder.setMessage("Team Approval");
                alertDialogBuilder.setPositiveButton("Approv",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                //holder.approv.setVisibility(View.VISIBLE);
                                status = "1";
                                holder.approv.setText("Team Approved");
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
                                                    Intent i = new Intent(mCtx,Home.class);
                                                    mCtx.startActivity(i);
                                                    Toast.makeText(mCtx, "Team Approved Successfully", Toast.LENGTH_SHORT).show();
                                                    ProductsAdapter3.this.notifyDataSetChanged();
                                                    ProductsAdapter3.this.notifyItemRangeChanged(position,productList.size());


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
                                        params.put("eventspinner", holder.event.getText().toString());
                                        params.put("status", String.valueOf(status));
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
                        holder.approv.setText("Team Rejected");
                        RequestQueue queue = Volley.newRequestQueue(mCtx);
                        String response = null;

                        final String finalResponse = response;

                        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        // pd.hide();

                                        if (response.contains("Successfully")) {
                                            //Intent i = new Intent(mCtx,Home.class);
                                            //mCtx.startActivity(i);
                                            Toast.makeText(mCtx,"Team Rejected Succesfully",Toast.LENGTH_SHORT).show();
                                            ProductsAdapter3.this.notifyDataSetChanged();
                                            ProductsAdapter3.this.notifyItemRangeChanged(position,productList.size());

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
                                params.put("eventspinner", holder.event.getText().toString());
                                params.put("status", String.valueOf(status));
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


        TextView teamname,captain,event,approv;
        Button takeaction;
        public ProductViewHolder(final View itemView) {
            super(itemView);

            teamname = (TextView) itemView.findViewById(R.id.teamname);
            captain = (TextView) itemView.findViewById(R.id.captain);
            event = (TextView) itemView.findViewById(R.id.eventname);
            approv = (TextView)itemView.findViewById(R.id.approv);
            takeaction = (Button)itemView.findViewById(R.id.takeaction);





        }
    }

}