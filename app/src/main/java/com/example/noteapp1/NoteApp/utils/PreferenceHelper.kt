package com.example.noteapp1.NoteApp.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper {
  private lateinit var  sharedPreferences: SharedPreferences
  fun unit (context: Context){
      sharedPreferences = context.getSharedPreferences("shered", Context.MODE_PRIVATE)
  }
    var title: String?
        get() = sharedPreferences.getString("title", "")
        set(value) = sharedPreferences.edit().putString("title", value).apply()
  }