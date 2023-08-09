package com.example.simple_notes_jetpack_room.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.simple_notes_jetpack_room.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
//
//    companion object {
//        private var INSTANSE: AppDatabase? = null
//        fun getInstance(context: Context): AppDatabase {
//            if (INSTANSE == null) {
//
//                INSTANSE = Room.databaseBuilder(
//                    context,
//                    AppDatabase::class.java, "notes.db"
//                )
//                    .fallbackToDestructiveMigration()
//                    .build()
//            }
//            return INSTANSE as AppDatabase
//
//        }
//    }
}