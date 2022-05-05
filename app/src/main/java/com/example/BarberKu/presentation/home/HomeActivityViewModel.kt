package com.example.BarberKu.presentation.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.BarberKu.data.BookBarRepository
import com.example.BarberKu.data.local.room.BarberDB
import com.example.BarberKu.data.local.room.BarberDao
import com.example.BarberKu.data.local.room.BookedBarber
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeActivityViewModel(application: Application): AndroidViewModel(application) {
    private val barberDao: BarberDao = BarberDB.invoke(application).barberDao
    private val repository: BookBarRepository = BookBarRepository(barberDao)

    val getAllBarber: LiveData<List<BookedBarber>> = repository.getBookData()

    fun AddBarber(bookedBarber: BookedBarber){
        viewModelScope.launch(Dispatchers.IO) {
            repository.AddBarber(bookedBarber)
        }
    }
    
    fun deleteBarber(bookedBarber: BookedBarber){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBarber(bookedBarber)
        }
    }
}