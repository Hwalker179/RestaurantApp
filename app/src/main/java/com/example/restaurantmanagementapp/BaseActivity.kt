package com.example.restaurantmanagementapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    lateinit var sessionManager: SessionManager

    abstract val pageTitle: String

    override fun setContentView(@LayoutRes layoutResID: Int) {
        super.setContentView(layoutResID)

        sessionManager = SessionManager(this)

        val titleView: TextView? = findViewById(R.id.tvTitle)
        titleView?.text = pageTitle

        val backButton: Button? = findViewById(R.id.btnBack)
        backButton?.setOnClickListener {
            finish()
        }

        val logoutButton: Button? = findViewById(R.id.btnLogout)
        logoutButton?.setOnClickListener {
            handleLogout()
        }
    }

    private fun handleLogout() {
        Toast.makeText(this, "Logging out...", Toast.LENGTH_SHORT).show()
        sessionManager.clearSession()
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}
