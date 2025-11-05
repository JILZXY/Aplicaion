package com.example.examen.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.examen.data.DataStoreDarkMode
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun View1(navController: NavController, darkModeStore: DataStoreDarkMode, darkMode: Boolean) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {Text("Vista 1")},
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
                    }
                }
            )
        }
    ) {
        ContenteView(it, darkModeStore, darkMode)
    }
}

@Composable
fun ContenteView(innerPadding: PaddingValues, darkModeStore: DataStoreDarkMode, darkMode: Boolean){
    val scope = rememberCoroutineScope()
    Column (verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(innerPadding).fillMaxSize()){
        Text("Cambiale de tema pa")
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = {
            scope.launch {
                if(darkMode) darkModeStore.saveDarkMode(false)
                else darkModeStore.saveDarkMode(true)
            }
        }) { Text("A ver calale")}
    }
}