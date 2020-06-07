package com.example.dell.teamup.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.teamup.R;
import com.example.dell.teamup.constructor_models.Product7;

import java.util.List;

/**
 * Created by Dell on 4/11/2018.
 */

public class ProductsAdapter6 extends RecyclerView.Adapter<ProductsAdapter6.ProductViewHolder> {

    private Context mCtx;
    private List<Product7> productList;

    public ProductsAdapter6(Context mCtx, List<Product7> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductsAdapter6.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.product_list5, null);
        return new ProductsAdapter6.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductsAdapter6.ProductViewHolder holder, int position) {
        Product7 product7 = productList.get(position);

        holder.teamname.setText(product7.getTeamname());
        holder.captain.setText(product7.getCaptain());

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


        }



    }
}
