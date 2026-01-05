package com.example.restaurantmanagementapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurantmanagementapp.GuestHomeActivity
import com.example.restaurantmanagementapp.RegisterActivity
import com.example.restaurantmanagementapp.StaffHomeActivity

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

        loginButton.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (username.isEmpty()) {
                Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (username.equals(SessionManager.STAFF_USERNAME, ignoreCase = true)) {
                if (password == SessionManager.STAFF_PASSWORD) {
                    Toast.makeText(this, "Staff login successful!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, StaffHomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Invalid password for staff account", Toast.LENGTH_SHORT).show()
                }
            } else {
                sessionManager.saveGuestLogin(username)
                Toast.makeText(this, "Welcome, $username!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, GuestHomeActivity::class.java)
                startActivity(intent)
            }
        }

        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}