package com.example.BookBath.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BarberDao {

    @Insert
    fun addBarber(bookedBarber: BookedBarber)

    @Delete
    fun deleteBarber(bookedBarber: BookedBarber)

    @Query("SELECT * FROM tb_booked_barber")
    fun getAllBarber(): LiveData<List<BookedBarber>>
}