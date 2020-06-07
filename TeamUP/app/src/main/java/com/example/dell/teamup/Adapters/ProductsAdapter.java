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
import com.example.dell.teamup.constructor_models.Product;

import java.util.List;

/**
 * Created by Dell on 4/1/2018.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

    private Context mCtx;
    private List<Product> productList;

    public ProductsAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.product_list, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);




        Glide.with(mCtx)
                .load(product.getImage())
                .into(holder.imageView);


        holder.textViewTitle.setText(product.getTitle());
        holder.textViewShortDesc.setText(product.getShortdesc());
        holder.city.setText(product.getLocation());
        holder.date.setText(product.getDate());
        holder.textViewTitle.setTextColor(mCtx.getResources().getColor(R.color.seagreen));
        holder.textViewShortDesc.setTextColor(mCtx.getResources().getColor(R.color.seagreen));
        holder.city.setTextColor(mCtx.getResources().getColor(R.color.seagreen));
        holder.date.setTextColor(mCtx.getResources().getColor(R.color.seagreen));



    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc,city,date;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            imageView = itemView.findViewById(R.id.imageView);
            date = itemView.findViewById(R.id.date);
            city = itemView.findViewById(R.id.city);
        }
    }
}
