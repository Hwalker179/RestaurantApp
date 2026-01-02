package com.example.restaurantmanagementapp.data.api;

import com.example.restaurantmanagementapp.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("read_all_users/{student_id}")
    Call<List<User>> getAllUsers(@Path("student_id") String studentId);
}
