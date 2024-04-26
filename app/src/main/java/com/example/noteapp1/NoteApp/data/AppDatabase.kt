package com.example.noteapp1.NoteApp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp1.NoteApp.data.db.daos.NoteDao
import com.example.noteapp1.NoteApp.data.model.NoteModel

@Database(entities = [NoteModel::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}
