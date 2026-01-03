package com.example.restaurantmanagementapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// Make sure these are imported
import com.example.restaurantmanagementapp.ReservationsActivity
import com.example.restaurantmanagementapp.RegisterActivity // Import RegisterActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sessionManager = SessionManager(this)

        val usernameInput: EditText = findViewById(R.id.editTextText)
        val passwordInput: EditText = findViewById(R.id.editTextTextPassword)
        val loginButton: Button = findViewById(R.id.Login)
        val registerButton: Button = findViewById(R.id.button2)

        // --- Login Logic (stays the same) ---
        loginButton.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Staff Login
            if (username == SessionManager.STAFF_USERNAME && password == SessionManager.STAFF_PASSWORD) {
                Toast.makeText(this, "Staff login successful!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ReservationsActivity::class.java)
                startActivity(intent)
                // Guest Login
            } else if (username == sessionManager.getUsername() && sessionManager.getUsername() != null) {
                Toast.makeText(this, "Welcome back, $username!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ReservationsActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }

        // --- FIX: Change Register Button to Navigate ---
        registerButton.setOnClickListener {
            // Create an Intent to open the RegisterActivity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
