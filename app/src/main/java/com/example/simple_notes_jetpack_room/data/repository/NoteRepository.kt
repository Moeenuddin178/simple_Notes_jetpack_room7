package com.example.simple_notes_jetpack_room.data.repository

import com.example.simple_notes_jetpack_room.data.NoteDao
import com.example.simple_notes_jetpack_room.model.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NoteDao) {

//    fun getAll(): Flow<List<NoteEntity>> = noteDao.getAll()

    fun getAllNotes(): Flow<List<NoteEntity>> = noteDao.getAll().flowOn(Dispatchers.IO)
        .conflate()

    suspend fun insert(noteEntity: NoteEntity) = noteDao.insert(noteEntity = noteEntity)

    suspend fun delete(noteEntity: NoteEntity) = noteDao.delete(noteEntity = noteEntity)

    suspend fun update(noteEntity: NoteEntity) = noteDao.update(noteEntity = noteEntity)


}