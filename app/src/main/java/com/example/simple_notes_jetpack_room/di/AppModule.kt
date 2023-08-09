package com.example.simple_notes_jetpack_room.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.simple_notes_jetpack_room.data.AppDatabase
import com.example.simple_notes_jetpack_room.data.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(
    SingletonComponent::class
)
object AppModule {

    @Singleton
    @Provides
    fun ProvideDataBase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "notes.db")
        .fallbackToDestructiveMigration()
        .build()


    @Singleton
    @Provides
    fun provideNoteDao(database: AppDatabase): NoteDao {
        return database.noteDao()
    }

}