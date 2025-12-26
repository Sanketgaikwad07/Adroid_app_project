package com.example.fake_store.repository;

import android.content.Context;

import com.example.fake_store.database.AppDatabase;
import com.example.fake_store.database.ProductDao;
import com.example.fake_store.model.Product;
import com.example.fake_store.network.ApiService;
import com.example.fake_store.network.RetrofitClient;

import java.util.List;
import retrofit2.Call;

public class ProductRepository {

    private ApiService apiService;
    private ProductDao productDao;

    public ProductRepository(Context context) {
        apiService = RetrofitClient.getInstance().create(ApiService.class);
        AppDatabase db = AppDatabase.getDatabase(context);
        productDao = db.productDao();
    }

    public Call<List<Product>> getProducts() {
        return apiService.getProducts();
    }
    
    public void saveProducts(List<Product> products) {
        productDao.deleteAllProducts();
        productDao.insertProducts(products);
    }

    public List<Product> getCachedProducts() {
        return productDao.getAllProducts();
    }
}