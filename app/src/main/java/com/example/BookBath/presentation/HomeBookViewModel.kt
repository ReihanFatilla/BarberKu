package com.example.BookBath.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.BookBath.data.BookBarRepository
import com.example.BookBath.data.local.room.BarberDB
import com.example.BookBath.data.local.room.BarberDao
import com.example.BookBath.data.local.room.BookedBarber
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeBookViewModel(application: Application): AndroidViewModel(application) {
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