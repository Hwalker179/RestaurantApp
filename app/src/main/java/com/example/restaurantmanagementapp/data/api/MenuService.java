package com.example.restaurantmanagementapp.data.api;

import com.example.restaurantmanagementapp.data.model.MenuItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MenuService {
    @GET("menu")
    Call<List<MenuItem>> getMenuItems();

    @POST("menu")
    Call<MenuItem> addMenuItem(@Body MenuItem menuItem);
}
