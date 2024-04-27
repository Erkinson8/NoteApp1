package com.example.noteapp1.NoteApp.interfaces

import com.example.noteapp1.NoteApp.data.model.NoteModel

interface OnClickItem {
    fun onLongClick(noteModel: NoteModel)
    fun onClick(noteModel: NoteModel)
}