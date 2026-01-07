package com.example.restaurantmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GuestHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_home);

        Button btnMenu = findViewById(R.id.btnMenu);
        Button btnReservations = findViewById(R.id.btnReservations);
        Button btnAccount = findViewById(R.id.btnAccount);
        Button btnLogout = findViewById(R.id.btnLogout);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuestHomeActivity.this, Menu.class);
                startActivity(intent);
            }
        });

        btnReservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuestHomeActivity.this, ReservationsActivity.class);
                intent.putExtra("user_type", "guest");
                startActivity(intent);
            }
        });

        btnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuestHomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}