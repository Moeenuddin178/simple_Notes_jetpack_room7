package com.example.simple_notes_jetpack_room.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "note")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    var roomId: Long? = null,
    var text: String,
)