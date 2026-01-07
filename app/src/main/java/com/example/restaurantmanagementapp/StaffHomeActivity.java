package com.example.restaurantmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StaffHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_home);

        Button btnMenu = findViewById(R.id.btnMenu);
        Button btnReservations = findViewById(R.id.btnReservations);
        Button btnLogout = findViewById(R.id.btnLogout);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StaffHomeActivity.this, Menu.class);
                intent.putExtra("user_type", "staff");
                startActivity(intent);
            }
        });

        btnReservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StaffHomeActivity.this, ReservationsActivity.class);
                intent.putExtra("user_type", "staff");
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StaffHomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}