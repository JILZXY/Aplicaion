package com.example.examen.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.examen.data.InterfaceUser.UserDao
import com.example.examen.data.Entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}