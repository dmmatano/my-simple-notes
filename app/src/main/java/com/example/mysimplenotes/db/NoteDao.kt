package com.example.mysimplenotes.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mysimplenotes.model.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE title LIKE :query OR body LIKE :query")
    fun searchNote(query: String?): LiveData<List<Note>>

}