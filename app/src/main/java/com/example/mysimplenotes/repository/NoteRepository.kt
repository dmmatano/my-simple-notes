package com.example.mysimplenotes.repository

import com.example.mysimplenotes.db.NoteDatabase
import com.example.mysimplenotes.model.Note

class NoteRepository(private val database: NoteDatabase) {

    suspend fun addNote(note: Note) = database.getNoteDao().addNote(note)
    suspend fun updateNote(note: Note) = database.getNoteDao().updateNote(note)
    suspend fun deleteNote(note: Note) = database.getNoteDao().deleteNote(note)
    fun getAllNotes() = database.getNoteDao().getAllNotes()
    fun searchNote(query: String?) = database.getNoteDao().searchNote(query)
}