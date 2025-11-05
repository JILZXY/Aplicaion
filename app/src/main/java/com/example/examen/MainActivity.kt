package com.example.examen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.example.examen.data.AppDatabase
import com.example.examen.data.ApplicationClass
import com.example.examen.data.DataStoreDarkMode
import com.example.examen.presentation.navManager.NavManager
import com.example.examen.ui.theme.ExamenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val database = (application as ApplicationClass).database
            val userDao = database.userDao()
            val darkModeStore = DataStoreDarkMode(this)
            val darkMode = darkModeStore.getDarkMode().collectAsState(initial = false)
            ExamenTheme (
                darkTheme = darkMode.value
            ) {
                NavManager(darkModeStore, darkMode.value, userDao)
            }
        }
    }
}
