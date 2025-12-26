package com.example.fake_store.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fake_store.R;
import com.example.fake_store.adapter.ProductAdapter;
import com.example.fake_store.model.Product;
import com.example.fake_store.repository.ProductRepository;
import com.example.fake_store.utils.NetworkUtils;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ProductAdapter adapter;
    private ProductRepository repository;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ProductAdapter();
        recyclerView.setAdapter(adapter);

        // Initialize SearchView
        searchView = findViewById(R.id.searchView);
        setupSearchView();

        // Check Network and Load Products
        repository = new ProductRepository(this);
        
        if (NetworkUtils.isNetworkAvailable(this)) {
            loadProducts();
        } else {
            Toast.makeText(this, "No Internet Connection. Loading Offline Data...", Toast.LENGTH_LONG).show();
            loadOfflineProducts();
        }
    }

    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return false;
            }
        });
    }

    private void loadProducts() {
        repository.getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Product> products = response.body();
                    adapter.setProducts(products);
                    
                    // Cache data for offline use
                    new Thread(() -> repository.saveProducts(products)).start();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to load products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void loadOfflineProducts() {
        // Load from Room Database
        new Thread(() -> {
            List<Product> offlineProducts = repository.getCachedProducts();
            runOnUiThread(() -> {
                if (offlineProducts != null && !offlineProducts.isEmpty()) {
                    adapter.setProducts(offlineProducts);
                } else {
                    Toast.makeText(MainActivity.this, "No offline data available", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }
}