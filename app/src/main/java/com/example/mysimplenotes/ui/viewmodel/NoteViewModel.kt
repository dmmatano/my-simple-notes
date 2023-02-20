package com.example.mysimplenotes.ui.viewmodel

import android.app.Application
import android.graphics.Color
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysimplenotes.model.Note
import com.example.mysimplenotes.repository.NoteRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class NoteViewModel(
    application: Application,
    private val noteRepository: NoteRepository
): AndroidViewModel(application) {

    fun addNote(note: Note) = viewModelScope.launch {
        noteRepository.addNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        noteRepository.deleteNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        noteRepository.updateNote(note)
    }

    fun getAllNotes() = noteRepository.getAllNotes()

    fun generateRandomColor(): Int {
        return when(Random().nextInt(5)){
            0 -> Color.argb(255, 255,255,255) //white
            1 -> Color.argb(255, 247,219,167) //yellow
            2 -> Color.argb(255, 82,255,184) //green
            3 -> Color.argb(255, 189,213,234) //blue
            else -> Color.argb(255, 241,166,106) //orange
        }
    }

    fun getCurrentDate(): String {
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        } else{
            SimpleDateFormat("dd.MM.yyyy").format(Date())
        }
    }

    fun searchNote(query: String?) = noteRepository.searchNote(query)


}