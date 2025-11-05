package com.example.examen.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class DataStoreDarkMode(private val context: Context) {
    companion object{
        private val Context.dataStore by preferencesDataStore("darkMode")
        private val DARK_MODE = booleanPreferencesKey("dark_mode")
    }

    fun getDarkMode() = context.dataStore.data.map {
        it[DARK_MODE] ?: false
    }

    suspend fun saveDarkMode(darkMode: Boolean){
        context.dataStore.edit {
            it[DARK_MODE] = darkMode
        }
    }


}