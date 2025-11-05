package com.example.examen.data

import android.app.Application
import androidx.room.Room
import com.example.examen.data.AppDatabase

class ApplicationClass : Application() {
    lateinit var database: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "user_database"
        ).build()
    }
}
