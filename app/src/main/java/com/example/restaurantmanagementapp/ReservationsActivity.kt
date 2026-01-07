package com.example.restaurantmanagementapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

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

        loadReservations()
    }

    override fun onResume() {
        super.onResume()
        loadReservations()
    }

    private fun loadReservations() {
        val reservationsListView: ListView = findViewById(R.id.lvBookings)
        val emptyMessage: TextView = findViewById(R.id.tvNoBookings)
        val reservation = sessionManager.getReservationDetails()
        val bookings = ArrayList<String>()
        if (reservation != null) {
            bookings.add(reservation)
        }

        if (bookings.isEmpty()) {
            emptyMessage.visibility = View.VISIBLE
            reservationsListView.visibility = View.GONE
        } else {
            emptyMessage.visibility = View.GONE
            reservationsListView.visibility = View.VISIBLE
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, bookings)
            reservationsListView.adapter = adapter

            val userType = intent.getStringExtra("user_type")
            if (userType == "guest") {
                reservationsListView.setOnItemClickListener { _, _, _, _ ->
                    AlertDialog.Builder(this)
                        .setTitle("Remove Booking")
                        .setMessage("Are you sure you want to remove this booking?")
                        .setPositiveButton("Yes") { _, _ ->
                            sessionManager.clearReservation()
                            Toast.makeText(this, "Booking removed", Toast.LENGTH_SHORT).show()
                            loadReservations()
                        }
                        .setNegativeButton("No", null)
                        .show()
                }
            } else {
                reservationsListView.onItemClickListener = null
            }
        }
    }
}
