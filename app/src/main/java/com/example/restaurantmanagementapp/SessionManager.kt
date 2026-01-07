package com.example.restaurantmanagementapp

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)

    companion object {
        const val USERNAME_KEY = "username"
        const val PASSWORD_KEY = "password"
        const val USER_TYPE_KEY = "user_type"
        const val STAFF_USERNAME = "staff"
        const val STAFF_PASSWORD = "password123"
        const val MENU_ITEMS_KEY = "menu_items"
        const val ALL_RESERVATIONS_KEY = "all_reservations"
    }

    fun saveGuestLogin(username: String) {
        val editor = prefs.edit()
        editor.putString(USERNAME_KEY, username)
        editor.apply()
    }

    fun saveReservation(reservationDetails: String) {
        val allReservations = getAllReservations().toMutableSet()
        allReservations.add(reservationDetails)
        val editor = prefs.edit()
        editor.putStringSet(ALL_RESERVATIONS_KEY, allReservations)
        editor.apply()
    }

    fun getAllReservations(): Set<String> {
        return prefs.getStringSet(ALL_RESERVATIONS_KEY, emptySet()) ?: emptySet()
    }

    fun removeReservation(reservationDetails: String) {
        val allReservations = getAllReservations().toMutableSet()
        allReservations.remove(reservationDetails)
        val editor = prefs.edit()
        editor.putStringSet(ALL_RESERVATIONS_KEY, allReservations)
        editor.apply()
    }

    fun getUsername(): String? {
        return prefs.getString(USERNAME_KEY, null)
    }

    fun saveMenuItems(menuItems: Set<String>) {
        val editor = prefs.edit()
        editor.putStringSet(MENU_ITEMS_KEY, menuItems)
        editor.apply()
    }

    fun getMenuItems(): MutableSet<String> {
        return prefs.getStringSet(MENU_ITEMS_KEY, setOf("Burger", "Pizza", "Salad"))?.toMutableSet() ?: mutableSetOf()
    }

    fun clearSession() {
        val editor = prefs.edit()
        editor.remove(USER_TYPE_KEY)
        editor.remove(USERNAME_KEY)
        editor.remove(PASSWORD_KEY)
        editor.apply()
    }
}
