package com.example.BookBath.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.BookBath.data.room.Barber
import com.example.BookBath.data.room.BarberDB
import com.example.BookBath.data.room.BarberDao

class BookRepository(application: Application): AndroidViewModel(application) {


    private val dao: BarberDao = BarberDB.invoke(application).barberDao

    suspend fun getBookData(): LiveData<List<Barber>>? {
        return dao.getAllUser()
    }
}