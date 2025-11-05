package com.example.examen.presentation.navManager

import android.net.wifi.hotspot2.pps.HomeSp
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.examen.data.DataStoreDarkMode
import com.example.examen.data.InterfaceUser.UserDao
import com.example.examen.presentation.views.Home
import com.example.examen.presentation.views.View1
import com.example.examen.presentation.views.View2

@Composable
fun NavManager(darkModeStore: DataStoreDarkMode, darkMode: Boolean, userDao: UserDao){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Home"){
        composable ("Home"){ Home(navController) }
        composable ("View1"){ View1(navController, darkModeStore, darkMode) }
        composable ("View2"){ View2(navController, userDao) }
    }
}