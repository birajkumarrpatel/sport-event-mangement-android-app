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
import com.example.dell.teamup.constructor_models.Product5;

import java.util.List;

/**
 * Created by Dell on 4/7/2018
 */

public class ProductsAdapter4 extends RecyclerView.Adapter<ProductsAdapter4.ProductViewHolder> {
    private Context mCtx;
    private List<Product5> productList;

    public ProductsAdapter4(Context mCtx, List<Product5> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductsAdapter4.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.product_list3, null);
        return new ProductsAdapter4.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductsAdapter4.ProductViewHolder holder, int position) {
        Product5 product5 = productList.get(position);

        Glide.with(mCtx)
                .load(product5.getImage())
                .into(holder.image);
        holder.teamname.setText(product5.getTeamname());
        holder.captain.setText(product5.getCaptain());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder  {

        TextView teamname,captain;
        ImageView image;
        public ProductViewHolder(View itemView) {
            super(itemView);

            teamname = (TextView) itemView.findViewById(R.id.teamname);
            captain = (TextView) itemView.findViewById(R.id.captain);
            image = (ImageView)itemView.findViewById(R.id.image);


        }



    }
}
