package com.example.examen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examen.data.InterfaceUser.UserDao
import com.example.examen.data.Entity.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModel(private val userDao: UserDao) : ViewModel() {

    private val _userId = MutableStateFlow<Int?>(null)

    // SOLUCIÓN: Convierte el Flow resultante en un StateFlow usando stateIn
    val user: StateFlow<User?> = _userId.flatMapLatest { userId ->
        if (userId == null || userId <= 0) {
            // Usa flowOf para crear un Flow simple que emite un único valor (null)
            flowOf(null)
        } else {
            userDao.getById(userId) // Esto ya devuelve un Flow<User?>
        }
    }.stateIn( // Convierte el Flow<User?> a un StateFlow<User?>
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null // El valor inicial será null hasta que la coroutina emita el primer valor
    )

    fun searchUserById(id: Int) {
        _userId.value = id
    }

    fun insertUser(user: User) {
        if (user.uid > 0) { // Validamos que el ID sea válido
            viewModelScope.launch {
                userDao.insertAll(user)
                // Después de insertar, actualizamos el ID para que el Flow se actualice
                _userId.value = user.uid
            }
        }
    }
}