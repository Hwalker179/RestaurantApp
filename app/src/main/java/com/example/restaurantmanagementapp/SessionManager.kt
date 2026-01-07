package com.example.restaurantmanagementapp

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)

    companion object {
        const val USER_TYPE_KEY = "user_type"
        const val USERNAME_KEY = "username"
        const val PASSWORD_KEY = "password"
        const val GUEST_USERNAME = "guest"
        const val STAFF_USERNAME = "staff"
        const val STAFF_PASSWORD = "password123"

        const val RESERVATION_NAME_KEY = "reservation_name"
        const val RESERVATION_DATE_KEY = "reservation_date"
        const val RESERVATION_TIME_KEY = "reservation_time"
    }

    fun saveGuestLogin(username: String) {
        val editor = prefs.edit()
        editor.putString(USERNAME_KEY, username)
        editor.putString(PASSWORD_KEY, "")
        editor.putString(USER_TYPE_KEY, "guest")
        editor.apply()
    }

    fun saveReservation(name: String, date: String, time: String) {
        val editor = prefs.edit()
        editor.putString(RESERVATION_NAME_KEY, name)
        editor.putString(RESERVATION_DATE_KEY, date)
        editor.putString(RESERVATION_TIME_KEY, time)
        editor.apply()
    }

    fun getReservationDetails(): String? {
        val name = prefs.getString(RESERVATION_NAME_KEY, null)
        val date = prefs.getString(RESERVATION_DATE_KEY, null)
        val time = prefs.getString(RESERVATION_TIME_KEY, null)

        return if (name != null && date != null && time != null) {
            "Booking for $name on $date at $time"
        } else {
            null
        }
    }

    fun getUsername(): String? {
        return prefs.getString(USERNAME_KEY, null)
    }

    fun getPassword(): String? {
        return prefs.getString(PASSWORD_KEY, null)
    }

    fun clearReservation() {
        val editor = prefs.edit()
        editor.remove(RESERVATION_NAME_KEY)
        editor.remove(RESERVATION_DATE_KEY)
        editor.remove(RESERVATION_TIME_KEY)
        editor.apply()
    }

    fun clearSession() {
        val editor = prefs.edit()
        editor.remove(USER_TYPE_KEY)
        editor.remove(USERNAME_KEY)
        editor.remove(PASSWORD_KEY)

        editor.apply()
    }
}
