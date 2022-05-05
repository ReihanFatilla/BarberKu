package com.example.BarberKu.data

import androidx.lifecycle.LiveData
import com.example.BarberKu.data.local.room.BarberDao
import com.example.BarberKu.data.local.room.BookedBarber

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