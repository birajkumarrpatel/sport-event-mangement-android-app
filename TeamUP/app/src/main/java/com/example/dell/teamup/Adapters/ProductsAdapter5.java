package com.example.dell.teamup.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.teamup.R;
import com.example.dell.teamup.constructor_models.Product6;

import java.util.List;

/**
 * Created by Dell on 4/11/2018.
 */

public class ProductsAdapter5 extends RecyclerView.Adapter<ProductsAdapter5.ProductViewHolder>{
    private Context mCtx;
    private List<Product6> productList;

    public ProductsAdapter5(Context mCtx, List<Product6> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductsAdapter5.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.product_list4, null);
        return new ProductsAdapter5.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductsAdapter5.ProductViewHolder holder, int position) {
        Product6 product6 = productList.get(position);

        Glide.with(mCtx)
                .load(product6.getImage())
                .into(holder.image);
        holder.eventname.setText(product6.getEventname());
        holder.address.setText(product6.getAddress());
        holder.date.setText(product6.getDate());
        holder.sport.setText(product6.getSport());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder  {

        TextView eventname,address,date,sport;
        ImageView image;
        public ProductViewHolder(View itemView) {
            super(itemView);

            eventname = (TextView)itemView.findViewById(R.id.eventname);
            address = (TextView)itemView.findViewById(R.id.address);
            date = (TextView)itemView.findViewById(R.id.date);
            sport = (TextView)itemView.findViewById(R.id.sport);
            image = (ImageView)itemView.findViewById(R.id.image);


        }



    }
}
