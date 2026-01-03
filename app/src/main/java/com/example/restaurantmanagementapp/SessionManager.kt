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
    }

    fun saveGuestLogin(username: String) {
        val editor = prefs.edit()
        editor.putString(USERNAME_KEY, username)
        editor.putString(PASSWORD_KEY, "") // Guests have no password
        editor.putString(USER_TYPE_KEY, "guest")
        editor.apply()
    }

    fun getUsername(): String? {
        return prefs.getString(USERNAME_KEY, null)
    }

    fun getPassword(): String? {
        return prefs.getString(PASSWORD_KEY, null)
    }

    fun clearSession() {
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }
}
