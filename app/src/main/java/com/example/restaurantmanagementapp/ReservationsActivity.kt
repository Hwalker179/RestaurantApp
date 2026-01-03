package com.example.restaurantmanagementapp

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ReservationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservations)

        val backButton: Button = findViewById(R.id.btnBack)
        val logoutButton: Button = findViewById(R.id.btnLogout)

        backButton.setOnClickListener {
            finish()
        }

        logoutButton.setOnClickListener {
            Toast.makeText(this, "Logging out...", Toast.LENGTH_SHORT).show()
            // Remember to add the full logout logic here later
        }
    }
}
