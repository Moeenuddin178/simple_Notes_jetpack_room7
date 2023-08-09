package com.example.simple_notes_jetpack_room.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.simple_notes_jetpack_room.data.repository.NoteRepository
import com.example.simple_notes_jetpack_room.model.NoteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel(),
    HomeViewModelAbstract {

    private var isScope = CoroutineScope(Dispatchers.IO)
    private val _selectedNoteState: MutableState<NoteEntity?> = mutableStateOf(null)

    override val selectedNoteState: State<NoteEntity?>
        get() = _selectedNoteState

    override val noteListFlow: Flow<List<NoteEntity>> = repository.getAllNotes()

    override fun insertOrupdate(note: NoteEntity) {
        isScope.launch {
            if (note.roomId == null) {
                repository.insert(note)
            } else {
                repository.update(note)
            }
        }
    }

    override fun deleteNote(note: NoteEntity) {
        isScope.launch {
            repository.delete(noteEntity = note)
        }
    }

    override fun updateNote(note: NoteEntity) {
        isScope.launch {
            repository.update(noteEntity = note)
        }
    }

    override fun selectNote(note: NoteEntity) {
        _selectedNoteState.value = note
    }


}