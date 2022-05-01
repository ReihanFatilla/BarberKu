package com.example.BookBath.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.BookBath.data.room.Barber
import com.example.BookBath.data.room.BarberDB
import com.example.BookBath.data.room.BarberDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    private val userDao: BarberDao = BarberDB.invoke(application).barberDao

    fun registerUser(user: Barber) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.addUser(user)
        }
    }
}
