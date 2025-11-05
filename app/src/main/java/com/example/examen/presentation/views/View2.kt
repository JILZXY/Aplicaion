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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Room
import com.example.examen.data.AppDatabase
import com.example.examen.data.Entity.User
import com.example.examen.data.InterfaceUser.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun View2(navController: NavController, userDao: UserDao) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {Text("Vista 2")},
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
        ContenteView2(it, userDao)
    }
}

@Composable
fun ContenteView2(innerPadding: PaddingValues, userDao: UserDao){
    val scope = rememberCoroutineScope()
    var texto1 by rememberSaveable { mutableStateOf("") }
    var texto2 by rememberSaveable { mutableStateOf("") }
    var texto3 by rememberSaveable { mutableStateOf("") }
    Column (verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()){
        Text("Sube tu informacion")
        TextField(
            value = texto1,
            onValueChange = { text -> texto1 = text },
            label = { Text("Nombres") }
        )
        TextField(
            value = texto2,
            onValueChange = { text -> texto2 = text },
            label = { Text("Apellidos") }
        )
        TextField(
            value = texto3,
            onValueChange = { text -> texto3 = text },
            label = { Text("Identificate pa") }
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = {
            scope.launch {
                val newUser = User(texto3, texto1, texto2)
                withContext(Dispatchers.IO) {
                    userDao.insertAll(newUser)
                    println("")
                }
            }
        }) {
            println("hola")
            Text("Guardalo no hay problem my friend")
        }
    }
}