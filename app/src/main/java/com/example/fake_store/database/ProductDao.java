package com.example.fake_store.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.fake_store.model.Product;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProducts(List<Product> products);

    @Query("SELECT * FROM products")
    List<Product> getAllProducts();

    @Query("DELETE FROM products")
    void deleteAllProducts();
}