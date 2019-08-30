package com.example.mylittleencyclopedia.presentation.sharedPrefs

import android.content.Context
import android.content.SharedPreferences

private const val SHAR_PREF = "SHAR_PREF"
private const val TEXT_KEY_USER_NAME = "TEXT_KEY_USER_NAME"
private const val TEXT_KEY_SEARCH_CATEGORY = "TEXT_KEY_SEARCH_CATEGORY"

class SharedPrefManager(private var context: Context?) {

    private val sharedPrefs: SharedPreferences = context!!.getSharedPreferences(SHAR_PREF, Context.MODE_PRIVATE)

    fun saveSharedPrefsUserName(text: String) {
        sharedPrefs
            .edit()
            .putString(TEXT_KEY_USER_NAME, text)
            .apply()
    }

    fun saveSharedPrefsSearchNameCategory(text: String) {
        sharedPrefs
            .edit()
            .putString(TEXT_KEY_SEARCH_CATEGORY, text)
            .apply()
    }

    fun readUserName(): String {
        return sharedPrefs.getString(TEXT_KEY_USER_NAME, "") ?: "!!! ERROR: text not find !!!"
    }

    fun readSearchNameCategory(): String {
        return sharedPrefs.getString(TEXT_KEY_SEARCH_CATEGORY, "") ?: "!!! ERROR: text not find !!!"
    }
}