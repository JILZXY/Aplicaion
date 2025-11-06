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

    val user: StateFlow<User?> = _userId.flatMapLatest { userId ->
        if (userId == null || userId <= 0) {
            flowOf(null)
        } else {
            userDao.getById(userId)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    fun searchUserById(id: Int) {
        _userId.value = id
    }

    fun insertUser(user: User) {
        if (user.uid > 0) {
            viewModelScope.launch {
                userDao.insertAll(user)
                _userId.value = user.uid
            }
        }
    }
}