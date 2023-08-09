package com.example.simple_notes_jetpack_room.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.simple_notes_jetpack_room.model.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("Select * from note")
    fun getAll() : Flow<List<NoteEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(noteEntity: NoteEntity)

    @Delete
    fun delete(noteEntity: NoteEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(noteEntity: NoteEntity)

    @Query("SELECT * from note where roomId =:id")
    suspend fun getNoteById(id: String): NoteEntity

    @Query("DELETE from note")
    suspend fun deleteAll()
}