package com.example.restaurantmanagementapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class ReservationsActivity : BaseActivity() {

    override val pageTitle: String = "Reservations"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservations)

        val makeReservationButton: Button = findViewById(R.id.btnMakeReservation)
        val userType = intent.getStringExtra("user_type")

        if (userType == "guest") {
            makeReservationButton.visibility = View.VISIBLE

            makeReservationButton.setOnClickListener {
                val intent = Intent(this, MakeReservationActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
