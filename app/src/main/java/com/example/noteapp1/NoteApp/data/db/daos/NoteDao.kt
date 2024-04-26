package com.example.noteapp1.NoteApp.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.noteapp1.NoteApp.data.model.NoteModel

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteModal: NoteModel)

    @Query("SELECT * FROM noteModel")
    fun getAll():LiveData<List<NoteModel>>
}