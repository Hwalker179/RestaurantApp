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

    private lateinit var reservationsListView: ListView
    private lateinit var emptyMessage: TextView
    private val fullBookingRecords = ArrayList<String>()
    private val displayBookings = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservations)

        reservationsListView = findViewById(R.id.lvBookings)
        emptyMessage = findViewById(R.id.tvNoBookings)

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

    override fun onResume() {
        super.onResume()
        loadReservations()
    }

    private fun loadReservations() {
        val userType = intent.getStringExtra("user_type")
        val allReservations = sessionManager.getAllReservations()

        fullBookingRecords.clear()
        displayBookings.clear()

        if (userType == "staff") {
            allReservations.forEach { fullRecord ->
                fullBookingRecords.add(fullRecord)
                displayBookings.add(fullRecord.substringAfter("|", "Invalid Record"))
            }
        } else {
            val currentGuest = sessionManager.getUsername()
            allReservations.forEach { fullRecord ->
                val recordOwner = fullRecord.substringBefore("|", "")
                if (recordOwner == currentGuest) {
                    fullBookingRecords.add(fullRecord)
                    displayBookings.add(fullRecord.substringAfter("|", "Invalid Record"))
                }
            }
        }

        if (displayBookings.isEmpty()) {
            emptyMessage.visibility = View.VISIBLE
            reservationsListView.visibility = View.GONE
        } else {
            emptyMessage.visibility = View.GONE
            reservationsListView.visibility = View.VISIBLE

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, displayBookings)
            reservationsListView.adapter = adapter

            if (userType == "guest") {
                reservationsListView.setOnItemClickListener { _, _, position, _ ->
                    val fullRecordToDelete = fullBookingRecords[position]
                    AlertDialog.Builder(this)
                        .setTitle("Remove Booking")
                        .setMessage("Are you sure you want to remove this booking?")
                        .setPositiveButton("Yes") { _, _ ->
                            sessionManager.removeReservation(fullRecordToDelete)
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
