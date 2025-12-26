package com.example.fake_store.network;

import com.example.fake_store.model.Product;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("products")
    Call<List<Product>> getProducts();
}
