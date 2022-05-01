package com.example.BookBath.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.BookBath.data.local.room.BarberDB
import com.example.BookBath.data.local.room.BarberDao
import com.example.BookBath.data.local.room.BookedBarber

class BookBarRepository(private val dao: BarberDao) {


    fun getBookData(): LiveData<List<BookedBarber>> {
        return dao.getAllBarber()
    }

    suspend fun AddBarber(bookedBarber: BookedBarber){
        dao.addBarber(bookedBarber)
    }

    suspend fun deleteBarber(bookedBarber: BookedBarber){
        dao.deleteBarber(bookedBarber)
    }

}