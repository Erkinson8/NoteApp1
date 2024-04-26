package com.example.noteapp1.NoteApp.data.model


import android.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteModel")
data class NoteModel(
    val title: String,
    val description: String,
    val tvDateTime: String,
    val color: Int? = Color.WHITE
    ){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
