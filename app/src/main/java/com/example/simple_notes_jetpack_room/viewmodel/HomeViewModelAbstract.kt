package com.example.simple_notes_jetpack_room.viewmodel

import androidx.lifecycle.Lifecycle
import kotlinx.coroutines.flow.Flow
import androidx.compose.runtime.State
import com.example.simple_notes_jetpack_room.model.NoteEntity

interface HomeViewModelAbstract {
    val selectedNoteState:State<NoteEntity?>
    val noteListFlow:Flow<List<NoteEntity>>
    fun insertOrupdate(note: NoteEntity)
    fun deleteNote(note: NoteEntity)
    fun updateNote(note: NoteEntity)
    fun selectNote(note: NoteEntity)
}