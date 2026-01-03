package com.example.restaurantmanagementapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurantmanagementapp.MainActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        sessionManager = SessionManager(this)

        val backButton: Button = findViewById(R.id.btnBack)
        val logoutButton: Button = findViewById(R.id.btnLogout)
        val profileName: TextView = findViewById(R.id.tvProfileName)

        profileName.text = sessionManager.getUsername() ?: "Guest"

        backButton.setOnClickListener {
            finish()
        }

        logoutButton.setOnClickListener {
            Toast.makeText(this, "Logging out...", Toast.LENGTH_SHORT).show()

            sessionManager.clearSession()

            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}
