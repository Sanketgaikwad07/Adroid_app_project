package com.example.fake_store.database;

import androidx.room.TypeConverter;
import com.example.fake_store.model.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class Converters {
    @TypeConverter
    public static Product.Rating fromString(String value) {
        Type listType = new TypeToken<Product.Rating>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromRating(Product.Rating rating) {
        Gson gson = new Gson();
        return gson.toJson(rating);
    }
}