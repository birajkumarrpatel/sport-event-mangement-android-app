package com.example.dell.teamup.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.dell.teamup.NewTeam;
import com.example.dell.teamup.R;
import com.example.dell.teamup.captain.HomeCap;
import com.example.dell.teamup.constructor_models.Product1;

import java.util.List;

public class ProductsAdapter9 extends RecyclerView.Adapter<ProductsAdapter9.ProductViewHolder> {


    private Context mCtx;
    private List<Product1> productList;

    public ProductsAdapter9(Context mCtx, List<Product1> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductsAdapter9.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.product_list, null);
        return new ProductsAdapter9.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductsAdapter9.ProductViewHolder holder, int position) {
        Product1 product1 = productList.get(position);




        Glide.with(mCtx)
                .load(product1.getImage())
                .into(holder.imageView);


        holder.textViewTitle.setText(product1.getEventname());
        holder.textViewShortDesc.setText(product1.getLocation());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder  {

        TextView textViewTitle, textViewShortDesc;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            imageView = itemView.findViewById(R.id.imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(mCtx,NewTeam.class);
                    mCtx.startActivity(i);
                }
            });
        }

    }

}
