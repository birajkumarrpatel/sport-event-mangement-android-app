package com.example.dell.teamup.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.teamup.R;
import com.example.dell.teamup.constructor_models.Product8;

import java.util.List;

/**
 * Created by Dell on 5/8/2018.
 */

public class ProductAdapter8 extends RecyclerView.Adapter<ProductAdapter8.ProductViewHolder> {
    private Context mCtx;
    private List<Product8> productList;

    public ProductAdapter8(Context mCtx, List<Product8> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductAdapter8.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.product_list6, null);
        return new ProductAdapter8.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductAdapter8.ProductViewHolder holder, int position) {
        Product8 product8 = productList.get(position);

        holder.eventSpinner.setText(product8.getEventSpinner());
        holder.winningTeam.setText(product8.getWinningTeam());
        holder.runnerUpTeam.setText(product8.getRunnerUp());
    }
    @Override
    public int getItemCount() {
        return productList.size();
    }
    class ProductViewHolder extends RecyclerView.ViewHolder  {
        TextView eventSpinner,winningTeam,runnerUpTeam;
        public ProductViewHolder(View itemView) {
            super(itemView);
            eventSpinner = (TextView) itemView.findViewById(R.id.eventSpinner);
            winningTeam = (TextView) itemView.findViewById(R.id.winningTeam);
            runnerUpTeam=(TextView) itemView.findViewById(R.id.runnerUpTeam);
        }
    }
}
