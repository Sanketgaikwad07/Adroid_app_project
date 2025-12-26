package com.example.fake_store.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fake_store.R;

import com.example.fake_store.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    // Initialize with ArrayList to ensure mutability
    private List<Product> productList = new ArrayList<>();
    private List<Product> fullList = new ArrayList<>();

    public void setProducts(List<Product> products) {
        // Create new ArrayLists to ensure they are mutable
        this.productList = new ArrayList<>(products);
        this.fullList = new ArrayList<>(products);
        notifyDataSetChanged();
    }

    // Search filtering
    public void filter(String query) {
        productList.clear();
        if (query.isEmpty()) {
            productList.addAll(fullList);
        } else {
            query = query.toLowerCase().trim();
            for (Product product : fullList) {
                // Filter by Title OR Price
                if (product.getTitle().toLowerCase().contains(query) || 
                    String.valueOf(product.getPrice()).contains(query)) {
                    productList.add(product);
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.title.setText(product.getTitle());
        holder.price.setText("₹ " + product.getPrice());

        // Picasso Image Loading
        Picasso.get()
                .load(product.getImage())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, price;
        ImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            price = itemView.findViewById(R.id.tvPrice);
            image = itemView.findViewById(R.id.ivImage);
        }
    }
}