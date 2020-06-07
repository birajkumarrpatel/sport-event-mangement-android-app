package com.example.dell.teamup.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.teamup.R;
import com.example.dell.teamup.constructor_models.Product3;

import java.util.List;

/**
 * Created by Dell on 4/6/2018.
 */

public class ProductsAdapter2 extends RecyclerView.Adapter<ProductsAdapter2.ProductViewHolder> {

    private Context mCtx;
    private List<Product3> productList;

    public ProductsAdapter2(Context mCtx, List<Product3> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductsAdapter2.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.product_list1, null);
        return new ProductsAdapter2.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductsAdapter2.ProductViewHolder holder,final int position) {
        final Product3 product2 = productList.get(position);






        holder.team1.setText(product2.getTeam1());
        holder.team2.setText(product2.getTeam2());
        holder.date.setText(product2.getDate());
        holder.time.setText(product2.getTime());
        holder.venue.setText(product2.getvenue());
        holder.description.setText(product2.getDescription());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productList.remove(position);
                notifyDataSetChanged();
                notifyItemRangeChanged(position,productList.size());

            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder  {

        TextView team1,team2,date,time,venue,description,delete;
        public ProductViewHolder(View itemView) {
            super(itemView);

            team1 = itemView.findViewById(R.id.teamname1);
            team2 = itemView.findViewById(R.id.teamname2);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            venue = itemView.findViewById(R.id.venue);
            description = itemView.findViewById(R.id.description);
            delete = itemView.findViewById(R.id.delete);
        }



    }
}
