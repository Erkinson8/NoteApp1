package com.example.noteapp1.NoteApp

import android.app.Application
import androidx.room.Room
import com.example.noteapp1.NoteApp.data.AppDatabase
import com.example.noteapp1.NoteApp.utils.PreferenceHelper
class App : Application() {
    companion object {
        var appDataBase: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = PreferenceHelper()
        sharedPreferences.unit(this)
        getInstance()
    }

     fun getInstance(): AppDatabase? {
        if (appDataBase == null) {
            appDataBase = applicationContext?.let {
                Room.databaseBuilder(
                    it,
                    AppDatabase::class.java,
                    "note.database"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
        }
        return appDataBase
    }
}
/*class App : Application() {
    companion object {
        private var instance: App? = null
        fun getInstance(): App {
            return instance ?: synchronized(this) {
                instance ?: App().also { instance = it }
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        val sharedPreferences = PreferenceHelper()
        sharedPreferences.unit(this)
        getInstance() // Вызываем getInstance() для инициализации
    }

    var appDataBase: AppDatabase? = null
        private set

    fun initDatabase() {
        appDataBase = applicationContext?.let {
            Room.databaseBuilder(
                it,
                AppDatabase::class.java,
                "note.database"
            ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
        }
    }
}*/